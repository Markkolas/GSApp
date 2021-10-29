package org.gloryseekers.infra.view.main;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.gloryseekers.aplication.CharacterManager;
import org.gloryseekers.domain.ManagementPort;
import org.gloryseekers.domain.model.Character;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class MainViewModel {

    private ManagementPort managementPort;

    private MainController controller;

    public MainViewModel(MainController controller) {
        this.controller = controller;
        this.managementPort = CharacterManager.getInstance();
        configureServices();
        dateService.start();
        characerService.start();
    }

    public void addNewCharacter(short fort, short disc, float silver, String charName, String ownerName) {
        int key = managementPort.addCharacter(fort, disc, silver, charName, ownerName);
        characerService.restart();
    }


    private void configureServices() {
        configureCharacterService();
        configureDateService();
    }

    private void configureCharacterService() {
        characerService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                controller.paintCharacters((List<Character>) event.getSource().getValue());
            }

        });

        characerService.setOnFailed(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        event.getSource().getException().printStackTrace();
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setTitle("ERROR");
                        alert.setContentText("Los Personajes no se cargaron correctamente \n "
                                + event.getSource().getException().toString());
                        alert.show();

                    }

                });
            }

        });
    }

    private void configureDateService() {
        dateService.setOnFailed(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        event.getSource().getException().printStackTrace();
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setTitle("ERROR");
                        alert.setContentText(
                                "La fecha no carga correctamente \n" + event.getSource().getException().toString());
                        alert.show();

                    }

                });

            }

        });

    }

    public ReadOnlyObjectProperty<String> getCurrentGameDateProperty() {
        return this.dateService.valueProperty();
    }

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

    public void updateCharacters() {
        characerService.restart();
    }

}
