package org.gloryseekers.domain.model;

public class Consumible extends Piece{
	private int charges;
	
	public Consumible(String name, int weigth, int value, int charges){
		super(name, weigth, value);
		this.charges = charges;
	}

	public int getCharges() {
		return charges;
	}

	public void setCharges(int charges) {
		this.charges = charges;
	}
	
	
}
