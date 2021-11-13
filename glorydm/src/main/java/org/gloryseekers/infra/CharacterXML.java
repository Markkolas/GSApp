package org.gloryseekers.infra;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;

import org.gloryseekers.domain.CharacterPort;
import org.gloryseekers.domain.model.Character;
import org.gloryseekers.domain.model.Piece;
import org.gloryseekers.domain.model.Loot;
import org.gloryseekers.domain.model.Equip;
import org.gloryseekers.domain.model.Consumible;


/*
 * PLEASE READ:
 * 
 * This code depends on the core of the application, although the core dosent depend on this (following the hexagonal architecture).
 * This class serializes to and deserializes from JSON files wich contains data related to one character. To archive this, Jackson databing
 * library is used.
 * The serialization is done automatically using the fields of the Character class. It is implemented in the storeCharacter() method.
 * The deserialization is NOT done automatically. Care must be taken when changing the attributes of Character or Piece (and sons)
 * classes. The deserialization code is in loadCharacter() method. Please, if you change the data structure of any class that has to be
 * serialized, ensure to make the corresponding changes here so the characters can be satisfactorily loaded. These changes could consist on changing
 * the index names used to load the attributes or add specific load logic.
 */

public class CharacterXML implements CharacterPort {

	private final ObjectMapper mapper;
	public CharacterXML() {
		//Configuration stage
		//This is needed bacause we handle polymorphism
		
		PolymorphicTypeValidator p = BasicPolymorphicTypeValidator.builder()
				.allowIfSubType("org.gloryseekers.domain.model")
				.allowIfSubType(Map.class)
				.build();
		
		mapper = new ObjectMapper();
		mapper.activateDefaultTyping(p, DefaultTyping.NON_FINAL)
		.enable(SerializationFeature.INDENT_OUTPUT)
		.setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
		.setVisibility(PropertyAccessor.GETTER, Visibility.NONE);
		
		
	}
	
    @Override
    public Character loadCharacter(File jsonFile) throws IOException{
        // TODO Auto-generated method stub
    	BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
    	/*
    	 * Javascript Arrays will appear constantly because we need to constantly know what class we are handling. The type of class will be in the 
    	 * first element of the array (index 0) and the data on the second (index 1)
    	 * 
    	 * ALTERNATIVE: Make custom serializer to avoid that -> extra work if Character or Piece data model changes in the future
    	 */
    	String line, jsonString="";
    	while((line = reader.readLine()) != null) jsonString += line;
    	reader.close();
    	
    	System.out.println(jsonString);
    	JsonNode node = mapper.readTree(jsonString).get(1);
    	
    	short fort = (short) ((NumericNode) node.get("fort")).asInt();
	    short disc = (short) ((NumericNode) node.get("disc")).asInt();
	    //short load = (short) ((NumericNode) node.get("disc")).asInt();
	    float silver = (float) ((NumericNode) node.get("silver")).asDouble();
	    boolean state = ((BooleanNode) node.get("state")).asBoolean();
	    String characterName = node.get("characterName").asText();
	    String ownerName = node.get("ownerName").asText();
	    
	    int waterCharges = ((NumericNode) node.get("water").get(1).get("charges")).asInt();
	    int rationCharges = ((NumericNode) node.get("rations").get(1).get("charges")).asInt();
	    
	    Map<String,Piece> inventory = createInventory(node.get("inventario").get(1));
	    
        return new Character(fort,
        		disc,
        		silver,
        		state,
        		characterName,
        		ownerName,
        		new Consumible("Agua", 0, waterCharges),
        		new Consumible("Raciones", 0, rationCharges),
        		inventory);
    }
    
    public Map<Integer, Character> loadAllCharacters(File directory) throws IOException {
    	Map<Integer, Character> map = new HashMap<Integer, Character>();
    	Character c;
    	
    	for(File file : directory.listFiles()) {
    		if(file.getName().contains(".json")) {
    			c = loadCharacter(file);
    			map.put(c.hashCode(), c);
    		}
    	}
    	
    	return map;
    }
    

    @Override
    public void storeCharacter(Character c, String saveDirectoryPath) throws IOException{
        // TODO Auto-generated method stub
		String jsonString = mapper.writeValueAsString(c);
    	
    	String path = saveDirectoryPath+c.getOwnerName()+"_"+c.getName()+".json";
    	
    	BufferedWriter writer = new BufferedWriter(new FileWriter(path));
    	
    	writer.write(jsonString);
    	writer.close();
    } 
    
    private Map<String,Piece> createInventory(JsonNode inventoryNode){
    	if(inventoryNode.isEmpty()) return new HashMap<String, Piece>();
    	
    	JsonNode node;
    	Piece e;
    	Map<String,Piece> inventory = new HashMap<String, Piece>();
    	Iterator<JsonNode> iterator = inventoryNode.elements();
    	
    	while(iterator.hasNext()) {
    		node = iterator.next();
    		
    		switch(node.get(0).asText()){
    		
    		case "org.gloryseekers.domain.model.Equip":
    			node = node.get(1);
    			e = new Equip(node.get("name").asText(),
    					node.get("weight").asInt(),
    					(float) node.get("value").asDouble(),
    					node.get("ammount").asInt());
    			inventory.put(e.getName(), e);
    			break;
    			
    		case "org.gloryseekers.domain.model.Loot":
    			node = node.get(1);
    			e = new Loot(node.get("name").asText(),
    					node.get("weight").asInt(),
    					(float) node.get("value").asDouble(),
    					node.get("ammount").asInt());
    			inventory.put(e.getName(), e);
    			break;
    			
    		case "org.gloryseekers.domain.model.Consumible":
    			e = new Consumible(node.get("name").asText(),
    					(float) node.get("value").asDouble(),
    					node.get("charges").asInt());
    			inventory.put(e.getName(), e);
    			break;
    			
    		default: 
    			System.out.println("Something went very bad in character loading");
    			return new HashMap<String, Piece>();
    		
    		}
    	}
    	
    	return inventory;
    }
}
