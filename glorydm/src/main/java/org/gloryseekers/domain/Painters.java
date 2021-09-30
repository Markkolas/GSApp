package org.gloryseekers.domain;

public interface Painters {

    public abstract void start();

    public abstract Character requestCharacter(String name);

    public abstract boolean storeCharacter(Characters characters);
    
}
