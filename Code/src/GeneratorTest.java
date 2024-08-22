import Character.Enemy.EnemyCategory;
import Character.Enemy.EnemyCharacter;
import Dice.Dice;
import Dice.DiceAction.DiceAction;
import Dice.DiceFactory;
import Dice.DiceSide;
import Equipment.Items.*;
import Fight.ActionTarget;
import Game.GameBalance;
import Game.GameLevel;
import Game.Tags;
import Generators.EnemyGenerator.EnemyGenerator;
import Generators.ItemGenerators.*;
import Loot.LootModule;
import Loot.LootSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class GeneratorTest {

    public static void main(String[] args) throws IOException {
        int count =5;
        GameLevel level=GameBalance.DUNGEON;
        EnemyCategory category = EnemyCategory.Strong;
        var t = EnemyGenerator.generateMore(level.getEnemyStrength());
        for(int i=1;i<=t.size();i++){
            EnemyCharacter enemy = t.get(i-1);
            System.out.println("Trial: "+i);
            System.out.println(enemy.getName());
            System.out.println("HP: "+enemy.getMaxHealth());
            System.out.println("Strength: "+enemy.getStrength());
            System.out.println("Endurance: "+enemy.getEndurance());
            System.out.println("Intelligence: "+enemy.getIntelligence());
            System.out.println("Charisma: "+enemy.getCharisma());
            System.out.println("Cunning: "+enemy.getCunning());
            System.out.println();
        }
    }
}
