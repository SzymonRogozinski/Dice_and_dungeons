
import GUI.MainFrame;
import GUI.WalkingGUI.WalkingGUIState;
import GUI.WalkingGUI.WalkingKeyListener;
import GUI.WalkingGUI.WalkingView;
import Game.Game;
import Walking.WalkingModule;

import javax.swing.*;

public class WalkingMain {

    private static WalkingView walkingView;
    private static final JFrame mainFrame=new MainFrame();

    public static void main(String[] args) {
        mainFrame.addKeyListener(new WalkingKeyListener());
        walkingView = new WalkingView();
        WalkingGUIState state = new WalkingGUIState(walkingView);

        try {
            WalkingModule manager=new WalkingModule("config.txt", state);
            Game.setWalkingManager(manager);
        }catch(Exception e){
            System.err.println(e.getMessage());
            return;
        }

        mainFrame.add(walkingView);

        mainFrame.pack();
        mainFrame.setVisible(true);


        //Start
       Game.getWalkingManager().getWalking().walkingStart();
    }
}
