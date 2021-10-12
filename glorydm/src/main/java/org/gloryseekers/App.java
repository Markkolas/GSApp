package org.gloryseekers;

import org.gloryseekers.aplication.CharacterManager;
import org.gloryseekers.domain.*;
import org.gloryseekers.infra.*;

public class App{
    public static void main(String[] args) {
    	final CharacterPort storageManager = new CharacterXML(); //load storage adapter
        final ManagementPort characterManager = new CharacterManager(storageManager); //load business manager
        final UI ui = new UI(); //load graphical interface
        ui.setManagementPort(CharacterManager.getInstance());
        ui.start(); //launch app
    }

}
