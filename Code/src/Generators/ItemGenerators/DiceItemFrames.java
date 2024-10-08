package Generators.ItemGenerators;

import Dice.ActionEnum;
import Fight.ActionTarget;
import Fight.GameActions.DiceLambda;
import Game.GameManager;

import javax.swing.*;

public class DiceItemFrames {

    private final static DiceItemFrame[] DICE_ITEM_FRAMES =new DiceItemFrame[]{
            new DiceItemFrame(new double[]{0,0,0.2,0.2,0.3,0.3}, ActionEnum.DAMAGE_ACTION,new String[]{"sword"},ActionTarget.ENEMY_CHARACTER,new int[]{ActionEnum.BLEEDING_ACTION,ActionEnum.WEAKNESS_ACTION},p->p.getDiceNumber(p.getStrength()),false, new ImageIcon("Texture/Items/attack_item/sword.png"),"strength"),
            new DiceItemFrame(new double[]{0,0.2,0.2,0.2,0.2,0.2}, ActionEnum.HEAL_ACTION,new String[]{"bag of healing"},ActionTarget.PLAYER_CHARACTER,new int[]{},p->p.getDiceNumber(p.getIntelligence()),false, new ImageIcon("Texture/Items/attack_item/bag_healing.png"),"intelligence"),
            new DiceItemFrame(new double[]{0,0,0.2,0.2,0.3,0.3}, ActionEnum.MANA_ACTION,new String[]{"magic sphere"},ActionTarget.PLAYER_CHARACTER,new int[]{},p->p.getDiceNumber(p.getIntelligence()),false, new ImageIcon("Texture/Items/attack_item/magic_sphere.png"),"intelligence"),
            new DiceItemFrame(new double[]{0,0.2,0.2,0.2,0.2,0.2}, ActionEnum.SHIELD_ACTION,new String[]{"shield"},ActionTarget.PLAYER_CHARACTER,new int[]{ActionEnum.HEAL_ACTION},p->p.getDiceNumber(p.getEndurance()),false, new ImageIcon("Texture/Items/attack_item/shield.png"),"endurance"),
            new DiceItemFrame(new double[]{0,0,0,0.25,0.25,0.5}, ActionEnum.BLEEDING_ACTION,new String[]{"trap"},ActionTarget.ENEMY_CHARACTER,new int[]{ActionEnum.WEAKNESS_ACTION, ActionEnum.POISON_ACTION},p->p.getDiceNumber(p.getCunning()),false, new ImageIcon("Texture/Items/attack_item/trap.png"),"cunning"),
            new DiceItemFrame(new double[]{0,0,0.2,0.2,0.2,0.4}, ActionEnum.COUNTER_ACTION,new String[]{"sword breaker"},ActionTarget.ENEMY_CHARACTER,new int[]{},p->p.getDiceNumber(p.getCharisma()),false, new ImageIcon("Texture/Items/attack_item/sword_breaker.png"),"charisma"),
            new DiceItemFrame(new double[]{0,0,0,0.33,0.33,0.33}, ActionEnum.POISON_ACTION,new String[]{"snake"},ActionTarget.ENEMY_CHARACTER,new int[]{ActionEnum.WEAKNESS_ACTION},p->p.getDiceNumber(p.getCunning()),false, new ImageIcon("Texture/Items/attack_item/snake.png"),"cunning"),
            new DiceItemFrame(new double[]{0,0,0.2,0.2,0.2,0.4}, ActionEnum.WEAKNESS_ACTION,new String[]{"bolas"},ActionTarget.ENEMY_CHARACTER,new int[]{},p->p.getDiceNumber(p.getCunning()),false, new ImageIcon("Texture/Items/attack_item/bolas.png"),"cunning"),
            new DiceItemFrame(new double[]{0,0.1,0.15,0.15,0.3,0.3}, ActionEnum.DAMAGE_ACTION,new String[]{"bow"},ActionTarget.ENEMY_CHARACTER,new int[]{ActionEnum.POISON_ACTION},p->p.getDiceNumber(p.getStrength()),false, new ImageIcon("Texture/Items/attack_item/bow.png"),"strength"),
            new DiceItemFrame(new double[]{0,0,0,0,0.5,0.5}, ActionEnum.DAMAGE_ACTION,new String[]{"axe"},ActionTarget.ENEMY_CHARACTER,new int[]{},p->p.getDiceNumber(p.getStrength()),false, new ImageIcon("Texture/Items/attack_item/axe.png"),"strength"),
            new DiceItemFrame(new double[]{0,0,0.2,0.2,0.2,0.4}, ActionEnum.DAMAGE_ACTION,new String[]{"morning star"},ActionTarget.ENEMY_CHARACTER,new int[]{ActionEnum.WEAKNESS_ACTION},p->p.getDiceNumber(p.getStrength()),false, new ImageIcon("Texture/Items/attack_item/morning_star.png"),"strength"),
            new DiceItemFrame(new double[]{0,0.15,0.15,0.15,0.15,0.4}, ActionEnum.POISON_ACTION,new String[]{"dart"},ActionTarget.ENEMY_CHARACTER,new int[]{ActionEnum.DAMAGE_ACTION,ActionEnum.WEAKNESS_ACTION},p->p.getDiceNumber(p.getCunning()),false, new ImageIcon("Texture/Items/attack_item/dart.png"),"cunning"),
            new DiceItemFrame(new double[]{0,0.2,0.2,0.2,0.2,0.2}, ActionEnum.COUNTER_ACTION,new String[]{"parrying dagger"},ActionTarget.PLAYER_CHARACTER,new int[]{ActionEnum.SHIELD_ACTION},p->p.getDiceNumber(p.getCunning()),true, new ImageIcon("Texture/Items/attack_item/parrying_dagger.png"),"cunning"),
            new DiceItemFrame(new double[]{0,0,0,0.3,0.3,0.4}, ActionEnum.SHIELD_ACTION,new String[]{"buckler"},ActionTarget.PLAYER_CHARACTER,new int[]{ActionEnum.COUNTER_ACTION},p->p.getDiceNumber(p.getCharisma()),true, new ImageIcon("Texture/Items/attack_item/buckler.png"),"charisma"),
            new DiceItemFrame(new double[]{0,0,0,0.2,0.4,0.4}, ActionEnum.MANA_ACTION,new String[]{"shaman charm"},ActionTarget.PLAYER_CHARACTER,new int[]{ActionEnum.SHIELD_ACTION},p->p.getDiceNumber(p.getIntelligence()),true, new ImageIcon("Texture/Items/attack_item/shaman_charm.png"),"intelligence"),
            new DiceItemFrame(new double[]{0,0,0,0.2,0.4,0.4}, ActionEnum.HEAL_ACTION,new String[]{"fairy"},ActionTarget.PLAYER_CHARACTER,new int[]{ActionEnum.MANA_ACTION},p->p.getDiceNumber(p.getCharisma()),false, new ImageIcon("Texture/Items/attack_item/fairy.png"),"charisma"),
            new DiceItemFrame(new double[]{0,0.2,0.2,0.2,0.2,0.2}, ActionEnum.BLEEDING_ACTION,new String[]{"pocket piranha"},ActionTarget.ENEMY_CHARACTER,new int[]{ActionEnum.POISON_ACTION},p->p.getDiceNumber(p.getCunning()),false, new ImageIcon("Texture/Items/attack_item/pocket_piranha.png"),"cunning"),
            new DiceItemFrame(new double[]{0,0.2,0.2,0.2,0.2,0.2}, ActionEnum.WEAKNESS_ACTION,new String[]{"smoke bomb"},ActionTarget.ENEMY_CHARACTER,new int[]{},p->p.getDiceNumber(p.getCunning()),false, new ImageIcon("Texture/Items/attack_item/smoke_bomb.png"),"cunning")
    };

