package org.gloryseekers.domain.model;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.HashMap;

public class Character {
	private short fort;
    private short disc;
    private float silver;
    private boolean state;
    private String name;
    
    private final Consumible water = new Consumible("Agua", 1, 0, 4);
    private final Consumible food = new Consumible("Raciones", 1, 0, 4);
    
    private Map<String, Piece> inventario = new HashMap<String, Piece>();

	public Character(short fort, short disc, float silver, boolean state, String name) {
		this.fort = fort;
		this.disc = disc;
		this.silver = silver;
		this.state = state;
		this.name = name;
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

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Piece> getInventario() {
		
		//Lo admito, solo quiero tocaros los cojones, hay una forma mucho más facil de hacer esto
		Map<String, Piece> aux = Stream.of(new Object[][] {
			{water.getName(), water},
			{food.getName(), food},
		}).collect(Collectors.toMap(data -> (String) data[0], data -> (Piece) data[1]));
		
		aux.putAll(inventario); //El agua y raciones nunca estarán en el "inventario" del personaje...
		
		return aux;
	}

	public Map<String, Piece> getRawInventario() {
		return inventario;
	}
	
	public void setInventario(Map<String, Piece> inventario) {
		this.inventario = inventario;
	}	
}
