package Character;

import java.util.ArrayList;

public class PlayerParty {

    private ArrayList<PlayerCharacter> characters;

    public PlayerParty(ArrayList<PlayerCharacter> characters) {
        this.characters = characters;
    }

    public ArrayList<PlayerCharacter> getCharacters() {
        return characters;
    }
}
