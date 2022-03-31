package org.gloryseekers.infra.view;

import java.io.IOException;

import org.gloryseekers.domain.model.LogType;
import org.gloryseekers.infra.log.GSLogger;
import org.gloryseekers.infra.view.main.MainController;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashController {

    @FXML
    public StackPane splashPane;

    public void initialize() {
        new SplashScreen().start();
    }

    class SplashScreen extends Thread {

        @Override
        public void run() {
            GSLogger.log(SplashScreen.class, LogType.INFO, "Load main view");
            PauseTransition pause = new PauseTransition(Duration.seconds(3)); //pause gives the loader enough time to load the splash screen. In GNU/Linux it still does not load the image correctly.
            pause.setOnFinished(event -> {
                BorderPane root = null;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(UI.class.getResource("main.fxml"));
                    fxmlLoader.setController(new MainController());
                    root = fxmlLoader.load();
                } catch (IOException e) {
                    GSLogger.log(SplashController.class, LogType.FATAL, e.getMessage());
                    Platform.exit();//close the app if we cant load the main view
                }
                var scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();
                splashPane.getScene().getWindow().hide();
            });
            pause.play();
        }

    }

}
