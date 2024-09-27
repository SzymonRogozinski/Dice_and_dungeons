package Generators.EnemyGenerator;

import Fight.GameActions.ConstantEnemyActions;
import Fight.GameActions.EnemyAction;
import Game.GameBalance;
import Game.GameManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnemyAdjective {

    //Static part
    private static final ArrayList<EnemyAdjective> ADJECTIVES = new ArrayList<>(Arrays.asList(
            new EnemyAdjective(new int[]{10,0,0,0,0},"strong",null,1),
            new EnemyAdjective(new int[]{0,10,0,0,0},"vital",null,1),
            new EnemyAdjective(new int[]{0,0,10,0,0},"wisdom",null,1),
            new EnemyAdjective(new int[]{0,0,0,10,0},"convincing",null,1),
            new EnemyAdjective(new int[]{0,0,0,0,10},"canny",null,1),

            new EnemyAdjective(new int[]{20,0,0,0,0},"overpower",null,2),
            new EnemyAdjective(new int[]{0,20,0,0,0},"hard-bitten",null,2),
            new EnemyAdjective(new int[]{0,0,20,0,0},"omniscient",null,2),
            new EnemyAdjective(new int[]{0,0,0,20,0},"charismatic",null,2),
            new EnemyAdjective(new int[]{0,0,0,0,20},"treacherous",null,2),

            new EnemyAdjective(new int[]{10,10,0,0,0},"warlike",null,2),
            new EnemyAdjective(new int[]{0,0,0,10,10},"eloquent",null,2),
            new EnemyAdjective(new int[]{20,10,0,0,0},"empowered",null,3),
            new EnemyAdjective(new int[]{0,0,10,10,10},"devilish",null,3),
            new EnemyAdjective(new int[]{10,10,10,10,10},"omnipotent",null,5),

            new EnemyAdjective(null,"brave",new ArrayList<>(List.of(ConstantEnemyActions.strongAttack)),1),
            new EnemyAdjective(null,"dangerous",new ArrayList<>(List.of(ConstantEnemyActions.deadlyAttack)),2),

            new EnemyAdjective(null,"dodgy",new ArrayList<>(List.of(ConstantEnemyActions.defence)),1),
            new EnemyAdjective(null,"invincible",new ArrayList<>(List.of(ConstantEnemyActions.invincibleDefence)),2),

            new EnemyAdjective(null,"vivid",new ArrayList<>(List.of(ConstantEnemyActions.regen)),1),
            new EnemyAdjective(null,"regenerative",new ArrayList<>(List.of(ConstantEnemyActions.goodRegen)),2),

            new EnemyAdjective(null,"venom",new ArrayList<>(List.of(ConstantEnemyActions.poison)),1),
            new EnemyAdjective(null,"toxic",new ArrayList<>(List.of(ConstantEnemyActions.deadlyPoison)),2),

            new EnemyAdjective(null,"bloody",new ArrayList<>(List.of(ConstantEnemyActions.bleed)),1),
            new EnemyAdjective(null,"bloodthirsty",new ArrayList<>(List.of(ConstantEnemyActions.strongBleed)),2),

            new EnemyAdjective(null,"cheater",new ArrayList<>(List.of(ConstantEnemyActions.weakness)),1),
            new EnemyAdjective(null,"trickster",new ArrayList<>(List.of(ConstantEnemyActions.deadlyWeakness)),2),

            new EnemyAdjective(null,"quick",new ArrayList<>(List.of(ConstantEnemyActions.counter)),1),
            new EnemyAdjective(null,"reactionary",new ArrayList<>(List.of(ConstantEnemyActions.goodCounter)),2),

            new EnemyAdjective(null,"versatile",new ArrayList<>(List.of(ConstantEnemyActions.weakAttack,ConstantEnemyActions.badDefence,ConstantEnemyActions.weakRegen,ConstantEnemyActions.weakCounter)),1)
    ));

    public static EnemyAdjective getAdjective(int cost){
        EnemyAdjective adjective = ADJECTIVES.get(GameManager.random.nextInt(ADJECTIVES.size()));
        return adjective.cost>cost?getAdjective(cost):adjective;
    }

    private final int[] stats;
    public final String name;
    private final ArrayList<EnemyAction> actions;
    public final int cost;

    public EnemyAdjective(int[] stats, String name, ArrayList<EnemyAction> actions, int cost) {
        this.stats = stats;
        this.name = name;
        this.actions = actions;
        this.cost = cost;
    }

    public void modifyEnemy(EnemyBase enemy){
        if(stats!=null){
            for(int i=0;i<5;i++) {
                enemy.stats[i] += stats[i];
                if(enemy.stats[i] > GameBalance.MAX_ENEMY_STAT)
                    enemy.stats[i] = GameBalance.MAX_ENEMY_STAT;
            }
        }
        if(actions!=null)
            enemy.actions.addAll(actions);
    }
}
