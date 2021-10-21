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
	
	public int addCharacter(short fort, short disc, float silver, String name) {
		Character c = new Character(fort, disc, silver, false, name);
		characters.put(c.hashCode(), c);
		return c.hashCode(); //for now...
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
	
	public boolean changeCharacter(int key, boolean newState) {
		characters.get(key).setState(newState);
		return true; //for now...
	}
	
	public boolean addPiece(int key, Piece p) {
		Character c = characters.get(key);
		Map<String, Piece> inv = c.getRawInventario();
		
		inv.put(p.getName(), p);
		return true; //for now...
	}
	
	public boolean rmPiece(int key, Piece p) {
		Character c = characters.get(key);
		Map<String, Piece> inv = c.getRawInventario();
		
		inv.remove(p.getName(),p);
		return true;
	}
	
	public boolean rmPiece(int key, String name) {
		Character c = characters.get(key);
		Map<String, Piece> inv = c.getRawInventario();
		
		inv.remove(name);
		return true;
	}
	
	
	//I LIKE IT A LOT
	public static CharacterManager getInstance(CharacterPort c) {
		return instance == null ? instance = new CharacterManager(c) : instance;
	}
	
	public static CharacterManager getInstance() {
		return instance;
	}
	
	
	public Map<Integer, Character> getCharactersMap(){
		return characters;
	}
}
