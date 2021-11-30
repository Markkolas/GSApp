package org.gloryseekers.infra.view.main;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.gloryseekers.aplication.CharacterManager;
import org.gloryseekers.domain.ManagementPort;
import org.gloryseekers.domain.model.Character;
import org.gloryseekers.domain.model.LogType;
import org.gloryseekers.domain.model.gsdate.GSDate;
import org.gloryseekers.infra.log.GSLogger;
import org.gloryseekers.infra.material.NewCharacterWindow;
import org.gloryseekers.infra.preferences.AppPreferences;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

/**
 * The view model corresponding to the main view.
 */
public class MainViewModel implements NewCharacterWindow.Delegate {

    /**
     * The ManagementPort instance.
     */
    private ManagementPort managementPort;

    /**
     * The controller corresponding to this model.
     */
    private MainViewModelInterface controller;

    /**
     * The AppPreferences instance.
     */
    private AppPreferences preferences;

    /**
     * Creates a new intance using the arguments provided.
     * 
     * @param controller The controller corresponding to this model.
     */
    public MainViewModel(MainViewModelInterface controller) {
        this.preferences = AppPreferences.getSystemInstance();
        this.controller = controller;
        this.managementPort = CharacterManager.getInstance();
        configureServices();
        dateService.start();
    }

    /**
     * Configures all this view services
     */
    private void configureServices() {
        configureCharacterService();
        configureDateService();
        configureLastURService();
        configureLoadService();
    }

    /**
     * Configures the given service so that if its status changes to FAILED the app
     * show an alert with the given text and the Exception that caused the failure.
     * 
     * @param <T>
     * @param service The Service which is going to be configured.
     * @param text    A string which represents the error text.
     */
    private <T> void configureError(Service<T> service, String text) {

        service.setOnFailed(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        GSLogger.log(MainViewModel.class, LogType.INFO, text);
                        GSLogger.log(MainViewModel.class, LogType.ERROR, event.getSource().getException().getMessage());
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

    /**
     * Configures the characterService.
     */
    private void configureCharacterService() {
        characerService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                controller.handleNewCharacterList((List<Character>) event.getSource().getValue());
            }

        });

        configureError(characerService, "No se cargaron correctamente los personajes");
    }

    /**
     * Configures the loadService.
     */
    private void configureLoadService() {
        loadService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                System.out.println("Loaded from disk to ram");
                loadCharacters();// (from ram)
            }

        });

        configureError(loadService, "No se cargaron correctamente los personajes del disco a ram");
    }

    /**
     * Configures the dateService.
     */
    private void configureDateService() {
        configureError(dateService, "La fecha no carga correctamente");

        dateService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                controller.handleNewDate((GSDate) event.getSource().getValue());
            }
        });

    }

    /**
     * Configures the lastURLService.
     */
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
     * Service with return Characters form ram.
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

    /**
     * Service used to retrieve the actual GSDate.
     */
    private Service<GSDate> dateService = new Service<>() {

        @Override
        protected Task<GSDate> createTask() {
            return new Task<GSDate>() {

                @Override
                protected GSDate call() throws Exception {
                    return managementPort.getDate();
                }

            };
        }
    };

    /**
     * Service to retrieve the last Directory used as an String.
     */
    private Service<String> lastURService = new Service<>() {

        @Override
        protected Task<String> createTask() {
            return new Task<String>() {

                @Override
                protected String call() throws Exception {
                    return preferences.getProperty("lastpartyurl"); // maybe I can force an exception is this is null
                }
            };
        }
    };

    /**
     * Service to store the properties to the computer's app data storage.
     */
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
     * Service to load Characters form disk to ram.
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
     * Service to save from Ram to Disk.
     */
    private Service<Boolean> saveService = new Service<>() {

        @Override
        protected Task<Boolean> createTask() {
            return new Task<Boolean>() {

                @Override
                protected Boolean call() {
                    String url = preferences.getProperty("lastpartyurl");
                    System.out.println("Saving Characters to " + url);
                    managementPort.storeAllCharacters(url);
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

    private void saveCharactersToDisk() {
        switch (saveService.getState()) {
            case SUCCEEDED:
                saveService.start();
                break;
            case CANCELLED:
            case FAILED:
                saveService.restart();
                break;
            case READY:
                saveService.start();
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
        this.loadCharacters();
    }

    public void selectNewDirectory(String url) {
        AppPreferences.getSystemInstance().setProperty("lastpartyurl", url);
        storeProperties();
        loadCharactersFromDisk();
    }

    public boolean saveCharacters() {
        if (AppPreferences.getSystemInstance().getProperty("lastpartyurl") == null)
            return false;
        saveCharactersToDisk();
        return true;
    }

    public ReadOnlyObjectProperty<GSDate> getCurrentGameDateProperty() {
        return this.dateService.valueProperty();
    }

    // DELEGATES

    @Override
    public void handleNewCharacterWindowReturn() {
        this.updateCharacters();
    }

    public interface MainViewModelInterface {

        public void handleNewCharacterList(List<Character> characters);

        public void handleNewDate(GSDate date);
    }

}
