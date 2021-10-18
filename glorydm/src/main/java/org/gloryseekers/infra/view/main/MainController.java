package org.gloryseekers.infra.view.main;

import java.util.ArrayList;

import org.gloryseekers.infra.material.CharacterCard;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;

public class MainController {

    @FXML
    private Tab tabOne;

    @FXML
    private Tab tabTwo;

    @FXML
    private FlowPane charactersCardPane;

    private ArrayList<CharacterCard> characterCards;
    
    public void initialize() {
        charactersCardPane.setVgap(20);
        charactersCardPane.setHgap(20);
        this.characterCards = new ArrayList<>();
        mock();
        charactersCardPane.getChildren().addAll(characterCards);
    }

    //debug
    private void mock() {
        for (int i = 0; i < 3; i++) {
            this.characterCards.add(new CharacterCard("wad","adsa","http://cr.openjdk.java.net/~jeff/Duke/png/Hips.png"));
        }
        this.characterCards.add(new CharacterCard("wad","adsa"));
    }
}
