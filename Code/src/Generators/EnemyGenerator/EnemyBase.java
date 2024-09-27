package Generators.EnemyGenerator;

import Character.Enemy.EnemyCategory;
import Fight.GameActions.ConstantEnemyActions;
import Fight.GameActions.EnemyAction;
import Game.GameManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnemyBase implements Cloneable{

    //Static part
    private static final Map<EnemyCategory,EnemyBase[]> BASES = Map.of(
            EnemyCategory.Minion, new EnemyBase[]{
                    new EnemyBase(new int[]{10,10,10,10,10},EnemyCategory.Minion,"skeleton",new ImageIcon("Texture/Enemy/skeleton.png"),new ArrayList<>(List.of(ConstantEnemyActions.attack)),1),
                    new EnemyBase(new int[]{10,20,10,10,10},EnemyCategory.Minion,"zombie",new ImageIcon("Texture/Enemy/zombie.png"),new ArrayList<>(List.of(ConstantEnemyActions.attack)),2),
                    new EnemyBase(new int[]{15,10,10,10,10},EnemyCategory.Minion,"wild dog",new ImageIcon("Texture/Enemy/wild_dog.png"),new ArrayList<>(List.of(ConstantEnemyActions.weakAttack,ConstantEnemyActions.badDefence)),1),
                    new EnemyBase(new int[]{15,10,10,10,20},EnemyCategory.Minion,"bat swarm",new ImageIcon("Texture/Enemy/bat_swarm.png"),new ArrayList<>(List.of(ConstantEnemyActions.strongWeakness)),2),
                    new EnemyBase(new int[]{15,10,10,15,10},EnemyCategory.Minion,"goblin",new ImageIcon("Texture/Enemy/goblin.png"),new ArrayList<>(List.of(ConstantEnemyActions.attack,ConstantEnemyActions.badDefence)),2),
                    new EnemyBase(new int[]{10,10,10,20,10},EnemyCategory.Minion,"giant spider",new ImageIcon("Texture/Enemy/giant_spider.png"),new ArrayList<>(List.of(ConstantEnemyActions.attack, ConstantEnemyActions.poison)),2),
                    new EnemyBase(new int[]{15,15,20,10,10},EnemyCategory.Minion,"goatman",new ImageIcon("Texture/Enemy/goatman.png"),new ArrayList<>(List.of(ConstantEnemyActions.attack,ConstantEnemyActions.weakRegen)),3),
                    new EnemyBase(new int[]{10,10,10,15,15},EnemyCategory.Minion,"orc",new ImageIcon("Texture/Enemy/orc.png"),new ArrayList<>(List.of(ConstantEnemyActions.attack,ConstantEnemyActions.counter)),2)
            },
            EnemyCategory.Strong, new EnemyBase[]{
                    new EnemyBase(new int[]{20,30,10,10,10},EnemyCategory.Strong,"giant slime",new ImageIcon("Texture/Enemy/giant_slime.png"),new ArrayList<>(List.of(ConstantEnemyActions.attack,ConstantEnemyActions.poison,ConstantEnemyActions.goodRegen,ConstantEnemyActions.poison)),1),
                    new EnemyBase(new int[]{30,20,10,10,10},EnemyCategory.Strong,"ogre",new ImageIcon("Texture/Enemy/ogre.png"),new ArrayList<>(List.of(ConstantEnemyActions.attack,ConstantEnemyActions.strongAttack,ConstantEnemyActions.strongAttack,ConstantEnemyActions.defence)),1),
                    new EnemyBase(new int[]{30,30,10,10,10},EnemyCategory.Strong,"troll",new ImageIcon("Texture/Enemy/troll.png"),new ArrayList<>(List.of(ConstantEnemyActions.strongAttack,ConstantEnemyActions.goodRegen)),2),
                    new EnemyBase(new int[]{20,40,10,10,10},EnemyCategory.Strong,"golem",new ImageIcon("Texture/Enemy/golem.png"),new ArrayList<>(List.of(ConstantEnemyActions.attack, ConstantEnemyActions.invincibleDefence, ConstantEnemyActions.strongAttack)),3)
            },
            EnemyCategory.Boss, new EnemyBase[]{
                    new EnemyBase(new int[]{40,40,20,20,20},EnemyCategory.Boss,"minotaur",new ImageIcon("Texture/Enemy/minotaur.png"),new ArrayList<>(List.of(ConstantEnemyActions.deadlyAttack,ConstantEnemyActions.invincibleDefence)),1),
                    new EnemyBase(new int[]{30,30,20,20,30},EnemyCategory.Boss,"dragon",new ImageIcon("Texture/Enemy/dragon.png"),new ArrayList<>(List.of(ConstantEnemyActions.attack,ConstantEnemyActions.strongBleed,ConstantEnemyActions.attack,ConstantEnemyActions.strongWeakness)),1),
                    new EnemyBase(new int[]{60,30,10,10,10},EnemyCategory.Boss,"giant",new ImageIcon("Texture/Enemy/giant.png"),new ArrayList<>(List.of(ConstantEnemyActions.deadlyAttack,ConstantEnemyActions.defence)),1),
                    new EnemyBase(new int[]{30,20,20,20,40},EnemyCategory.Boss,"demon",new ImageIcon("Texture/Enemy/demon.png"),new ArrayList<>(List.of(ConstantEnemyActions.attack, ConstantEnemyActions.deadlyWeakness, ConstantEnemyActions.attack, ConstantEnemyActions.deadlyPoison, ConstantEnemyActions.attack,ConstantEnemyActions.strongBleed)),1)
            }
    );

    public static EnemyBase getBase(EnemyCategory category, int cost){
        EnemyBase[] bases = BASES.get(category);
        EnemyBase base = bases[GameManager.random.nextInt(bases.length)];
        return base.cost>cost?getBase(category,cost):base;
    }

    //strength, endurance, intelligence, charisma, cunning;
    int[] stats;
    final EnemyCategory category;
    String name;
    ImageIcon image;
    ArrayList<EnemyAction> actions;
    ArrayList<EnemyAdjective> adjectives;
    final int cost;

    public EnemyBase(int[] stats, EnemyCategory category, String name, ImageIcon image, ArrayList<EnemyAction> actions, int cost) {
        this.stats = stats;
        this.category = category;
        this.name = name;
        this.image = image;
        this.actions=actions;
        adjectives=new ArrayList<>();
        this.cost=cost;
    }

    @Override
    protected Object clone() {
        return new EnemyBase(stats.clone(),category,name,image, (ArrayList<EnemyAction>) actions.clone(),cost);
    }
}
