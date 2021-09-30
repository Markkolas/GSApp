package org.gloryseekers.aplication;

import org.gloryseekers.domain.Characters;
import org.gloryseekers.domain.Painters;
import org.gloryseekers.infra.CharacterXML;
import org.gloryseekers.infra.JavaFXPainter;

public class CharacterManager {

    private Painters painters;

    private Characters characters;

    private CharacterManager(Painters painters, Characters characters) {
        this.painters = painters;
        this.characters = characters;
        painters.start();
    }

    private static class CharacterManagerSingleton {
        private static final CharacterManager INSTANCE = new CharacterManager(new JavaFXPainter(), new CharacterXML()); 
    }

    public static CharacterManager getInstance() {
        return CharacterManagerSingleton.INSTANCE;
    }
    
}
