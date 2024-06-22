import GUI.EquipmentGUI.EquipmentView;
import GUI.MainFrame;

import javax.swing.*;

public class Test {

    private static final JFrame mainFrame=new MainFrame();
    private static EquipmentView view;

    public static void main(String[] args) {
        view = new EquipmentView();

        mainFrame.add(view);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
