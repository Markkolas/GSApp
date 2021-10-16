package org.gloryseekers.infra.view.main;

import java.util.ArrayList;

import org.gloryseekers.infra.material.CharacterCard;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;

public class MainController {

    @FXML
    private Tab tabOne;

    @FXML
    private Tab tabTwo;

    private ArrayList<CharacterCard> characterCards;
    
    public void initialize() {
        FlowPane flowPane = new FlowPane();
        this.characterCards = new ArrayList<>();
        mock();
        flowPane.getChildren().addAll(characterCards);
        tabOne.contentProperty().set(flowPane);
    }

    //debug
    private void mock() {
        for (int i = 0; i < 3; i++) {
            this.characterCards.add(new CharacterCard("wad","adsa",new Image("http://cr.openjdk.java.net/~jeff/Duke/png/Hips.png",400,400,false,false)));
        }
        this.characterCards.add(new CharacterCard("wad","adsa"));
    }
}
