package org.gloryseekers.infra.view;

import org.gloryseekers.domain.ManagementPort;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;

public class UI extends Application {
	
	private ManagementPort manager;

    private static ManagementPort managementPort;
	
    // This is done because javaFX applications are launched from a launcher and modifying the default constructor makes the application unable to start.
    public void setManagementPort(ManagementPort manager) {
        this.manager = manager; 
    }

    public ManagementPort getManagementPort() {
        return this.manager;
    }

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(UI.class.getResource("Splash.fxml"));
        fxmlLoader.setController(new SplashController(this));
        StackPane root = fxmlLoader.load();
        var scene = new Scene(root);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void start() {
        launch();
    }
    
}