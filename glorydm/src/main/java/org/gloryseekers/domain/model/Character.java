package org.gloryseekers.domain.model;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.HashMap;

/*
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!README!!!!!!!!!!!!!!!!!!!!!!!!!
 * Before making ANY changes to class attributes, please take care look at CharacterXML.java code, specially the initial comment
 */
public class Character {

	private short fort;

    private short disc;

    private float silver;

    private boolean state;

    private String characterName;

    private String ownerName;

    private short load;

    private Consumible water;
	
    private Consumible rations;
    
    private Map<String, Piece> inventario = new HashMap<String, Piece>();

	public Character(short fort, short disc, float silver, boolean state, String characterName, String ownerName) {
		this.fort = fort;
		this.disc = disc;
		this.silver = silver;
		this.state = state;
		this.characterName = characterName;
		this.ownerName = ownerName;
		
		this.water = new Consumible("Agua", 0, 4);
		this.rations = new Consumible("Raciones", 0, 4);
		
		updateLoad();
	}
	
	//This will be usefull
	public Character(short fort, short disc, float silver, boolean state, String characterName, String ownerName, Consumible water, Consumible rations, Map<String, Piece> inventario) {
		this.fort = fort;
		this.disc = disc;
		this.silver = silver;
		this.state = state;
		this.characterName = characterName;
		this.ownerName = ownerName;
		this.water = water;
		this.rations = rations;
		this.inventario = inventario;
		
		updateLoad();
	}

	//GETTERS AND SETTERS
	public short getFort() {
		return fort;
	}

	public void setFort(short fort) {
		this.fort = fort;
	}

	public short getDisc() {
		return disc;
	}

	public void setDisc(short disc) {
		this.disc = disc;
	}

	public float getSilver() {
		return silver;
	}

	public void setSilver(float silver) {
		this.silver = silver;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String name) {
		this.ownerName = name;
	}
	
	public String getName() {
		return characterName;
	}

	public void setName(String name) {
		this.characterName = name;
	}
	
	public void setWaterCharges(int charges) {
		water.setAmmountOrCharges(charges);
	}
	
	public int getWaterCharges() {
		return (int) water.getTypeAndAmmountOrCharges()[1];
	}
	
	public void setRationsCharges(int charges) {
		rations.setAmmountOrCharges(charges);
	}
	
	public int getRationsCharges() {
		return (int) rations.getTypeAndAmmountOrCharges()[1];
	}

	public Map<String, Piece> getInventario() {
		
		//Lo admito, solo quiero tocaros los cojones, hay una forma mucho m??s facil de hacer esto
		Map<String, Piece> aux = Stream.of(new Object[][] {
			{water.getName(), water},
			{rations.getName(), rations},
		}).collect(Collectors.toMap(data -> (String) data[0], data -> (Piece) data[1]));
		
		aux.putAll(inventario); //El agua y raciones nunca estar??n en el "inventario" del personaje...
		
		return aux;
	}

	public Map<String, Piece> getRawInventario() {
		return inventario;
	}
	
	public void setInventario(Map<String, Piece> inventario) {
		this.inventario = inventario;
	}
	
	public void updateLoad() {
		load=0;
		for(Piece p : getInventario().values()) load += p.getTotalWeight();
	}
	
	public short getLoad() {
		return load;
	}
}
