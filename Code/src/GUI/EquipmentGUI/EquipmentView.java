package GUI.EquipmentGUI;

import GUI.GUISettings;
import GUI.ViewPanel;
import Game.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class EquipmentView extends ViewPanel {

    private static MouseMotionAdapter mouseMotionAdp;
    private SwitchPanel switchPanel;
    private ItemInfoPanel itemInfoPanel;
    private CharactersInfoPanel charactersInfoPanel;
    private ItemManagementPanel itemManagementPanel;
    private ImageIcon dragableIcon;
    private Point position;

    public EquipmentView() {
        super(new ItemManagementPanel(getSharedBorder()),new SwitchPanel(getSharedBorder()),new ItemInfoPanel(getSharedBorder()),new CharactersInfoPanel(getSharedBorder()));

        //Get child component
        ArrayList<JPanel> panels = getChildPanels();
        itemManagementPanel = (ItemManagementPanel) panels.get(0);
        switchPanel = (SwitchPanel) panels.get(1);
        itemInfoPanel = (ItemInfoPanel) panels.get(2);
        charactersInfoPanel = (CharactersInfoPanel) panels.get(3);

        mouseMotionAdp=new DragListener();
        this.addMouseMotionListener(mouseMotionAdp);

        position=new Point(0,0);
        dragableIcon=new ImageIcon("ItemsIcons/bag-pl.png");
    }

    public static MouseMotionAdapter getMouseMotionAdp() {
        return mouseMotionAdp;
    }

    public void setState(int newState, int currentState){
        switch (newState){
            case EquipmentGUIState.EQUIPMENT -> {
                charactersInfoPanel.setEquipmentVisibility(true);
                itemManagementPanel.changeCard("Equipment");
            }
            case EquipmentGUIState.BACKPACK -> {
                charactersInfoPanel.setEquipmentVisibility(false);
                itemManagementPanel.changeCard("Backpack");
            }
        }
        refresh();
    }

    public void refresh(){
        if(GameManager.getEquipment()==null)
            return;
        charactersInfoPanel.refresh();
        itemManagementPanel.refresh();
        itemInfoPanel.refresh();
        //Refresh
        this.revalidate();
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        ItemSlot it = GameManager.getEquipment().getClickedSlot();
        if(it!=null && it.getItem()!=null) {
            position=MouseInfo.getPointerInfo().getLocation();
            it.getItem().getIcon().paintIcon(this, g, (int) position.getX() - GUISettings.ITEM_ICON_SIZE/2 - this.getLocationOnScreen().x, (int) position.getY() - GUISettings.ITEM_ICON_SIZE/2- this.getLocationOnScreen().y);
        }
    }

    private class DragListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            repaint();
        }
    }


}
