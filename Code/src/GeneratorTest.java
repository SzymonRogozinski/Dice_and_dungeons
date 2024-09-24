import Character.Enemy.EnemyCategory;
import Character.Enemy.EnemyCharacter;
import Equipment.Items.ItemQuality;
import Game.GameBalance;
import Game.GameConst;
import Game.GameLevel;
import Generators.EnemyGenerator.EnemyGenerator;
import Generators.ItemGenerators.ArmorGenerator;
import Generators.ItemGenerators.DiceItemGenerator;
import Generators.ItemGenerators.SpellItemGenerator;
import Generators.ItemGenerators.UsableItemGenerator;

import java.io.IOException;

public class GeneratorTest {

    public static void main(String[] args) throws IOException {
        int count =1000;
        int cost = 4;

        //Armor test
        System.out.println("Armor tests");
        for(ItemQuality q:ItemQuality.values()){
            int i=1;
            try {
                for (; i <= count; i++) {
                    ArmorGenerator.generateArmor(q);
                }
            }catch (Exception e){
                System.err.println(i);
                System.err.printf("Armor test. Quality %s\n",q);
                System.err.printf("Exception: %s\n",e.getMessage());
            }
        }

        //Dice item test
        System.out.println("Dice item tests");
        for(ItemQuality q:ItemQuality.values()){
            int i=1;
            try {
                for (; i <= count; i++) {
                    DiceItemGenerator.generateItem(q);
                }
            }catch (Exception e){
                System.err.println(i);
                System.err.printf("Dice item test. Quality %s\n",q);
                System.err.printf("Exception: %s\n",e.getMessage());
            }
        }

        //Usable item test
        System.out.println("Usable item tests");
        for(ItemQuality q:ItemQuality.values()){
            int i=1;
            try {
                for (; i <= count; i++) {
                    UsableItemGenerator.generate(q);
                }
            }catch (Exception e){
                System.err.println(i);
                System.err.printf("Usable item test. Quality %s\n",q);
                System.err.printf("Exception: %s\n",e.getMessage());
            }
        }

        //Spell test
        System.out.println("Spell tests");
        for(ItemQuality q:ItemQuality.values()){
            int i=1;
            try {
                for (; i <= count; i++) {
                    SpellItemGenerator.generateItem(q);
                }
            }catch (Exception e){
                System.err.println(i);
                System.err.printf("Spell item test. Quality %s\n",q);
                System.err.printf("Exception: %s\n",e.getMessage());
            }
        }

        //Enemy test
        System.out.println("Enemy tests");
        for(EnemyCategory category: EnemyCategory.values()){
            int i=1;
            try {
                for (; i <= count; i++) {
                    EnemyGenerator.generate(category,cost);
                }
            }catch (Exception e){
                System.err.println(i);
                System.err.printf("Enemy test. Category %s\n",category);
                System.err.printf("Exception: %s\n",e.getMessage());
            }
        }
    }
}
