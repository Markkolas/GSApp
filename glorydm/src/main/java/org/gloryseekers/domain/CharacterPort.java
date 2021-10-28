package org.gloryseekers.domain;

import org.gloryseekers.domain.model.Character;
import java.io.IOException;

public interface CharacterPort {

    public Character loadCharacter(String path) throws IOException;
    //public Map<Integer, Character> loadAllCharacters() throws IOException;
    public boolean storeCharacter(Character c) throws IOException;

}
