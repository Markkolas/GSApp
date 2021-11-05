package org.gloryseekers.aplication;

import org.gloryseekers.domain.*;
import org.gloryseekers.domain.model.*;
import org.gloryseekers.domain.model.Character;
import org.gloryseekers.domain.model.gsdate.GSDate;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

public class CharacterManager implements ManagementPort {

	private static CharacterManager instance = null;
	
	private CharacterPort characterPort;
	private Map<Integer, Character> characters = new HashMap<Integer, Character>();
	private float savedSilver = (float) 0;
	
	private CharacterManager(CharacterPort characterPort) {
		this.characterPort = characterPort;
	}

	@Override
	public int addCharacter(short fort, short disc, float silver, String charName, String ownerName) {
		Character c = new Character(fort, disc, silver, false, charName, ownerName);
		characters.put(c.hashCode(), c);
		return c.hashCode(); //for now...
	}

	@Override
	public Character readCharacter(int key) {
		return characters.get(key);
	}

	@Override
	public boolean modCharacter(short fort, short disc, float silver, String charName, String ownerName, int key) {
		Character c = characters.get(key);
		
		c.setFort(fort);
		c.setDisc(disc);
		c.setSilver(silver);
		c.setName(charName);
		c.setOwnerName(ownerName);
		
		return true; //for now...
	}

	@Override
	public boolean rmCharacter(int key) {
		characters.remove(key);
		return true; //for now...
	}

	@Override
	public boolean changeCharacter(int key, boolean newState) {
		characters.get(key).setState(newState);
		return true; //for now...
	}

	@Override
	public boolean addPiece(int key, Piece p) {
		Map<String, Piece> inv = characters.get(key).getRawInventario();
		int[] info = p.getTypeAndAmmountOrCharges();
		
		if(inv.containsKey(p.getName()) && info[0] != 1) {
			Piece inInv = inv.get(p.getName());
			int ammount = inInv.getTypeAndAmmountOrCharges()[1];
			inInv.setAmmountOrCharges(info[1]+ammount);
		}
		
		inv.put(p.getName(), p);
		
		characters.get(key).updateLoad();
		
		return true; //for now...
	}

	@Override
	public boolean rmPiece(int key, Piece p) {
		Map<String, Piece> inv = characters.get(key).getRawInventario();
		int[] info = p.getTypeAndAmmountOrCharges();
		
		if(info[0]!=1 && info[1]>1) inv.get(p.getName()).setAmmountOrCharges(info[1]-1);
		else if(inv.remove(p.getName())==null) return false;
		
		characters.get(key).updateLoad();
		
		return true;
	}
	
	@Override
	public boolean rmPiece(int key, String name) {
		Map<String, Piece> inv = characters.get(key).getRawInventario();
		int[] info = inv.get(name).getTypeAndAmmountOrCharges();
		
		if(info[0]!=1 && info[1]>1) inv.get(name).setAmmountOrCharges(info[1]-1);
		else if(inv.remove(name)==null) return false;
		
		characters.get(key).updateLoad();
		
		return true;
	}

	@Override
	public boolean doShortRest() {
		for(Character c : characters.values()) {
			if (c.getState()) {
				c.setWaterCharges(c.getRationsCharges()-1);
				c.setRationsCharges(c.getRationsCharges()-1);
			}
		}
		//TODO:Add one to calendar
		return true; //for now...
	}

	@Override
	public boolean doLongRest() {
		float newSilver, aux=0;
		List<Short> listDisc = new ArrayList<Short>();
		int[] info;
		
		for(Character c : characters.values()) {
			if(c.getState()) {
				listDisc.add(c.getDisc());
				newSilver = c.getSilver();
				
				for(Piece p : c.getInventario().values()) {
					info = p.getTypeAndAmmountOrCharges();
					
					if(info[0] == 1 && info[1] < 4) { //Its Consumible AND has to be recharged
						p.setAmmountOrCharges(4);
					}
					else if(info[0] == 2) { //Its Loot
						while(info[1] >= 1) {
							newSilver += p.getValue();
							rmPiece(c.hashCode(), p);
							info[1]--;
						}
					}
				}
				aux += newSilver;
				c.setSilver(0);
			}
		}
		
		for(short disc : listDisc) savedSilver += aux*((float)0.05+(float)(0.02*disc));
		//TODO: Add 5 to calendar logic
		
		return true;//for now...
	}
	
	//I LIKE IT A LOT
	public static CharacterManager getInstance(CharacterPort c) {
		return instance == null ? instance = new CharacterManager(c) : instance;
	}
	
	public static CharacterManager getInstance() {
		return instance;
	}
	
	@Override
	public Map<Integer, Character> getCharactersMap(){
		return characters;
	}
	
	@Override
	public float getSavedSilver() {
		return savedSilver;
	}

	@Override
	public GSDate getDate() {
		
		return new GSDate();//mock
	}
	
	/*
	 * FOR THE UI PROGRAMER: These functions work on core level, updating instances on memory or saving characters in files. It is expected that these functions
	 * will be used in conjuction with getCharactersMap() method.
	 */
	
	public boolean loadCharacter(String absolutePath) {
		try {
			/* THIS IS NOT VERY CHEEKI BREEEKI
			 * By doing this, user could load an already loaded character, creating not a logic but conceptual duplicate of the character.
			 * Two diferent instances with same attributes would appear in the character map, this is bad to the user.
			 * 
			 * TODO: Implement equals() method in Character and inventory classes so we can compare by using forEach() or containsValue() method.
			 */
			Character c = characterPort.loadCharacter(absolutePath);
			characters.put(c.hashCode(), c);
			return true;
		}
		catch(IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean loadAllCharacters(String loadDirectoryPath){
		try {
			characters = characterPort.loadAllCharacters(loadDirectoryPath);
			return true;
		}
		catch(IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean storeCharacter(Character c, String saveDirectoryPath) {
		try {
			characterPort.storeCharacter(c, saveDirectoryPath);
			return true;
		}
		catch(IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
