package Tests;

import GUI.TestFrame;
import GUI.WalkingGUI.WalkingGUIState;
import GUI.WalkingGUI.WalkingKeyListener;
import GUI.WalkingGUI.WalkingView;
import Game.GameManager;
import Walking.WalkingModule;

import javax.swing.*;

public class WalkingTest {

    private static WalkingView walkingView;
    private static final JFrame mainFrame=new TestFrame();

    public static void main(String[] args) {
        mainFrame.addKeyListener(new WalkingKeyListener());
        walkingView = new WalkingView();
        WalkingGUIState state = new WalkingGUIState(walkingView);

        try {
            WalkingModule manager=new WalkingModule(state);
            GameManager.setWalkingManager(manager);
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
