package org.gloryseekers;

import org.gloryseekers.aplication.CharacterManager;
import org.gloryseekers.domain.*;
import org.gloryseekers.infra.*;

import org.gloryseekers.domain.model.*;

public class App{
    public static void main(String[] args) {
    	final CharacterPort storageManager = new CharacterXML(); //load storage adapter
        final UI ui = new UI(); //load graphical interface
        
        ui.setManagementPort(CharacterManager.getInstance(storageManager)); //Load the business manager nad give him to UI
        ui.start(); //launch app
    }

}
