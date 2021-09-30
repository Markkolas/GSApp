package org.gloryseekers.aplication;

import org.gloryseekers.infra.CharacterXML;
import org.gloryseekers.infra.JavaFXPainter;

public class App {
    public static void main(String[] args) {
        CharacterManager characterManager = new CharacterManager(new JavaFXPainter(), new CharacterXML()); // maybe this can be loaded by spring later
    }
}
