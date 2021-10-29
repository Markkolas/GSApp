package org.gloryseekers.infra.material;

import java.io.IOException;

import org.gloryseekers.aplication.CharacterManager;
import org.gloryseekers.domain.ManagementPort;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewCharacterWindow extends Stage {

    private ManagementPort managementPort = CharacterManager.getInstance();

    public NewCharacterWindow(Delegate delegate) {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(NewCharacterWindow.class.getResource("NewCharacterPane.fxml"));
            fxmlLoader.setController(new Controller(delegate));
            Scene scene = new Scene(fxmlLoader.load());
            this.setScene(scene);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private class Controller extends AnchorPane {

        @FXML
        private TextField fortField;

        @FXML
        private TextField disField;

        @FXML
        private TextField silverField;

        @FXML
        private TextField nameField;

        @FXML
        private TextField ownerField;

        private Delegate delegate;

        public Controller(Delegate delegate) {
            this.delegate = delegate;
        }

        @FXML
        private void handleButtonClick() {
            System.out.print("foo");
            int key1 = managementPort.addCharacter(Short.parseShort(fortField.getText()),
                    Short.parseShort(disField.getText()), Float.parseFloat(silverField.getText()), nameField.getText(),
                    ownerField.getText());
            delegate.handleNewCharacterWindowReturn();
        }

    }

    public interface Delegate {
        public void handleNewCharacterWindowReturn();
    }
}
