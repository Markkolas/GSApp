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
        
        /////////////////////////////////////ALPHA TEST////////////////////////////////////////
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
        System.out.println(agua.getName()+" "+agua.getValue()+" "+agua.getWeight()+" "+agua.getTypeAndAmmountOrCharges()[1]+"\n");
        
        //Modify characters and equipment
        c.setSilver(c.getSilver()-(float)1.6);
        System.out.println(c.getSilver()+"\n"); //Formatting will prob be necesary...
        Piece p = new Equip("Escudo", 2, (float)10.0, 1);
        manager.addPiece(key4, p); //Add shield
        manager.addPiece(key4, p); //Add another to test add and load logic
        System.out.println("Inventory list: "+c.getInventario().toString()+"\nThe load is: "+c.getLoad());
        manager.rmPiece(key4, "Escudo"); //Remove the shield
        System.out.println("Inventory list: "+c.getInventario().toString()+"\nThe load is: "+c.getLoad());
        
        //Delete character
        manager.rmCharacter(key1);
        list = manager.getCharactersMap().toString();
        System.out.println(list+"\n");
        
        //State changing test
        c.setState(true);
        System.out.println("You should see true -> "+c.getState());
        c.setState(false);
        System.out.println("You should see false -> "+c.getState());
        
        System.out.println("CRUD alpha version: OK\n");
        
        //Short rest
        Character c1 = manager.getCharactersMap().get(key2);
        Character c2 = manager.getCharactersMap().get(key3);
        c1.setState(true);
        c2.setState(true);
        
        Piece platos = new Loot("Plato de plata", 3, (float)30.0, 3);
        Piece copas = new Loot("Copa de plata", 2, (float)10.0, 2);
        manager.addPiece(key2, platos);
        manager.addPiece(key3, copas);
        
        manager.doShortRest(null, null);
        System.out.println("Current water and ration charges:"+c1.getWaterCharges()+" "+c1.getRationsCharges()+"    "+
        		c2.getWaterCharges()+" "+c2.getRationsCharges());
        
        Piece conejo = new Loot("Conejo",1, (float)6.4, 1);
        Piece pollo = new Loot("Pollo",1, (float)2.3, 1);
        manager.addPiece(key2, p);
        manager.addPiece(key2, conejo);
        manager.addPiece(key3, pollo);
        manager.doShortRest(null, conejo);
        System.out.println("Current water and ration charges:"+c1.getWaterCharges()+" "+c1.getRationsCharges()+"    "+
        		c2.getWaterCharges()+" "+c2.getRationsCharges());
        
        System.out.println("Inventory lists: "+c1.getInventario().toString()+"\n"+c2.getInventario().toString()+"\n");
        
        //Long rest
        manager.doLongRest();
        System.out.println("Current water and ration charges:"+c1.getWaterCharges()+" "+c1.getRationsCharges()+"    "+
        		c2.getWaterCharges()+" "+c2.getRationsCharges());
        System.out.println("Inventory lists: "+c1.getInventario().toString()+"\n"+c2.getInventario().toString()+"\n");
        System.out.println("Current savings: "+manager.getSavedSilver()+"\n");
        
        System.out.println("PreAlpha rest logic: OK\n");
    }
}
