package org.gloryseekers.infra.view.main;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.gloryseekers.aplication.CharacterManager;
import org.gloryseekers.domain.ManagementPort;
import org.gloryseekers.domain.model.Character;
import org.gloryseekers.infra.material.NewCharacterWindow;
import org.gloryseekers.infra.preferences.AppPreferences;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class MainViewModel implements NewCharacterWindow.Delegate {

    private ManagementPort managementPort;

    private MainController controller;

    public MainViewModel(MainController controller) {
        this.controller = controller;
        this.managementPort = CharacterManager.getInstance();
        configureServices();
        dateService.start();
        characerService.start();
        lastURService.start();
    }

    private void configureServices() {
        configureCharacterService();
        configureDateService();
        configureLastURService();
    }

    private <T> void configureError(Service<T> service, String text) {

        service.setOnFailed(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        event.getSource().getException().printStackTrace();
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setTitle("ERROR");
                        alert.setContentText(text + " \n " + event.getSource().getException().toString());
                        alert.show();

                    }

                });
            }
        });
    }

    private void configureCharacterService() {
        characerService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                controller.paintCharacters((List<Character>) event.getSource().getValue());
            }

        });

        configureError(characerService, "No se cargaron correctamente los personajes");
    }

    private void configureDateService() {
        configureError(dateService, "La fecha no carga correctamente");

    }

    private void configureLastURService() {

        lastURService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                System.out.println("The last url was " + event.getSource().getValue());
            }

        });

        configureError(lastURService, "Las preferencias no cargan correctamente");
    }

    // SERVICES

    private Service<List<Character>> characerService = new Service<>() {

        @Override
        protected Task<List<Character>> createTask() {
            return new Task<List<Character>>() {

                @Override
                protected List<Character> call() throws Exception {
                    Map<Integer, Character> map = managementPort.getCharactersMap();
                    return map.values().stream().collect(Collectors.toList());
                }

            };
        }
    };

    private Service<String> dateService = new Service<>() {

        @Override
        protected Task<String> createTask() {
            return new Task<String>() {

                @Override
                protected String call() throws Exception {
                    /*
                     * GSDate date = managementPort.getDate(); GSDateFormater gsDateFormater = new
                     * GSDateFormater(); return gsDateFormater.format(date);
                     */
                    return "Ichigatsu, 1, 1001";
                }

            };
        }
    };

    private Service<String> lastURService = new Service<>() {

        @Override
        protected Task<String> createTask() {
            return new Task<String>() {

                @Override
                protected String call() throws Exception {
                    AppPreferences appPreferences = AppPreferences.getSystemInstance();
                    return appPreferences.getProperty("lastpartyurl"); // maybe I can force an exception is this is null
                }
            };
        }
    };

    private Service<Boolean> storePropertiesService = new Service<>() {

        @Override
        protected Task<Boolean> createTask() {
            return new Task<Boolean>() {

                @Override
                protected Boolean call() throws Exception {
                    AppPreferences appPreferences = AppPreferences.getSystemInstance();
                    if (!appPreferences.store())
                        throw new Exception("UADS");
                    return Boolean.TRUE;
                }
            };
        }
    };

    private void storeProperties() {
        switch (storePropertiesService.getState()) {
        case CANCELLED:
        case FAILED:
            storePropertiesService.restart();
            break;
        case READY:
            storePropertiesService.start();
        case RUNNING:
        case SCHEDULED:
        case SUCCEEDED:
        }
    }

    // PUBLIC

    public void addNewCharacter() {
        NewCharacterWindow ncw = new NewCharacterWindow(this);
        ncw.show();
    }

    public void updateCharacters() {
        characerService.restart();
    }

    public void selectNewDirectory(String url) {
        AppPreferences.getSystemInstance().setProperty("lastpartyurl", url);
        storeProperties();
    }

    public ReadOnlyObjectProperty<String> getCurrentGameDateProperty() {
        return this.dateService.valueProperty();
    }

    // DELEGATES

    @Override
    public void handleNewCharacterWindowReturn() {
        this.updateCharacters();
    }

}
