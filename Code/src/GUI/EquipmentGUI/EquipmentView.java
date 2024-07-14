package GUI.EquipmentGUI;

import GUI.GUISettings;
import GUI.MainPanel;
import Game.GameCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class EquipmentView extends MainPanel {

    private static MouseMotionAdapter mouseMotionAdp;
    private SwitchPanel switchPanel;
    private ItemInfoPanel itemInfoPanel;
    private CharactersInfoPanel charactersInfoPanel;
    private ItemManagementPanel itemManagementPanel;
    private ImageIcon dragableIcon;
    private Point position;

    public EquipmentView() {
        super();
        mouseMotionAdp=new DragListener();
        this.addMouseMotionListener(mouseMotionAdp);
        switchPanel=new SwitchPanel(getBorder());
        itemManagementPanel=new ItemManagementPanel(getBorder());
        itemInfoPanel = new ItemInfoPanel(getBorder());
        charactersInfoPanel=new CharactersInfoPanel(getBorder());

        setPanelsContent(itemManagementPanel,switchPanel,itemInfoPanel,charactersInfoPanel);

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
        if(GameCollection.getEquipment()==null)
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

        ItemSlot it = GameCollection.getEquipment().getClickedSlot();
        if(it!=null && it.getItem()!=null) {
            position=MouseInfo.getPointerInfo().getLocation();
            it.getItem().getIcon().paintIcon(this, g, (int) position.getX() - GUISettings.ITEM_ICON_SIZE/2, (int) position.getY() - GUISettings.ITEM_ICON_SIZE/2);
        }
    }

    private class DragListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            repaint();
        }
    }


}
