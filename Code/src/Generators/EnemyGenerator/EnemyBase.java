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
                    new EnemyBase(new int[]{10,20,10,10,10},EnemyCategory.Minion,"zombie",new ImageIcon("Texture/Enemy/zombie.png"),new ArrayList<>(List.of(ConstantEnemyActions.attack)),2)
            },
            EnemyCategory.Strong, new EnemyBase[]{
                    new EnemyBase(new int[]{20,20,10,10,20},EnemyCategory.Strong,"monster",new ImageIcon("Texture/Enemy/monster.png"),new ArrayList<>(List.of(ConstantEnemyActions.attack)),1)
            },
            EnemyCategory.Boss, new EnemyBase[]{
                    new EnemyBase(new int[]{30,30,20,20,20},EnemyCategory.Boss,"minotaur",new ImageIcon("Texture/Enemy/minotaur.png"),new ArrayList<>(List.of(ConstantEnemyActions.attack)),1)
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
