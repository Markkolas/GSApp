package org.gloryseekers.infra.view;

import java.io.IOException;

import org.gloryseekers.infra.preferences.AppPreferences;
import org.gloryseekers.infra.view.main.MainController;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SplashController {

    @FXML
    public StackPane splashPane;

    public void initialize() {
        new SplashScreen().start();
    }

    class SplashScreen extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(1000); //IDK MEN :D
                AppPreferences.getSystemInstance();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            Platform.runLater(() -> {
               
                TabPane root = null;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(UI.class.getResource("main.fxml"));
                    fxmlLoader.setController(new MainController());
                    root = fxmlLoader.load();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                var scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();
                splashPane.getScene().getWindow().hide();

            });
        }

    }

}
