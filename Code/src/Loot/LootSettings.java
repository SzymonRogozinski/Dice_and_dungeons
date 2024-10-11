package Loot;

import Game.GameManager;

public class LootSettings {

    private final int points;
    private final double[] probabilities;

    public LootSettings(int points, double[] probabilities) {
        this.points = points;
        double sum=0;
        for(double d:probabilities)
            sum+=d;
        if(sum!=1)
            throw new IllegalArgumentException("Probability does not sum up to 1!");
        this.probabilities = probabilities;
    }

    public int getPoints() {
        return points;
    }

    public int getQualityId(){
        double randomValue = GameManager.getRandom().nextDouble();
        double probability = 0;
        int i=0;
        for(;i<probabilities.length;i++){
            probability+=probabilities[i];
            if(randomValue<=probability)
                break;
        }

        return i+1;
    }
}
