import GUI.MainFrame;
import GUI.StartGUI.StartPanel;

import javax.swing.*;

public class StartTest {

    private static final JFrame mainFrame=new MainFrame();

    public static void main(String[] args){
        StartPanel panel = new StartPanel();




        mainFrame.add(panel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
