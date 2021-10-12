package org.gloryseekers;

import org.gloryseekers.aplication.CharacterManager;
import org.gloryseekers.domain.*;
import org.gloryseekers.infra.*;

public class App {
    public static void main(String[] args) {
        CharacterManager characterManager = CharacterManager.getInstance(); //load business manager
        UI ui = new UI(); //load graphical interface
        CharacterXML characterStorage = new CharacterXML(); //load database manager
        
        ui.start(); //launch app
    }
}
