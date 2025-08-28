package Save;

import Game.GameLevel;
import Game.GameManager;
import Loot.LootSettings;
import Walking.WalkingLevel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LevelLoader {

    public static void loadLevel(String filename) throws FileNotFoundException {
        //Read json
        JSONObject mapJson;
        try {
            mapJson = new JSONObject(Files.readString(Path.of(filename)));
        } catch (IOException e) {
            throw new FileNotFoundException("File not found!");
        }
        //Load loot settings
        int points = mapJson.getJSONObject("LootSettings").getInt("points");
        double[] probability = jsonArrayToDoubleArray(mapJson.getJSONObject("LootSettings").getJSONArray("probabilities"));
        LootSettings lootSettings = new LootSettings(points,probability);

        points = mapJson.getJSONObject("LootQuest").getInt("points");
        probability = jsonArrayToDoubleArray(mapJson.getJSONObject("LootQuest").getJSONArray("probabilities"));
        LootSettings lootQuest = new LootSettings(points,probability);
        //Load enemies stats
        int enemyCost = mapJson.getJSONObject("EnemiesStats").getInt("enemyCost");
        int minHP = mapJson.getJSONObject("EnemiesStats").getInt("minHP");

        GameManager.setLevel(new GameLevel(lootSettings, lootQuest, enemyCost, minHP, null));
        //Load level layout
        WalkingLevel level = new WalkingLevel(mapJson);
        GameManager.getWalkingManager().setMap(level);
    }

    private static double[] jsonArrayToDoubleArray(JSONArray array){
        double[] result = new double[array.length()];
        for (int i = 0; i < result.length; i++)
            result[i] = (double) array.get(i);
        return result;
    }
}
