import Dice.Dice;
import Dice.DiceAction.DiceAction;
import Dice.DiceFactory;
import Dice.DiceSide;
import Equipment.Items.*;
import Fight.ActionTarget;
import Game.Tags;
import Generators.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class GeneratorTest {

    public static void main(String[] args) throws IOException {
//        Dice d = DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,4},{1,4,7,3},{1,6}});
//        var s =d.getSides()[4].getIcon();
//        BufferedImage bi = new BufferedImage(s.getIconWidth(),s.getIconHeight(),BufferedImage.TYPE_INT_RGB);
//        Graphics2D g = bi.createGraphics();
//        g.drawImage(s.getImage(),0,0,null);
//        g.dispose();
//        ImageIO.write(bi,"png", new File("test.png"));

        ItemQuality q = ItemQuality.LEGENDARY;
        int count=10;
        UsableItem[] list = new UsableItem[count];
        for(int i=0; i<count;i++)
            list[i]= UsableItemGenerator.generate(q);

        for(int i =0; i<count;i++) {
            System.out.println("Trial: "+i);
            UsableItem a = list[i];
            System.out.println();
            System.out.println("Result");
            System.out.println("Name: "+a.name);
            System.out.println("Count: "+a.getNumberOfItems());
            System.out.println("Tags: "+ Arrays.toString(a.tags));
            System.out.println("Action Tags: "+ Arrays.toString(a.getAction().tags));
            StringBuilder sides= new StringBuilder();
            for (DiceAction action:a.getAction().getActionFactories()){
                    String s = action.actionDescription();
                    sides.append(s.isEmpty()?"None":s);
                    sides.append(" ");
                    sides.append("| ");
            }
            sides.delete(sides.length()-3,sides.length());
            System.out.println("Dices: [ "+ sides +" ]");
            System.out.println();
        }
    }
}
