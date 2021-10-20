package org.gloryseekers.domain;

import java.util.Map;

import org.gloryseekers.aplication.*;
import org.gloryseekers.domain.model.*;
import org.gloryseekers.domain.model.Character;

public interface ManagementPort {
	int addCharacter(short fort, short disc, float silver, String name); 
	Character readCharacter(int key);
	boolean modCharacter(short fort, short disc, float silver, String name, int key);
	boolean rmCharacter(int key);
	boolean changeCharacter(int key, boolean newState);
	public boolean addPiece(int key, Piece p);
	public boolean rmPiece(int key, Piece p);
	public Map<Integer, Character> getCharactersMap();
}
