import GUI.TestFrame;
import GUI.StartGUI.MenuModule;
import GUI.StartGUI.MenuState;
import GUI.StartGUI.StartView;
import Game.GameManager;

import javax.swing.*;

public class StartTest {

    private static final JFrame mainFrame=new TestFrame();

    public static void main(String[] args){
        StartView view = new StartView();
        MenuState state = new MenuState(view);
        MenuModule module = new MenuModule(state);
        GameManager.setMenuModule(module);

        mainFrame.add(view);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
