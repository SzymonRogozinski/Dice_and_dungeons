package Tests;

import Equipment.Items.ItemQuality;
import Game.Game;
import Game.*;
import Character.PlayerParty;
import Loot.LootSettings;
import Walking.WalkingLevel;
import Walking.WalkingSettings;

import java.util.ArrayList;
import java.util.List;

public class StoryGeneratorTest {

    public static void main(String[] args) {
        //Set General game
        new Game();

        try {
            //Mocks
            var party = new ArrayList<>(List.of(GameConst.START_CHARACTER.getFirst()));
            PlayerInfo.setParty(new PlayerParty(party, new ArrayList<>()));
            GameManager.getWalkingManager().setMap(new WalkingLevel(new GameLevel(
                    new LootSettings(3, new double[]{0.7, 0.3}), new LootSettings(5, new double[]{0.3, 0.6, 0.1}),
                    4, 10, new WalkingSettings(0, 2, 50, 50, 250, 2, 5,2, 2, ItemQuality.COMMON, 3, "Texture/MapTextures/dungeon/", false)
            )));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        GameManager.changeState(GameStates.WALKING);
    }
}
