package Generators.ItemGenerators;

import Dice.ActionEnum;
import Dice.Dice;
import Dice.DiceFactory;
import Equipment.Items.ActionItem;
import Equipment.Items.ItemQuality;
import Fight.GameActions.ItemAction;
import Game.GameManager;
import Game.Tags;
import Generators.Generator;
import Generators.GeneratorConst;
import Generators.ItemGenerators.Dictionaries.ItemDictionary;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DiceItemGenerator extends Generator {

    private final static double CONCENTRATED_PROP = 0.9;
    private final static double REPLACE_EMPTY_SIDE_PROP = 0.5;
    private final static double EQUALITY_EDGE = 0.15;

    public static ActionItem generateItem(ItemQuality quality) {
        if (quality == null)
            throw new IllegalArgumentException("Quality of item cannot be null!");

        int points;
        Tags tag = null;

        switch (quality) {
            case COMMON -> points = getPoints(GeneratorConst.MEDIUM_POINTS * GeneratorConst.COMMON_MOD);
            case RARE -> {
                points = getPoints(GeneratorConst.MEDIUM_POINTS * GeneratorConst.RARE_MOD);
                //Add tag
                if (GameManager.getRandom().nextBoolean()) {
                    tag = getRandomTag();
                    points += GeneratorConst.TAG_BONUS * GeneratorConst.RARE_MOD;
                }
            }
            case LEGENDARY -> {
                points = getPoints(GeneratorConst.MEDIUM_POINTS * GeneratorConst.LEGENDARY_MOD) + GeneratorConst.TAG_BONUS * GeneratorConst.LEGENDARY_MOD;
                tag = getRandomTag();
            }
            default -> throw new RuntimeException("Quality not implemented");
        }

        int startPoints = points;
        int basePoints;
        // Item Concentrated
        if (quality == ItemQuality.COMMON)
            basePoints = points;
        else if (GameManager.getRandom().nextDouble() >= CONCENTRATED_PROP) {
            basePoints = points;
        } else
            basePoints = GeneratorConst.MEDIUM_POINTS * GeneratorConst.COMMON_MOD;
        points -= basePoints;
        DiceItemBase base = DiceItemFrames.getRandomDiceItemBase(basePoints);
        boolean generationLocked = false;
        while (points > 0) {
            //Too little points to other action
            if (generationLocked || points < (int) (startPoints * EQUALITY_EDGE)) {
                redistributePointsEqual(base, points);
                points = 0;
            } else if (base.haveEmptySide && GameManager.getRandom().nextDouble() < REPLACE_EMPTY_SIDE_PROP) {
                points -= replaceEmptySide(base, points);
            } else if (base.secondaryActionList.length > 0) {
                base.secondActionValues = new int[6];
                getRandomAction(base);
                addActionRandomly(base, points);
                points = 0;
            }
            if (!base.haveEmptySide && base.secondaryActionList.length == 0)
                generationLocked = true;
        }
        Tags[] tags = tag == null ? new Tags[]{} : new Tags[]{tag};
        base.tags = new ArrayList<>(List.of(tags));
        String shortName = base.names[GameManager.getRandom().nextInt(base.names.length)];
        String name = ItemDictionary.getItemNameFromItemBase(base, shortName);
        Dice dice = DiceFactory.buildDice(base);
        Tags[] actionTags = ItemDictionary.getTagsFromAction(base.firstAction, base.secondAction);

        ItemAction action = new ItemAction(dice, base.target, base.diceLambda, actionTags);
        ImageIcon icon = base.icon;
        String attr = base.attribute;

        return new ActionItem(action, tags, icon, name, shortName, quality, attr);
    }

    private static void addActionRandomly(DiceItemBase base, int points) {
        int bound = base.firstActionValues[0] == 0 ? 5 : 4;
        int roll = GameManager.getRandom().nextInt(bound);
        switch (roll) {
            case 0 -> addEffectEqualAll(base, points);
            case 1 -> addEffectEqualFullSides(base, points);
            case 2 -> addEffectProportional(base, points);
            case 3 -> addEffectInverselyProportional(base, points);
            case 4 -> addEffectEqualEmptySides(base, points);
        }
    }

    private static void addEffectEqualAll(DiceItemBase base, int points) {
        int sidePoints = points / 6;
        for (int i = 0; i < 6; i++)
            base.secondActionValues[i] = sidePoints;
        points -= (sidePoints * 6);
        giveAwayPoints(base.secondActionValues, points, true);
    }

    private static void addEffectEqualEmptySides(DiceItemBase base, int points) {
        int index = findFirstEmptySide(base.firstActionValues);
        int sidePoints = points / (index + 1);
        for (int i = 0; i < (index + 1); i++)
            base.secondActionValues[i] = sidePoints;
        points -= (sidePoints * (index + 1));
        giveAwayPoints(base.secondActionValues, points, true);
    }

    private static void addEffectEqualFullSides(DiceItemBase base, int points) {
        int index = findFirstEmptySide(base.firstActionValues);
        int sidePoints = points / (5 - index);
        for (int i = index + 1; i < 6; i++)
            base.secondActionValues[i] = sidePoints;
        points -= (sidePoints * (5 - index));
        giveAwayPoints(base.secondActionValues, points, true);
    }

    private static void addEffectProportional(DiceItemBase base, int points) {
        int index = findFirstEmptySide(base.firstActionValues);
        int sum = 0;
        for (int i = index + 1; i < 6; i++)
            sum += base.firstActionValues[i];

        int pointsCopy = points;
        for (int i = index + 1; i < 6; i++) {
            //Calculate proportional value
            int tmp = (int) (((double) (base.firstActionValues[i]) / sum) * pointsCopy);
            base.secondActionValues[i] = tmp;
            points -= tmp;
        }
        giveAwayPoints(base.secondActionValues, points, true);
    }

    private static void addEffectInverselyProportional(DiceItemBase base, int points) {
        int index = findFirstEmptySide(base.firstActionValues);
        int sum = 0;
        for (int i = index + 1; i < 6; i++)
            sum += base.firstActionValues[i];

        //Invert proportion
        double[] prop = new double[6];
        double iSum = 0;
        for (int i = index + 1; i < 6; i++) {
            double tmp = Math.pow((double) base.firstActionValues[i] / sum, -1);
            prop[i] = tmp;
            iSum += tmp;
        }
        int pointsCopy = points;
        for (int i = index + 1; i < 6; i++) {
            //Calculate proportional value
            int tmp = (int) ((prop[i] / iSum) * pointsCopy);
            base.secondActionValues[i] = tmp;
            points -= tmp;
        }
        giveAwayPoints(base.secondActionValues, points, true);
    }

    private static void redistributePointsEqual(DiceItemBase base, int points) {
        boolean isFirstValue = true;
        while (points > 0) {
            points = giveAwayPoints(isFirstValue ? base.firstActionValues : base.secondActionValues, points, false);
            //If base use second action, change focused action
            if (base.secondAction != ActionEnum.NULL_ACTION)
                isFirstValue = !isFirstValue;
        }
    }

    private static int replaceEmptySide(DiceItemBase base, int points) {
        int index = findFirstEmptySide(base.firstActionValues);
        if (index == -1) {
            base.haveEmptySide = false;
            return 0;
        }
        int put = Math.min(points, base.firstActionValues[index + 1]);
        base.firstActionValues[index] = put;
        return put;
    }

    private static void getRandomAction(DiceItemBase base) {
        if (base.secondaryActionList.length == 0)
            return;
        base.secondAction = base.secondaryActionList[GameManager.getRandom().nextInt(base.secondaryActionList.length)];
        if (base.secondAction == base.firstAction)
            getRandomAction(base);
    }

    private static int findFirstEmptySide(int[] array) {
        int index;
        for (index = 5; index >= 0; index--) {
            if (array[index] == 0)
                break;
        }
        return index;
    }

    private static int giveAwayPoints(int[] array, int points, boolean isLooped) {

        int goBackIndex = -1;
        for (int i = 5; i >= 0; i--) {
            if (array[i] > 0) {
                goBackIndex = i;
                break;
            }
        }
        if (goBackIndex < 0)
            goBackIndex = 5;

        int i = 5;
        while (points > 0) {
            //If out of range or empty side, return index to top
            if (i < 0 || (array[i] == 0)) {
                if (!isLooped)
                    break;
                else
                    i = goBackIndex;
            }
            array[i--]++;
            points--;
        }
        return points;
    }

}
