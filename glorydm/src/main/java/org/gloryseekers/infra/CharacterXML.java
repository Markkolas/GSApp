package org.gloryseekers.infra;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;

import org.gloryseekers.domain.CharacterPort;
import org.gloryseekers.domain.model.Character;

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
		mapper.activateDefaultTyping(p, DefaultTyping.JAVA_LANG_OBJECT)
		.enable(SerializationFeature.INDENT_OUTPUT)
		.setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
		.setVisibility(PropertyAccessor.GETTER, Visibility.NONE);
		
		
	}
	
    @Override
    public Character loadCharacter(String absolutePath) throws IOException{
        // TODO Auto-generated method stub
    	BufferedReader reader = new BufferedReader(new FileReader(absolutePath));
    	
    	String line, xmlString="";
    	while((line = reader.readLine()) != null) xmlString += line;
    	reader.close();
    	
        return null;
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
		String jsonString = mapper.writeValueAsString(c);
    	
    	String path = saveDirectoryPath+c.getOwnerName()+"_"+c.getName()+".json";
    	
    	BufferedWriter writer = new BufferedWriter(new FileWriter(path));
    	
    	writer.write(jsonString);
    	writer.close();
    }
    
}
