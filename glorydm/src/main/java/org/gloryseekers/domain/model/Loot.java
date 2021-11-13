package org.gloryseekers.domain.model;

/*
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!README!!!!!!!!!!!!!!!!!!!!!!!!!
 * Before making ANY changes to class attributes, please take care look at CharacterXML.java code, specially the initial comment
 */

public class Loot extends Piece {
	private int ammount;
	
	public Loot(String name, int weigth, float value, int ammount){
		super(name, weigth, value);
		this.ammount = ammount;
	}
	
	public int[] getTypeAndAmmountOrCharges() {
		int[] i = new int[] {2,ammount};
		return i;
	}
	
	public void setAmmountOrCharges(int value) {
		ammount = value;
	};
}
