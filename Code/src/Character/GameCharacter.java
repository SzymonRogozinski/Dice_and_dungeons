package Character;

import javax.swing.*;

public class GameCharacter {
    private int strength,endurance,intelligence,charisma,cunning,luck;
    private final int startStrength,startEndurance,startIntelligence,startCharisma,startCunning,startLuck;
    private final String name;
    private final ImageIcon image;

    public GameCharacter(int startStrength, int startEndurance, int startIntelligence, int startCharisma, int startCunning, int startLuck,String name,ImageIcon image) {
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
        this.name=name;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public ImageIcon getImage(){
        return image;
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
