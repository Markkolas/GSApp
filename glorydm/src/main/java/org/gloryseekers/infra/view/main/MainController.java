package org.gloryseekers.infra.view.main;

import org.gloryseekers.infra.material.CharacterCard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;

public class MainController {

    @FXML
    private Tab tabOne;

    @FXML
    private Tab tabTwo;
    
    public void initialize() {
        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().addAll(new Label("Hola"), new Label("hey"), new CharacterCard());
        tabOne.contentProperty().set(flowPane);
    }
}
