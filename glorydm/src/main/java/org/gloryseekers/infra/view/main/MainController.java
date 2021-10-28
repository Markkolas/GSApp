package org.gloryseekers.infra.view.main;

import java.util.ArrayList;
import java.util.List;

import org.gloryseekers.domain.CharacterPort;
import org.gloryseekers.domain.ManagementPort;
import org.gloryseekers.domain.model.Character;
import org.gloryseekers.infra.material.CharacterCard;
import org.gloryseekers.infra.view.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Button;

public class MainController {

    private MainViewModel mainViewModel;
    @FXML
    private TabPane rootTabPane;

    @FXML
    private Tab tabOne;

    @FXML
    private Tab tabTwo;

    @FXML
    private FlowPane charactersCardPane;

    @FXML
    private Button calendarButton;

    private ArrayList<CharacterCard> characterCards;


    public void initialize() {
        charactersCardPane.setVgap(20);
        charactersCardPane.setHgap(20);
        this.characterCards = new ArrayList<>();
        this.mainViewModel = new MainViewModel(this);
        calendarButton.textProperty().bind(mainViewModel.getCurrentGameDateProperty());
    }

    @FXML
    public void onCalendarButtonAction() {
        rootTabPane.selectionModelProperty().get().select(tabTwo);
    }

    public void paintCharacters(List<Character> characters) {
        this.characterCards.clear();
        characters.forEach((c)-> {
            characterCards.add(new CharacterCard(c.getName(), c.getOwnerName()));
        });
        charactersCardPane.getChildren().clear();
        charactersCardPane.getChildren().addAll(characterCards);
    }
}
