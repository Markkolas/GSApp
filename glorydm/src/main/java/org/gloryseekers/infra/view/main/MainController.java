package org.gloryseekers.infra.view.main;

import java.util.ArrayList;

import org.gloryseekers.infra.material.CharacterCard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;

public class MainController {

    @FXML
    private Tab tabOne;

    @FXML
    private Tab tabTwo;
    
    public void initialize() {
        FlowPane flowPane = new FlowPane();
        ArrayList<CharacterCard> children = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            children.add(new CharacterCard.Builder()
            .setNameText("Player").setLoadText("5/12")
            .setImage(new Image("http://cr.openjdk.java.net/~jeff/Duke/png/Hips.png"))
            .build());
        }
        flowPane.getChildren().addAll(children);
        tabOne.contentProperty().set(flowPane);
    }
}
