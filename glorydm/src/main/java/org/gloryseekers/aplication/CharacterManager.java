package org.gloryseekers.aplication;

import org.gloryseekers.domain.*;
import org.gloryseekers.infra.CharacterXML;

public class CharacterManager implements ManagementPort {

	private static CharacterManager instance;
	
	private CharacterPort characterPort;
	
	public CharacterManager(CharacterPort characterPort) {
		this.characterPort = characterPort;
	}
	
	public static CharacterManager getInstance() {
		return instance == null ? instance = new CharacterManager(new CharacterXML()) : instance;
	}
}
