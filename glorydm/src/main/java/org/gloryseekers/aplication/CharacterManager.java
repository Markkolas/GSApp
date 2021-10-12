package org.gloryseekers.aplication;

import org.gloryseekers.domain.Characters;
import org.gloryseekers.domain.Painters;
import org.gloryseekers.infra.CharacterXML;
import org.gloryseekers.infra.UI;

public class CharacterManager {

    private static class CharacterManagerSingleton {
        private static final CharacterManager INSTANCE = new CharacterManager(); 
    }

    public static CharacterManager getInstance() {
        return CharacterManagerSingleton.INSTANCE;
    }
    
}
