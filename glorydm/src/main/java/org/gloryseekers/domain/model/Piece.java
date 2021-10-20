package org.gloryseekers.domain.model;

public abstract class Piece {
	private String name;
	//Integer class implements methos to treat ints as unsigned values, but Java dosent implement the structure itself
	private int weight; 
	private int value;
	
	protected Piece(String name, int weigth, int value) {
		this.name = name;
		this.weight = weigth;
		this.value = value;
	}
	
	//GETTERS AND SETTERS
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public abstract int[] getTypeAndAmmountOrCharges(); //Will be of use ->int[0]=> 0=Equip, 1=Consumable, 2=Loot
	public abstract void setAmmountOrCharges(int value);
	
}
