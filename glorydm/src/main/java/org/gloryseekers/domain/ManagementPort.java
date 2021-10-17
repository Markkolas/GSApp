package org.gloryseekers.domain;

import org.gloryseekers.aplication.*;
import org.gloryseekers.domain.model.*;
import org.gloryseekers.domain.model.Character;

public interface ManagementPort {
	boolean addCharacter(short fort, short disc, float silver, String name); 
	Character readCharacter(int key);
	boolean modCharacter(short fort, short disc, float silver, String name, int key);
	boolean rmCharacter(int key);
}
