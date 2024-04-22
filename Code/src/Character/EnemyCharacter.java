package Character;

import javax.swing.*;

public class EnemyCharacter extends GameCharacter{

    public EnemyCharacter(int startStrength, int startEndurance, int startIntelligence, int startCharisma, int startCunning, int startLuck, String name, ImageIcon image) {
        super(startStrength, startEndurance, startIntelligence, startCharisma, startCunning, startLuck,name,image);
    }

    public void action(){
        System.out.println("Enemy attack!");
    }
}
