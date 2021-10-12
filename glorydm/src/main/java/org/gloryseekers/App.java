package org.gloryseekers;

import org.gloryseekers.aplication.CharacterManager;
import org.gloryseekers.domain.*;
import org.gloryseekers.infra.*;

public class App{
    public static void main(String[] args) {
    	final CharacterPort storageManager = new CharacterXML(); //load storage adapter
        final ManagementPort characterManager = new CharacterManager(storageManager); //load business manager
        final UI ui = new UI(); //load graphical interface
        
        ui.start(); //launch app
    }
    
    /*public void start(Stage primaryStage) throws Exception {
        var label = new Label("Hello, glory seekers");
        var scene = new Scene(new StackPane(label), 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void start() {
        launch();
    }*/
}