    private final static DiceItemFrame[] SPELL_FRAMES =new DiceItemFrame[]{
            new DiceItemFrame(new double[]{0,0,0,0.2,0.4,0.4}, ActionEnum.DAMAGE_ACTION,new String[]{"thunder ball"},ActionTarget.ENEMY_CHARACTER,new int[]{},p->p.getDiceNumber(p.getIntelligence()),false,new ImageIcon("Texture/Items/spells/thunder_ball.png"),""),
            new DiceItemFrame(new double[]{0,0.2,0.2,0.2,0.2,0.2}, ActionEnum.DAMAGE_ACTION,new String[]{"earthquake"},ActionTarget.ENEMY_CHARACTER,new int[]{ActionEnum.WEAKNESS_ACTION},p->p.getDiceNumber(p.getIntelligence()),false,new ImageIcon("Texture/Items/spells/earthquake.png"),""),
            new DiceItemFrame(new double[]{0,0,0.16,0.16,0.33,0.33}, ActionEnum.WEAKNESS_ACTION,new String[]{"gender change"},ActionTarget.ENEMY_CHARACTER,new int[]{},p->p.getDiceNumber(p.getIntelligence()),false,new ImageIcon("Texture/Items/spells/gender_change.png"),""),
            new DiceItemFrame(new double[]{0.07,0.18,0.18,0.18,0.18,0.18}, ActionEnum.SHIELD_ACTION,new String[]{"void barrier"},ActionTarget.PLAYER_CHARACTER,new int[]{},p->p.getDiceNumber(p.getIntelligence()),true,new ImageIcon("Texture/Items/spells/void_barrier.png"),""),
            new DiceItemFrame(new double[]{0,0,0,0.2,0.4,0.4}, ActionEnum.HEAL_ACTION,new String[]{"goddess kiss"},ActionTarget.PLAYER_CHARACTER,new int[]{},p->p.getDiceNumber(p.getIntelligence()),false,new ImageIcon("Texture/Items/spells/goddess_kiss.png"),""),
            new DiceItemFrame(new double[]{0,0,0,0.33,0.33,0.33}, ActionEnum.POISON_ACTION,new String[]{"toxic flower"},ActionTarget.ENEMY_CHARACTER,new int[]{ActionEnum.WEAKNESS_ACTION},p->p.getDiceNumber(p.getIntelligence()),false,new ImageIcon("Texture/Items/spells/toxic_flower.png"),""),
            new DiceItemFrame(new double[]{0,0,0.16,0.16,0.33,0.33}, ActionEnum.BLEEDING_ACTION,new String[]{"falling spikes"},ActionTarget.ENEMY_CHARACTER,new int[]{ActionEnum.POISON_ACTION},p->p.getDiceNumber(p.getIntelligence()),false,new ImageIcon("Texture/Items/spells/falling_spikes.png"),""),
            new DiceItemFrame(new double[]{0,0.08,0.15,0.15,0.31,0.31}, ActionEnum.COUNTER_ACTION,new String[]{"ghost sword"},ActionTarget.PLAYER_CHARACTER,new int[]{ActionEnum.SHIELD_ACTION},p->p.getDiceNumber(p.getIntelligence()),true,new ImageIcon("Texture/Items/spells/ghost_sword.png"),"")
    };

