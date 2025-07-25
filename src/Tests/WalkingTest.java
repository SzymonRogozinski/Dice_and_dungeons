package Tests;

import Character.PlayerParty;
import Game.*;
import Game.GameManager;
import Game.PlayerInfo;
import Loot.LootModule;

import java.util.ArrayList;

public class WalkingTest {



    public static void main(String[] args) {
        //Set General game
        new Game();

        try {
            //Mocks
            PlayerInfo.setParty(new PlayerParty(new ArrayList<>(), new ArrayList<>()));
            GameManager.setLoot(new LootModule());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        GameManager.changeState(GameStates.WALKING);
    }
}
