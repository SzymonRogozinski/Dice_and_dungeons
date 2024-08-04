import Dice.DiceAction.DiceAction;
import Dice.DiceSide;
import Equipment.Items.ActionItem;
import Equipment.Items.ArmorItem;
import Equipment.Items.ItemQuality;
import Generators.ArmorGenerator;
import Generators.DiceItemBase;
import Generators.DiceItemGenerator;

import java.util.Arrays;

public class GeneratorTest {

    public static void main(String[] args){
        ItemQuality q = ItemQuality.RARE;
        int count=100;

        for(int i =0; i<count;i++) {
            System.out.println("Trial: "+i);
            ActionItem a = DiceItemGenerator.generateItem(q);
            System.out.println();
            System.out.println("Result");
            System.out.println("Name: "+a.name);
            System.out.println("Tags: "+ Arrays.toString(a.tags));
            System.out.println("Action Tags: "+ Arrays.toString(a.getAction().tags));
            StringBuilder sides= new StringBuilder();
            for (DiceSide side:a.getAction().getDice().getSides()){
                for (DiceAction action:side.getValue()){
                    String s = action.actionDescription();
                    sides.append(s.isEmpty()?"None":s);
                    sides.append(" ");
                }
                sides.append("| ");
            }
            sides.delete(sides.length()-3,sides.length());
            System.out.println("Dices: [ "+ sides +" ]");
            System.out.println();
        }
    }
}
