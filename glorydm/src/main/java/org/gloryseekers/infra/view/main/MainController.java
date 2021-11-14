package org.gloryseekers.infra.view.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.gloryseekers.domain.model.Character;
import org.gloryseekers.domain.model.LogType;
import org.gloryseekers.domain.model.gsdate.GSDate;
import org.gloryseekers.domain.model.gsdate.GSDateFormater;
import org.gloryseekers.infra.log.GSLogger;
import org.gloryseekers.infra.material.CharacterCard;
import org.gloryseekers.infra.view.main.MainViewModel.MainViewModelInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

public class MainController implements MainViewModelInterface {

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
    private Text monthText;

    @FXML
    private Button calendarButton;

    private ArrayList<CharacterCard> characterCards;

    private CharacterCard addNewCharacterCard = new CharacterCard("", "",
            MainController.class.getResourceAsStream("newuser.png"));

    public void initialize() {
        addNewCharacterCard.setOnMouseClicked((c) -> {
            mainViewModel.addNewCharacter();
        });
        charactersCardPane.setVgap(20);
        charactersCardPane.setHgap(20);
        this.characterCards = new ArrayList<>();
        this.mainViewModel = new MainViewModel(this);
        paintCharacters();
    }

    @FXML
    public void onCalendarButtonAction() {
        rootTabPane.selectionModelProperty().get().select(tabTwo);
    }

    private void paintCharacters(List<Character> characters) {
        this.characterCards.clear();
        characters.forEach((c) -> {
            characterCards.add(new CharacterCard(c.getName(), c.getOwnerName()));
        });
        paintCharacters();
    }

    private void paintCharacters() {
        charactersCardPane.getChildren().clear();
        charactersCardPane.getChildren().addAll(characterCards);
        charactersCardPane.getChildren().add(this.addNewCharacterCard);
    }

    private void paintNewDate(GSDate date) {
        calendarButton.setText(GSDateFormater.getDateInstance().format(date));
        monthText.setText(GSDateFormater.getDateInstance().getMonthName(date));//This is temporal
    }

    @FXML
    public void selectDirectory(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(rootTabPane.getScene().getWindow());
        if(selectedDirectory!=null) {
            GSLogger.log(MainController.class, LogType.INFO, "URL selected: " + selectedDirectory.toString());
            mainViewModel.selectNewDirectory(selectedDirectory.toString());
        } else {
            GSLogger.log(MainController.class, LogType.INFO, "DirectoryChooser closed");
        }
    }

    //Delegates

    @Override
    public void handleNewCharacterList(List<Character> characters) {
        paintCharacters(characters);
    }

    @Override
    public void handleNewDate(GSDate gsDate) {
        paintNewDate(gsDate);
    }

}
