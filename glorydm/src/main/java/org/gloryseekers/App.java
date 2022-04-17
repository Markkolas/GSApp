package org.gloryseekers;

import org.gloryseekers.aplication.CharacterManager;
import org.gloryseekers.domain.*;
import org.gloryseekers.infra.view.UI;
import org.gloryseekers.infra.persistance.characters.CharacterPersistor;

public class App {
    public static void main(String[] args) {
    	final CharacterPort storageManager = new CharacterPersistor(); //load storage adapter
        final ManagementPort managementPort = CharacterManager.getInstance(storageManager);
        final UI ui = new UI(); //load graphical interface    
        ui.start(); //launch app        
    }
}
