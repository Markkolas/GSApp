package org.gloryseekers.domain.model;

/*
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!README!!!!!!!!!!!!!!!!!!!!!!!!!
 * Before making ANY changes to class attributes, please take care look at CharacterXML.java code, specially the initial comment
 */

public abstract class Piece {
	private String name;
	//Integer class implements methos to treat ints as unsigned values, but Java dosent implement the structure itself
	private final int weightPerUnit;
	private int totalWeight;
	private float value;
	
	protected Piece(String name, int weigth, float value) {
		this.name = name;
		this.weightPerUnit = weigth;
		this.value = value;
	}
	
	//GETTERS AND SETTERS
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeightPerUnit() {
		return weightPerUnit;
	}
	public int getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(int weight) {
		this.totalWeight = weight;
	}
	public float getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public abstract int[] getTypeAndAmmountOrCharges(); //Will be of use ->int[0]=> 0=Equip, 1=Consumable, 2=Loot
	public abstract void setAmmountOrCharges(int value);
	
}
