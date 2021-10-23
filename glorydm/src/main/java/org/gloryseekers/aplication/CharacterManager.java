package org.gloryseekers.aplication;

import org.gloryseekers.domain.*;
import org.gloryseekers.domain.model.*;
import org.gloryseekers.domain.model.Character;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class CharacterManager implements ManagementPort {

	private static CharacterManager instance = null;
	
	private CharacterPort characterPort;
	private Map<Integer, Character> characters = new HashMap<Integer, Character>();
	private float savedSilver = (float) 0;
	
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
		Map<String, Piece> inv = characters.get(key).getRawInventario();
		int[] info = p.getTypeAndAmmountOrCharges();
		
		if(info[0]!=1 && info[1]>1) inv.get(p.getName()).setAmmountOrCharges(info[1]-1);
		else inv.remove(p.getName());
		
		return true;
	}
	
	public boolean rmPiece(int key, String name) {
		Map<String, Piece> inv = characters.get(key).getRawInventario();
		int[] info = inv.get(name).getTypeAndAmmountOrCharges();
		
		if(info[0]!=1 && info[1]>1) inv.get(name).setAmmountOrCharges(info[1]-1);
		else inv.remove(name);
		
		return true;
	}
	
	public boolean doRest(String tipo, Piece w, Piece f) {
		/* PROBABLY UNNECESARRY
		if(tipo.equals("Estandar")) {
			
			for(Character c : characters.values()) {
				if (c.getState()) {
					c.setWaterCharges(c.getRationsCharges()-1);
					c.setRationsCharges(c.getRationsCharges()-1);
				}
			}
			//TODO: Add one to calendar
		}
		*/
		if (tipo.equals("Corto")) {
			
			for(Character c : characters.values()) {
				if (c.getState()) {
					if(w == null) c.setWaterCharges(c.getRationsCharges()-1);
					else rmPiece(c.hashCode(), w);
						
					if(f == null) c.setRationsCharges(c.getRationsCharges()-1);
					else rmPiece(c.hashCode(), f);
				}
			}
			//TODO:Add one to calendar
		}
		else if (tipo.equals("Largo")) {
			float newSilver, aux=0;
			List<Short> listDisc = new ArrayList<Short>();
			int[] info;
			
			for(Character c : characters.values()) {
				if(c.getState()) {
					listDisc.add(c.getDisc());
					newSilver = c.getSilver();
					
					for(Piece p : c.getInventario().values()) {
						info = p.getTypeAndAmmountOrCharges();
						
						if(info[0] == 1) { //Its Consumible
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
			
		}
		return true; //for now...
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
