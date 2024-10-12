package Tests;

import GUI.TestFrame;
import GUI.WalkingGUI.WalkingGUIState;
import GUI.WalkingGUI.WalkingKeyListener;
import GUI.WalkingGUI.WalkingView;
import Game.GameManager;
import Game.PlayerInfo;
import Loot.LootModule;
import Walking.WalkingModule;
import Character.PlayerParty;

import javax.swing.*;
import java.util.ArrayList;

public class WalkingTest {

    private static final JFrame mainFrame=new TestFrame();

    public static void main(String[] args) {
        WalkingView walkingView = new WalkingView();
        WalkingGUIState state = new WalkingGUIState(walkingView);

        try {
            GameManager.setWalkingManager(new WalkingModule(state));
            //Mocks
            PlayerInfo.setParty(new PlayerParty(new ArrayList<>(),new ArrayList<>()));
            GameManager.setLoot(new LootModule());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return;
        }
        mainFrame.add(walkingView);
        mainFrame.pack();
        mainFrame.setVisible(true);

        //Start
       GameManager.getWalkingManager().getWalking().walkingStart();
    }
}
