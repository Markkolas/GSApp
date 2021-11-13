package org.gloryseekers.domain.model;

public class Equip extends Piece{
	private int ammount;
	
	public Equip(String name, int weigth, float value, int ammount){
		super(name, weigth, value);
		this.ammount = ammount;
	}
	
	public int[] getTypeAndAmmountOrCharges() {
		int[] i = new int[] {0,ammount};
		return i;
	}
	
	public void setAmmountOrCharges(int value) {
		ammount = value;
	};
		
}
