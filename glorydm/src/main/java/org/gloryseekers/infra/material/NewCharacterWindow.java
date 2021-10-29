package org.gloryseekers.infra.material;

import java.io.IOException;

import org.gloryseekers.aplication.CharacterManager;
import org.gloryseekers.domain.ManagementPort;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
        private Delegate delegate;

        public Controller (Delegate delegate) {
            this.delegate = delegate;
        }

        @FXML
        private void handleButtonClick() {
        System.out.print("foo");
        int key1 = managementPort.addCharacter((short) 2, (short) 1, (float) 23.56, "XD", "Moro");
        delegate.handleNewCharacterWindowReturn();
        this.getScene().getWindow().hide();//This does not work, but for now you can close this manualy
        }

    }

    public interface Delegate {
        public void handleNewCharacterWindowReturn();
    }
}
