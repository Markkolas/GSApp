package org.gloryseekers.aplication;

import org.gloryseekers.domain.*;
import org.gloryseekers.domain.model.*;
import org.gloryseekers.domain.model.Character;

import java.util.Map;
import java.util.HashMap;

public class CharacterManager implements ManagementPort {

	private static CharacterManager instance = null;
	
	private CharacterPort characterPort;
	private Map<Integer, Character> characters = new HashMap<Integer, Character>();
	
	private CharacterManager(CharacterPort characterPort) {
		this.characterPort = characterPort;
	}
	
	public boolean addCharacter(short fort, short disc, float silver, String name) {
		Character c = new Character(fort, disc, silver, false, name);
		characters.put(c.hashCode(), c);
		return true; //for now...
	}
	
	public Character readCharacter(int key) {
		return characters.get(key);
	}
	
	public boolean modCharacter(short fort, short disc, float silver, String name, int key) {
		Character c = characters.get(key);
		
		c.setFort(fort);
		c.setDisc(disc);
		c.setSilver(silver);
		c.setName(name);
		
		return true; //for now...
	}
	
	public boolean rmCharacter(int key) {
		characters.remove(key);
		return true; //for now...
	}
	
	//I LIKE IT A LOT
	public static CharacterManager getInstance(CharacterPort c) {
		return instance == null ? instance = new CharacterManager(c) : instance;
	}
	
	public static CharacterManager getInstance() {
		return instance;
	}
}
