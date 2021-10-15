package org.gloryseekers.domain.model;

public class Equip extends Piece{
	private int ammount;
	
	public Equip(String name, int weigth, int value, int ammount){
		super(name, weigth, value);
		this.ammount = ammount;
	}

	public int getAmmount() {
		return ammount;
	}

	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}
		
}
