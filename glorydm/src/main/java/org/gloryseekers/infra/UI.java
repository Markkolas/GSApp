package org.gloryseekers.infra;

import org.gloryseekers.domain.ManagementPort;
import org.gloryseekers.aplication.CharacterManager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class UI extends Application {
	
	final ManagementPort manager = CharacterManager.getThis();
	/*Siento que lo de arriba es una chapuza pero no se como modificar el constructor de esta clase (hija de Application) sin que rompa...*/
	
    public void start(Stage primaryStage) throws Exception {
        var label = new Label("Hello, glory seekers");
        var scene = new Scene(new StackPane(label), 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void start() {
        launch();
    }
    
}
