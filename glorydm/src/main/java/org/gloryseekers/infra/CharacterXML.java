package org.gloryseekers.infra;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import org.gloryseekers.domain.CharacterPort;
import org.gloryseekers.domain.model.Character;
import org.gloryseekers.domain.model.Consumible;
import org.gloryseekers.domain.model.Equip;
import org.gloryseekers.domain.model.Loot;
import org.gloryseekers.domain.model.Piece;

public class CharacterXML implements CharacterPort {

	private final XStream xstream;
	
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
    public Character loadCharacter(String absolutePath) throws IOException{
        // TODO Auto-generated method stub
    	BufferedReader reader = new BufferedReader(new FileReader(absolutePath));
    	
    	String line, xmlString="";
    	while((line = reader.readLine()) != null) xmlString += line;
    	reader.close();
    	
        return (Character)xstream.fromXML(xmlString);
    }
    
    public Map<Integer, Character> loadAllCharacters(String loadDirectoryPath) throws IOException {
		System.out.println("CharacterXML" + loadDirectoryPath);
    	File loadDirectory = new File(loadDirectoryPath);
    	Map<Integer, Character> map = new HashMap<Integer, Character>();
    	Character c;
    	
    	for(File file : loadDirectory.listFiles()) {
    		if(file.getName().contains(".xml")) {
    			c = loadCharacter(file.getAbsolutePath());
    			map.put(c.hashCode(), c);
    		}
    	}
    	
    	return map;
    }
    

    @Override
    public void storeCharacter(Character c, String saveDirectoryPath) throws IOException{
        // TODO Auto-generated method stub
		String xmlString = xstream.toXML(c);
    	
    	String path = saveDirectoryPath+c.getOwnerName()+"_"+c.getName()+".xml";
    	
    	BufferedWriter writer = new BufferedWriter(new FileWriter(path));
    	
    	writer.write(xmlString);
    	writer.close();
    }
    
}
