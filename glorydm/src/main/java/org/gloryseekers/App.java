package org.gloryseekers;

import org.gloryseekers.aplication.CharacterManager;
import org.gloryseekers.domain.*;
import org.gloryseekers.infra.*;

import org.gloryseekers.domain.model.*;
import org.gloryseekers.domain.model.Character;
import org.gloryseekers.domain.model.*;

public class App{
    public static void main(String[] args) {
    	final CharacterPort storageManager = new CharacterXML(); //load storage adapter
        final UI ui = new UI(); //load graphical interface
        
//        ui.setManagementPort(CharacterManager.getInstance(storageManager)); //Load the business manager nad give him to UI
//        ui.start(); //launch app
        
        /////////////////////////////////////TEST////////////////////////////////////////
        ManagementPort manager = CharacterManager.getInstance(storageManager);
        
        //Add characters Test and read characters test
        int key1 = manager.addCharacter((short) 2, (short) 1, (float) 23.56, "Puto");
        int key2 = manager.addCharacter((short) 1, (short) 3, (float) 96, "Juan");
        int key3 = manager.addCharacter((short) -2, (short) 1, (float) 46.5, "Yoyi");
        int key4 = manager.addCharacter((short) 2, (short) -1, (float) 2.4, "Pato");
        
        String list = manager.getCharactersMap().toString();
        System.out.println(list+"\n");
        
        Character c = manager.readCharacter(key4);
        System.out.println(c.getFort()+" "+c.getDisc()+" "+c.getSilver()+" "+c.getName());
        System.out.println(c.getInventario().toString());
        
        Piece agua = c.getInventario().get("Agua");
        System.out.println(agua.getName()+" "+agua.getValue()+" "+agua.getWeight()+" "+agua.getTypeAndAmmountOrCharges()[1]);
        
        
    }

}
