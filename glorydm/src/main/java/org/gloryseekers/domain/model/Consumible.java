package org.gloryseekers.domain.model;

/*
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!README!!!!!!!!!!!!!!!!!!!!!!!!!
 * Before making ANY changes to class attributes, please take care look at CharacterXML.java code, specially the initial comment
 */

public class Consumible extends Piece{
	private int charges;
	
	public Consumible(String name, float value, int charges){
		super(name, 0, value);
		setAmmountOrCharges(charges);
	}
	public int[] getTypeAndAmmountOrCharges() {
		int[] i = new int[] {1,charges};
		return i;
	}
	
	public void setAmmountOrCharges(int value) {
		charges = value;
		if(value % 4 != 0) super.setWeight((value/4)+1);
		else super.setWeight(value/4);
	}
}
