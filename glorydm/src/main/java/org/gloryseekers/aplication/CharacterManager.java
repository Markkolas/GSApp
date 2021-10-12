package org.gloryseekers.aplication;

import org.gloryseekers.domain.*;

public class CharacterManager implements ManagementPort {

	private static CharacterManager INSTANCE; //Puede ser util, de todas forma ver UI.java...
	
	private CharacterPort storageInterface;
	
	public CharacterManager(CharacterPort i) {
		storageInterface = i;
		INSTANCE = this;
	}
	
	public static CharacterManager getThis() {
		return INSTANCE;
	}
}
