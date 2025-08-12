package Tests;

import Quest.Quest;
import Game.*;
import Character.PlayerParty;

import java.util.ArrayList;
import java.util.List;

public class StoryTest {

    public static void main(String[] args) {
        //Set General game
        new Game();

        try {
            //Mocks
            var party = new ArrayList<>(List.of(GameConst.START_CHARACTER.getFirst()));
            PlayerInfo.setParty(new PlayerParty(party, new ArrayList<>()));
            GameManager.getQuestModule().getQuests().add(new Quest(
                    "Test quest name 1",
                    "Test quest description"
            ));
            GameManager.getQuestModule().getQuests().add(new Quest(
                    "Test quest name 2",
                    "Test quest description Test quest description Test quest descriptionTest quest descriptionTest quest description"
            ));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        GameManager.changeState(GameStates.QUEST);
    }
}
