package org.gloryseekers.infra;

import org.gloryseekers.domain.CharacterPort;
import org.gloryseekers.domain.model.*;
import org.gloryseekers.domain.model.Character;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

import java.util.Map;
import java.util.HashMap;

public class CharacterXML implements CharacterPort {

	XStream xstream;
	
	public CharacterXML() {
		//Configuration stage
		xstream = new XStream(new DomDriver());
		
		xstream.alias("Character", Character.class);
		xstream.alias("Equip", Equip.class);
		xstream.alias("Loot", Loot.class);
		xstream.alias("Piece", Piece.class);
		
		//An attacker could use the XML files unmarshallowing to bad things. This line prevents it.
		xstream.allowTypes(new Class[] {Character.class, Equip.class, Consumible.class, Loot.class});
	}
	
    @Override
    public Character loadCharacter(String path) throws IOException{
        // TODO Auto-generated method stub
    	BufferedReader reader = new BufferedReader(new FileReader("charSaves/"+path));
    	
    	String line, xmlString="";
    	while((line = reader.readLine()) != null) xmlString += line;
    	
        return (Character)xstream.fromXML(xmlString);
    }
    /*
    public Map<Integer, Character> loadAllCharacters(){
    	
    }
    */

    @Override
    public boolean storeCharacter(Character c) throws IOException{
        // TODO Auto-generated method stub
		String xmlString = xstream.toXML(c);
    	
    	String path = "charSaves/"+c.getOwnerName()+"_"+c.getName()+".xml";
    	
    	BufferedWriter writer = new BufferedWriter(new FileWriter(path));
    	
    	writer.write(xmlString);
    	writer.close();
    	
        return true;//for now...
    }
    
}
