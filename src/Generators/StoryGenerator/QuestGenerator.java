package Generators.StoryGenerator;

import Equipment.Items.Item;
import Game.GameManager;
import Loot.LootModule;
import Loot.LootSettings;
import Quest.*;

import java.util.ArrayList;

public class QuestGenerator {

    private static QuestType[] types = new QuestType[]{QuestType.CHEST_OPEN, QuestType.ENEMY_DEFEAT};

    public static Quest generateQuest(LootSettings settings){
        return generateQuest(types[GameManager.getRandom().nextInt(types.length)], settings);
    }

    public static Quest generateQuest(QuestType questType, LootSettings settings){
        String questName="";
        String questDesc="";
        switch (questType){
            case CHEST_OPEN -> {
                questName="Find chest";
                questDesc="Find chest, open it and return.";
            }
            case ENEMY_DEFEAT -> {
                questName="Kill enemy";
                questDesc="Find enemies, defeat them and return";
            }
            case null, default -> throw new RuntimeException("Error while generating quest. QuestType cannot be null.");
        }

        ArrayList<Item> rewards = LootModule.generateLoot(settings);

        return new Quest(questName,questDesc,questType,rewards);
    }
}
