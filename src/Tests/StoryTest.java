package Tests;

import Dialog.DialogLine;
import Dialog.DialogQuestLine;
import Dialog.NPCLines;
import Equipment.Items.ItemQuality;
import Equipment.Items.UsableItem;
import Game.*;
import Character.PlayerParty;
import Generators.ItemGenerators.UsableItemGenerator;
import Quest.*;
import Save.LevelLoader;
import Walking.Places.NPCPlace;
import Walking.Places.QuestPlace;
import Walking.Places.TreasureGamePlace;
import Walking.WalkingLevel;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class StoryTest {

    public static void main(String[] args) {
        //Set General game
        new Game();

        try {
            //Mocks
            var party = new ArrayList<>(List.of(GameConst.START_CHARACTER.getFirst()));
            PlayerInfo.setParty(new PlayerParty(party, new ArrayList<>()));

            LevelLoader.loadLevel("src/Tests/story_map.json");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        GameManager.changeState(GameStates.WALKING);
    }
}
