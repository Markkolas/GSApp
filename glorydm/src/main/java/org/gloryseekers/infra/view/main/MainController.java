package org.gloryseekers.infra.view.main;

import java.util.ArrayList;

import org.gloryseekers.domain.CharacterPort;
import org.gloryseekers.domain.ManagementPort;
import org.gloryseekers.infra.material.CharacterCard;
import org.gloryseekers.infra.view.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Button;

public class MainController {

    private MainViewModel mainViewModel = new MainViewModel();

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

    private ManagementPort managementPort;

    private ArrayList<CharacterCard> characterCards;


    
    public MainController(UI ui) {
        this.managementPort = ui.getManagementPort();
    }

    public void initialize() {
        charactersCardPane.setVgap(20);
        charactersCardPane.setHgap(20);
        this.characterCards = new ArrayList<>();
        mock();
        charactersCardPane.getChildren().addAll(characterCards);
        calendarButton.textProperty().bind(mainViewModel.getCurrentGameDateProperty());

    }

    @FXML
    public void onCalendarButtonAction() {
        rootTabPane.selectionModelProperty().get().select(tabTwo);
    }

    //debug 
    private void mock() {
        for (int i = 0; i < 3; i++) {
            this.characterCards.add(new CharacterCard("wad","adsa","http://cr.openjdk.java.net/~jeff/Duke/png/Hips.png"));
        }
        this.characterCards.add(new CharacterCard("wad","adsa"));
    }
}
