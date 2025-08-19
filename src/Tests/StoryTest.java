package Tests;

import Dialog.DialogLine;
import Dialog.DialogQuestLine;
import Dialog.NPCLines;
import Equipment.Items.ItemQuality;
import Equipment.Items.UsableItem;
import Game.*;
import Character.PlayerParty;
import Generators.ItemGenerators.UsableItemGenerator;
import Quest.Quest;
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
            //Set Quests
            UsableItem item = UsableItemGenerator.generate(ItemQuality.COMMON);
            UsableItem item2 = UsableItemGenerator.generate(ItemQuality.COMMON);
            Quest q = new Quest("Test quest1","You must open chest", new ArrayList<>(List.of(item)));
            Quest q2 = new Quest("Test quest2","You must defeat enemies", new ArrayList<>(List.of(item2)));

            //Dialog
            NPCLines npc = new NPCLines("Bob",new DialogLine[]{
                    new DialogLine("Hi","Good evening", new int[]{1,3},true),                                                                 //0
                    new DialogQuestLine("Start quest1","Ok","",new int[]{2},true,q),                                              //1
                    new DialogQuestLine("End quest1","You do not finish quest","Well done",new int[]{},false,q),                  //2
                    new DialogQuestLine("Start quest2","Ok","",new int[]{4},true,q2),                                             //3
                    new DialogQuestLine("End quest2","You do not finish quest","Well done",new int[]{},false,q2)                  //4

//                    new DialogLine("Choose 2","2",new int[]{5},true),               //2
//                    new DialogLine("Choose 3 Multiple","M",new int[]{6},false),     //3
//                    new DialogLine("Choose 4","4",new int[]{},true),                //4
//                    new DialogLine("Choose 5 Multiple","m",new int[]{},false),      //5
//                    new DialogLine("Choose 6","6",new int[]{},true)                 //6
            });
            GameManager.getDialogModule().setPointedNPC(npc);

            GameManager.getWalkingManager().setMap(new WalkingLevel("src/Tests/story_map.json"));
            //Set NPC
            var npcPlace = new NPCPlace(GameManager.getWalkingManager().getWalking().getMap().getPath());
            npcPlace.setLines(npc);
            GameManager.getWalkingManager().getWalking().getMap().setTerrain(
                    npcPlace,
                    11,6
            );
            //Set chest
            QuestPlace questPlace = new QuestPlace(new TreasureGamePlace(GameManager.getWalkingManager().getWalking().getMap().getPath()),q);
            GameManager.getWalkingManager().getWalking().getMap().setTerrain(
                    questPlace,
                    6,8
            );
            //Set enemy
            //GameManager.getWalkingManager().getWalking().getEnemies().addQuestToEnemy(0,q2);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        GameManager.changeState(GameStates.WALKING);
    }
}
