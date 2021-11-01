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
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class MainViewModel implements NewCharacterWindow.Delegate {

    private ManagementPort managementPort;

    private MainController controller;

    private AppPreferences preferences;

    public MainViewModel(MainController controller) {
        this.preferences = AppPreferences.getSystemInstance();
        this.controller = controller;
        this.managementPort = CharacterManager.getInstance();
        configureServices();
        dateService.start();
    }

    private void configureServices() {
        configureCharacterService();
        configureDateService();
        configureLastURService();
        configureLoadService();
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

    private void configureLoadService() {
        loadService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                System.out.println("Loaded from disk to ram");
                loadCharacters();//(from ram)
            }

        });

        configureError(loadService, "No se cargaron correctamente los personajes del disco a ram");
    }

    private void configureDateService() {
        configureError(dateService, "La fecha no carga correctamente");

    }

    private void configureLastURService() {

        lastURService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                loadService.restart();
            }

        });

        configureError(lastURService, "Las preferencias no cargan correctamente");
    }

    // SERVICES

    /**
     * Service with loads the Characters form ram
     */
    private Service<List<Character>> characerService = new Service<>() {

        @Override
        protected Task<List<Character>> createTask() {
            return new Task<List<Character>>() {

                @Override
                protected List<Character> call() throws Exception {
                    System.out.println("Started characterService");
                    Map<Integer, Character> map = managementPort.getCharactersMap();// from ram
                    System.out.println("Map To Sting " + map.toString());
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
                    return preferences.getProperty("lastpartyurl"); // maybe I can  force an exception is  this is null
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
                    if (!preferences.store())
                        throw new Exception("UADS");
                    return Boolean.TRUE;
                }
            };
        }
    };

    /**
     * Service to load form disk
     */
    private Service<Boolean> loadService = new Service<>() {

        @Override
        protected Task<Boolean> createTask() {
            return new Task<Boolean>() {

                @Override
                protected Boolean call() {
                    String url = preferences.getProperty("lastpartyurl");
                    System.out.println("Loading Characters from " + url);
                    managementPort.loadAllCharacters(url);
                    return Boolean.TRUE;
                }
            };
        }
    };

        /**
     * Service to save to disk
     */
    private Service<Boolean> saveService = new Service<>() {

        @Override
        protected Task<Boolean> createTask() {
            return new Task<Boolean>() {

                @Override
                protected Boolean call() {
             
                    return Boolean.TRUE;
                }
            };
        }
    };

    private void storeProperties() {
        switch (storePropertiesService.getState()) {
        case CANCELLED:
        case FAILED:
        case SUCCEEDED:
            storePropertiesService.restart();
            break;
        case READY:
            storePropertiesService.start();
        case RUNNING:
        case SCHEDULED:
        }
    }

    private void loadCharacters() {
        switch (characerService.getState()) {
        case CANCELLED:
        case FAILED:
        case SUCCEEDED:
            characerService.restart();
            break;
        case READY:
            characerService.start();
        case RUNNING:
        case SCHEDULED:
        }
        updateCharacters();
    }

    private void loadCharactersFromDisk() {
        switch (loadService.getState()) {
        case SUCCEEDED:
        case CANCELLED:
        case FAILED:
            loadService.restart();
            break;
        case READY:
            loadService.start();
        case RUNNING:
        case SCHEDULED:
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
        loadCharactersFromDisk();
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
