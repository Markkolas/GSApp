package org.gloryseekers.domain;

import java.io.IOException;
import java.util.Map;

import org.gloryseekers.domain.model.Character;
import org.gloryseekers.domain.model.Piece;
import org.gloryseekers.domain.model.gsdate.GSDate;

public interface ManagementPort {
	int addCharacter(short fort, short disc, float silver, String name, String ownerName); 
	Character readCharacter(int key);
	boolean modCharacter(short fort, short disc, float silver, String charName, String ownerName, int key);
	boolean rmCharacter(int key);
	boolean changeCharacter(int key, boolean newState);
	boolean addPiece(int key, Piece p);
	boolean rmPiece(int key, Piece p);
	boolean rmPiece(int key, String name);
	boolean doShortRest();
	boolean doLongRest();
	Map<Integer, Character> getCharactersMap();
	float getSavedSilver();
	GSDate getDate();
	public Character loadCharacter(String path);
    //public Map<Integer, Character> loadAllCharacters();
    public boolean storeCharacter(Character c);
}
