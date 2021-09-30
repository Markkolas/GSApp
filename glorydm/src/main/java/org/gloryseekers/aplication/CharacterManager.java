package org.gloryseekers.aplication;

import org.gloryseekers.domain.Characters;
import org.gloryseekers.domain.Painters;

public class CharacterManager {

    private Painters painters;

    private Characters characters;

    public CharacterManager(Painters painters, Characters characters) {
        this.painters = painters;
        this.characters = characters;
        painters.start();
    }
    
}
