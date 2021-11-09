package org.gloryseekers.infra;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NumericNode;

import com.fasterxml.jackson.core.JsonParser;

import org.gloryseekers.domain.model.Character;

public class CharacterDeserializer extends StdDeserializer<Character>{
	public CharacterDeserializer() {
		this(null);
	}
	
	public CharacterDeserializer(Class<?> vc) {
		super(vc);
	}
	
	@Override
	public Character deserialize(JsonParser jp, DeserializationContext ct) throws IOException, JsonMappingException{
		JsonNode node = jp.getCodec().readTree(jp);
		
		short fort = (short) ((NumericNode) node.get("fort")).asInt();
	    short disc = (short) ((NumericNode) node.get("disc")).asInt();
	    short load = (short) ((NumericNode) node.get("disc")).asInt();
	    float silver = (float) ((NumericNode) node.get("silver")).asDouble();
	    boolean state = ((NumericNode) node.get("state")).asBoolean();
	    String characterName = node.get("characterName").asText();
	    String ownerName = node.get("ownerName").asText();
	}
}
