package org.gloryseekers.domain;

import org.gloryseekers.domain.model.Character;
import java.io.IOException;
import java.util.Map;

public interface CharacterPort {

    Character loadCharacter(String absolutePath) throws IOException;
    Map<Integer, Character> loadAllCharacters(String loadDirectoryPath) throws IOException;
    void storeCharacter(Character c, String saveDirectoryPath) throws IOException;

}
