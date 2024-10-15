package Fight.GameActions;

import Character.Enemy.EnemyActionFactory;
import Fight.ActionTarget;
import Game.Tags;

import java.util.ArrayList;
import java.util.List;

public class ConstantEnemyActions {

    //Action strength
    private static final double weak = 0.2;
    private static final double medium = 0.3;
    private static final double strong = 0.5;
    private static final double veryStrong = 0.7;

    public static EnemyAction weakAttack = new EnemyAction(ActionTarget.PLAYER_CHARACTER, new Tags[]{Tags.ATTACK}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getStrength(), weak, false, 1))));
    public static EnemyAction attack = new EnemyAction(ActionTarget.PLAYER_CHARACTER, new Tags[]{Tags.ATTACK}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getStrength(), medium, false, 1))));
    public static EnemyAction strongAttack = new EnemyAction(ActionTarget.PLAYER_CHARACTER, new Tags[]{Tags.ATTACK}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getStrength(), strong, false, 1))));
    public static EnemyAction deadlyAttack = new EnemyAction(ActionTarget.PLAYER_CHARACTER, new Tags[]{Tags.ATTACK}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getStrength(), veryStrong, false, 1))));

    public static EnemyAction badDefence = new EnemyAction(ActionTarget.ENEMY_CHARACTER, new Tags[]{Tags.DEFENCE}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getEndurance(), weak, true, 2))));
    public static EnemyAction defence = new EnemyAction(ActionTarget.ENEMY_CHARACTER, new Tags[]{Tags.DEFENCE}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getEndurance(), medium, true, 2))));
    public static EnemyAction invincibleDefence = new EnemyAction(ActionTarget.ENEMY_CHARACTER, new Tags[]{Tags.DEFENCE}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getEndurance(), veryStrong, true, 2))));

    public static EnemyAction weakRegen = new EnemyAction(ActionTarget.ENEMY_CHARACTER, new Tags[]{}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getIntelligence(), weak, true, 3))));
    public static EnemyAction regen = new EnemyAction(ActionTarget.ENEMY_CHARACTER, new Tags[]{}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getIntelligence(), medium, true, 3))));
    public static EnemyAction goodRegen = new EnemyAction(ActionTarget.ENEMY_CHARACTER, new Tags[]{}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getIntelligence(), strong, true, 3))));

    public static EnemyAction poison = new EnemyAction(ActionTarget.PLAYER_CHARACTER, new Tags[]{}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getCunning(), medium, false, 5))));
    public static EnemyAction deadlyPoison = new EnemyAction(ActionTarget.PLAYER_CHARACTER, new Tags[]{}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getCunning(), veryStrong, false, 5))));

    public static EnemyAction bleed = new EnemyAction(ActionTarget.PLAYER_CHARACTER, new Tags[]{}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getCunning(), medium, false, 6))));
    public static EnemyAction strongBleed = new EnemyAction(ActionTarget.PLAYER_CHARACTER, new Tags[]{}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getCunning(), strong, false, 6))));

    public static EnemyAction weakness = new EnemyAction(ActionTarget.PLAYER_CHARACTER, new Tags[]{}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getCunning(), medium, false, 7))));
    public static EnemyAction strongWeakness = new EnemyAction(ActionTarget.PLAYER_CHARACTER, new Tags[]{}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getCunning(), strong, false, 7))));
    public static EnemyAction deadlyWeakness = new EnemyAction(ActionTarget.PLAYER_CHARACTER, new Tags[]{}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getCunning(), veryStrong, false, 7))));

    public static EnemyAction weakCounter = new EnemyAction(ActionTarget.ENEMY_CHARACTER, new Tags[]{Tags.DEFENCE}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getCharisma(), weak, false, 8))));
    public static EnemyAction counter = new EnemyAction(ActionTarget.ENEMY_CHARACTER, new Tags[]{Tags.DEFENCE}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getCharisma(), medium, false, 8))));
    public static EnemyAction goodCounter = new EnemyAction(ActionTarget.ENEMY_CHARACTER, new Tags[]{Tags.DEFENCE}, new ArrayList<>(List.of(new EnemyActionFactory(e -> e.getCharisma(), strong, false, 8))));
}
