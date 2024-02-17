import Dice.Dice;
import Dice.DiceAction.DiceAction;
import Dice.DicePool;
import Dice.DiceAction.DamageAction;
import Dice.DiceAction.NullAction;
import Dice.DiceSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        DamageAction dmg=new DamageAction(4);
        DamageAction bigdmg=new DamageAction(6);
        NullAction none=new NullAction();

        DiceSide dmgSide=new DiceSide(new ArrayList<>(List.of(dmg)));
        DiceSide bigdmgSide=new DiceSide(new ArrayList<>(List.of(bigdmg)));
        DiceSide noneSide=new DiceSide(new ArrayList<>(List.of(none)));

        Dice dice=new Dice(new DiceSide[]{noneSide,noneSide,noneSide,dmgSide,dmgSide,bigdmgSide});

        DicePool pool=new DicePool(dice,6);
        pool.roll();
        int reroll=2;

        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(reroll>0){
            ArrayList<DiceSide> results=pool.getRollResult();
            for(int i=0;i< results.size();i++){
                System.out.println((i+1)+": "+results.get(i).getIcon());
            }

            System.out.println("Reroll dice? Yoy have "+reroll+" rerolls. Write 0, to skip or write dice number,to reroll it.");
            // Reading data using readLine
            String numberString = reader.readLine();
            try {
                int number = Integer.parseInt(numberString);
                if(number<=0 || number>results.size()){
                    break;
                }else{
                    pool.reroll(number-1);
                    reroll--;
                }
            }catch(Exception ignore){
                break;
            }
        }
        ArrayList<DiceSide> results=pool.getRollResult();
        for(int i=0;i< results.size();i++){
            System.out.println((i+1)+": "+results.get(i).getIcon());
        }
        System.out.println("Result is:");
        ArrayList<DiceAction> resultActions=pool.sumUp();
        for(DiceAction act:resultActions){
            System.out.println(act.action());
        }

    }
}
