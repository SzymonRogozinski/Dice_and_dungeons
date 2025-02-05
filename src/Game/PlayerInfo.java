package Game;

import Character.PlayerParty;

public class PlayerInfo {

    private static int keyCollected = 0;
    private static PlayerParty party;

    public static int getKeyCollected() {
        return keyCollected;
    }

    public static void collectKey() {
        keyCollected++;
    }

    public static boolean useKey() {
        if (keyCollected > 0) {
            keyCollected--;
            return true;
        }
        return false;
    }

    public static PlayerParty getParty() {
        return party;
    }

    public static void setParty(PlayerParty party) {
        if (PlayerInfo.party == null)
            PlayerInfo.party = party;
    }
}
