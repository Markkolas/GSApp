package org.gloryseekers.infra;



import org.gloryseekers.domain.ManagementPort;
import org.gloryseekers.infra.material.CharacterCard;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class UI extends Application {
	
	private ManagementPort manager;
	
    // This is done because javaFX applications are launched from a launcher and modifying the default constructor makes the application unable to start.
    public void setManagementPort(ManagementPort manager) {
        this.manager = manager; 
    }

    public void start(Stage primaryStage) throws Exception {
        //TabPane root = FXMLLoader.load(UI.class.getResource("main.fxml"));
        CharacterCard root = new CharacterCard();
        root.setLoadText("value");
        var scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void start() {
        launch();
    }
    
}
