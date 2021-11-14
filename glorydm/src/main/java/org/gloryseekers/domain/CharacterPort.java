package org.gloryseekers.domain;

import org.gloryseekers.domain.model.Character;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface CharacterPort {

    Character loadCharacter(File jsonFile) throws IOException;
    Map<Integer, Character> loadAllCharacters(File directory) throws IOException;
    void storeCharacter(Character c, String saveDirectoryPath) throws IOException;

}
