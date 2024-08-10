import Dice.DiceAction.DiceAction;
import Dice.DiceSide;
import Equipment.Items.*;
import Generators.*;

import java.util.Arrays;

public class GeneratorTest {

    public static void main(String[] args){
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
