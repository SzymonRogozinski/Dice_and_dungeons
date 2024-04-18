package Character;

public class GameCharacter {
    private int strength,endurance,intelligence,charisma,cunning,luck;
    private final int startStrength,startEndurance,startIntelligence,startCharisma,startCunning,startLuck;

    public GameCharacter(int startStrength, int startEndurance, int startIntelligence, int startCharisma, int startCunning, int startLuck) {
        this.startStrength = startStrength;
        strength = startStrength;
        this.startEndurance = startEndurance;
        endurance = startEndurance;
        this.startIntelligence = startIntelligence;
        intelligence = startIntelligence;
        this.startCharisma = startCharisma;
        charisma = startCharisma;
        this.startCunning = startCunning;
        cunning = startCunning;
        this.startLuck = startLuck;
        luck = startLuck;
    }

    public int getStrength() {
        return strength;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getCunning() {
        return cunning;
    }

    public int getLuck() {
        return luck;
    }
}
