package org.gloryseekers.domain;

import org.gloryseekers.domain.model.Character;
import java.io.IOException;
import java.util.Map;

public interface CharacterPort {

    Character loadCharacter(String fileName) throws IOException;
    Map<Integer, Character> loadAllCharacters() throws IOException;
    void storeCharacter(Character c) throws IOException;

}
