
import GUI.MainFrame;
import GUI.WalkingGUI.WalkingKeyListener;
import GUI.WalkingGUI.WalkingView;
import Game.GameCollection;
import Walking.WalkingManager;

import javax.swing.*;

public class WalkingMain {

    private static WalkingView walkingView;
    private static final JFrame mainFrame=new MainFrame();

    public static void main(String[] args) {
        try {
            WalkingManager manager=new WalkingManager("config.txt");
            GameCollection.setWalkingManager(manager);
        }catch(Exception e){
            System.err.println(e.getMessage());
            return;
        }

        walkingView = new WalkingView();
        walkingView.addKeyListener(new WalkingKeyListener());

        mainFrame.add(walkingView);

        mainFrame.pack();
        mainFrame.setVisible(true);


        //Start
       GameCollection.getWalkingManager().getWalking().walkingStart();
//        walkingView.revalidate();
//        walkingView.repaint();
    }
}
