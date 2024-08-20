package Generators.EnemyGenerator;

import Character.Enemy.EnemyCategory;
import Fight.GameActions.EnemyAction;
import Game.GameCollection;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;

public class EnemyBase implements Cloneable{

    //Static part
    private static final Map<EnemyCategory,EnemyBase[]> BASES = Map.of(
            EnemyCategory.Minion, new EnemyBase[]{},
            EnemyCategory.Strong, new EnemyBase[]{},
            EnemyCategory.Boss, new EnemyBase[]{}
    );

    public static EnemyBase getBase(EnemyCategory category, int cost){
        EnemyBase[] bases = BASES.get(category);
        EnemyBase base = bases[GameCollection.random.nextInt(bases.length)];
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
