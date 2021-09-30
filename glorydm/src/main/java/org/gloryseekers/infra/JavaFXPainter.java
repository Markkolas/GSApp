package org.gloryseekers.infra;

import org.gloryseekers.domain.Painters;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFXPainter extends Application implements Painters {

    @Override
    public void start(Stage primaryStage) throws Exception {
        var label = new Label("Hello, glory seekers");
        var scene = new Scene(new StackPane(label), 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void start() {
        launch();
    }
    
}