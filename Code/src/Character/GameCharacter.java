package Character;

import Fight.Statuses.GameStatus;
import Fight.Statuses.StatusEvaporatedException;
import Fight.Statuses.StatusException;
import Fight.Tags;
import jdk.jshell.spi.ExecutionControl;

import javax.swing.*;
import java.util.ArrayList;

public class GameCharacter {
    private int strength,endurance,intelligence,charisma,cunning,luck,shield;
    private final int startStrength,startEndurance,startIntelligence,startCharisma,startCunning,startLuck;
    private double statisticMod,damageReceivingMod;
    private final ArrayList<GameStatus> statuses;
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
        this.shield=0;
        this.statuses=new ArrayList<>();
        this.statisticMod=1;
        this.damageReceivingMod=1;
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

    public void dealDamage(int damage) throws CharacterDieException {
        throw new RuntimeException("Method not implemented");
    }

    public void healDamage(int heal){throw new RuntimeException("Method not implemented");}

    public void gainMana(int mana){throw new RuntimeException("Method not implemented");}

    public void addShield(int shield){
        this.shield+=shield;
    }

    public void addStatus(GameStatus newStatus){
        for(GameStatus status:statuses){
            if(status.getClass().equals(newStatus.getClass())){
                status.addEffect(newStatus.getSumUpValue());
                return;
            }
        }
        statuses.add(newStatus);
    }

    public void statusEvaporate(){
        int i=0;
        while(i<statuses.size()){
            try {
                statuses.get(i).evaporate();
                i++;
            }catch (StatusEvaporatedException e){
                statuses.remove(i);
            }
        }
    }

    public void resetStatus(){
        statuses.clear();
    }

    public ArrayList<GameStatus> getStatuses() {
        return statuses;
    }

    public ArrayList<GameStatus> getStatusWithTag(Tags tag){
        ArrayList<GameStatus> result = new ArrayList<>();
        for(GameStatus status:statuses){
            if(status.haveTag(tag)) {
                result.add(status);
            }
        }
        return result;
    }

    public void resetMod(){
        this.statisticMod=1;
        this.damageReceivingMod=1;
    }

    public void multiplyMod(double statisticModChange,double damageReceivingModChange){
        this.statisticMod*=statisticModChange;
        this.damageReceivingMod*=damageReceivingModChange;
    }

    public double getStatisticMod() {
        return statisticMod;
    }

    public double getDamageReceivingMod() {
        return damageReceivingMod;
    }
}
