package org.gloryseekers.domain;

import org.gloryseekers.domain.model.Character;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Collection;

public interface CharacterPort {

    Character loadCharacter(String absolutePath) throws IOException;
    Map<Integer, Character> loadAllCharacters(String loadDirectoryPath) throws IOException;
    void storeCharacter(Character c, String saveDirectoryPath) throws IOException;
    void storeAllCharacters(Collection<Character> collection, String saveDirectoryPath) throws IOException;
}