    public static DiceItemBase getRandomDiceItemBase(int points){
        int i = GameManager.random.nextInt(DICE_ITEM_FRAMES.length);
        return DICE_ITEM_FRAMES[i].getDiceItemBase(points);
    }

    public static DiceItemBase getRandomSpellItemBase(int points){
        int i = GameManager.random.nextInt(SPELL_FRAMES.length);
        return SPELL_FRAMES[i].getDiceItemBase(points);
    }

    private static class DiceItemFrame {
        final double[] values;
        final int diceAction;
        final String[] names;
        final ActionTarget target;
        final int[] secondaryActionType;
        final DiceLambda diceLambda;
        final boolean actionOnSelf;
        final ImageIcon icon;
        final String attribute;

        DiceItemFrame(double[] values, int diceAction, String[] names, ActionTarget target,int[] secondaryActionType,DiceLambda diceLambda,boolean actionOnSelf,ImageIcon icon,String attribute) {
            this.values = values;
            this.diceAction = diceAction;
            this.names = names;
            this.target = target;
            this.secondaryActionType=secondaryActionType;
            this.diceLambda=diceLambda;
            this.actionOnSelf=actionOnSelf;
            this.icon=icon;
            this.attribute=attribute;
        }

        DiceItemBase getDiceItemBase(int points){
            //Count points
            int [] stats = new int[6];
            int count=0;
            for(int i=0;i<6;i++) {
                int s = (int) (values[i] * points);
                stats[i] = s;
                count+=s;
            }
            //Add rest
            int i=5;
            while(count<points){
                if(i<0 || stats[i]==0)
                    i=5;
                stats[i--]+=1;
                count++;
            }

            return new DiceItemBase(stats,diceAction,names,target,secondaryActionType,diceLambda,actionOnSelf,icon,attribute);
        }
    }
}
