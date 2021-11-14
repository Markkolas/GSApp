package org.gloryseekers.domain.model;

/*
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!README!!!!!!!!!!!!!!!!!!!!!!!!!
 * Before making ANY changes to class attributes, please take care look at CharacterXML.java code, specially the initial comment
 */

public class Equip extends Piece{
	private int ammount;
	
	public Equip(String name, int weigth, float value, int ammount){
		super(name, weigth, value);
		setAmmountOrCharges(ammount);
	}
	
	public int[] getTypeAndAmmountOrCharges() {
		int[] i = new int[] {0,ammount};
		return i;
	}
	
	public void setAmmountOrCharges(int value) {
		ammount = value;
		super.setTotalWeight(super.getWeightPerUnit()*ammount);
	};
		
}
