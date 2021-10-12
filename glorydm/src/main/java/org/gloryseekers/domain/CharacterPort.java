package org.gloryseekers.domain;

import org.gloryseekers.domain.model.Character;

public interface CharacterPort {

    public abstract Character loadCharacter(String name);

    public abstract boolean storeCharacter(Character character);

}
