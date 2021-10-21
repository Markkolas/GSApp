package org.gloryseekers.domain.model;

public class Consumible extends Piece{
	private int charges;
	
	public Consumible(String name, int weigth, int value, int charges){
		super(name, weigth, value);
		this.charges = charges;
	}
	public int[] getTypeAndAmmountOrCharges() {
		int[] i = new int[] {1,charges};
		return i;
	}
	
	public void setAmmountOrCharges(int value) {
		charges = value;
	};
}
