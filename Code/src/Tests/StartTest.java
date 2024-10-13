package Tests;

import GUI.MenuGUI.MenuModule;
import GUI.MenuGUI.MenuState;
import GUI.MenuGUI.MenuView;
import Game.GameManager;

import javax.swing.*;

public class StartTest {

    private static final JFrame mainFrame=new TestFrame();

    public static void main(String[] args){
        MenuView view = new MenuView();
        MenuState state = new MenuState(view);
        MenuModule module = new MenuModule(state);
        GameManager.setMenuModule(module);

        mainFrame.add(view);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
