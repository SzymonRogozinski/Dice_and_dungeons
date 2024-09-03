import GUI.MainFrame;
import GUI.StartGUI.MenuModule;
import GUI.StartGUI.MenuState;
import GUI.StartGUI.StartView;
import Game.Game;

import javax.swing.*;

public class StartTest {

    private static final JFrame mainFrame=new MainFrame();

    public static void main(String[] args){
        StartView view = new StartView();
        MenuState state = new MenuState(view);
        MenuModule module = new MenuModule(state);
        Game.setMenuModule(module);

        mainFrame.add(view);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
