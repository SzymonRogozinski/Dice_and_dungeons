package main;

import GUI.RollPanel;
import GUI.DicePanel;
import GUI.MainPanel;

public class FightModule {
    private final DiceMaster master;
    private final MainPanel panel;

    public FightModule(MainPanel panel) {
        this.panel=panel;
        this.master = new DiceMaster(this);
        this.panel.setDiceMaster(master);
        this.panel.setFightModule(this);
    }

    public DicePanel getDicePanel(){
        return panel.getDicePanel();
    }

    public RollPanel getControlPanel(){
        return panel.getRollPanel();
    }

    public void hideRollPanel(){
        panel.getRollPanel().setVisible(false);
    }
}
