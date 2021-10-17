package org.gloryseekers.infra.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.gloryseekers.infra.view.main.MainController;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SplashController implements Initializable {

    @FXML
    public StackPane splashPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new SplashScreen().start();
    }

    class SplashScreen extends Thread {

        @Override
        public void run() {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(UI.class.getResource("main.fxml"));
                        fxmlLoader.setController(new MainController());
                        TabPane root = fxmlLoader.load();
                        var scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setMaximized(true);
                        stage.show();
                        splashPane.getScene().getWindow().hide();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });

        }

    }

}
