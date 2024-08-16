import Dice.Dice;
import Dice.DiceAction.DiceAction;
import Dice.DiceFactory;
import Dice.DiceSide;
import Equipment.Items.*;
import Fight.ActionTarget;
import Game.GameBalance;
import Game.Tags;
import Generators.*;
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
        LootModule loot = new LootModule();
        LootSettings settings = GameBalance.MINE.getLootSettings();
        for(int i=0;i<10;i++)
            loot.getLoot(settings);
    }
}
