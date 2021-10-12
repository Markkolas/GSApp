package org.gloryseekers.infra;

import org.gloryseekers.domain.ManagementPort;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class UI extends Application {
	
	private ManagementPort manager;
	
    // This is done because javaFX applications are launched from a launcher and modifying the default constructor makes the application unable to start.
    public void setManagementPort(ManagementPort manager) {
        this.manager = manager; 
    }

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
