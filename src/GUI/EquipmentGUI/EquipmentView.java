package GUI.EquipmentGUI;

import GUI.Components.GameLabel;
import GUI.GUISettings;
import GUI.ViewPanel;
import Game.GameManager;
import Game.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class EquipmentView extends ViewPanel {

    private static MouseMotionAdapter mouseMotionAdp;
    private final ItemInfoPanel itemInfoPanel;
    private final CharactersInfoPanel charactersInfoPanel;
    private final ItemManagementPanel itemManagementPanel;
    private final SwitchPanel switchPanel;
    private final GameLabel dragableIcon;
    private ImageIcon dragIcon;

    public EquipmentView() {
        super(new ItemManagementPanel(getSharedBorder()), new SwitchPanel(getSharedBorder()), new ItemInfoPanel(getSharedBorder()), new CharactersInfoPanel(getSharedBorder()));

        //Get child component
        ArrayList<JPanel> panels = getChildPanels();
        itemManagementPanel = (ItemManagementPanel) panels.get(0);
        switchPanel = (SwitchPanel) panels.get(1);
        itemInfoPanel = (ItemInfoPanel) panels.get(2);
        charactersInfoPanel = (CharactersInfoPanel) panels.get(3);

        mouseMotionAdp = new DragListener();
        this.addMouseMotionListener(mouseMotionAdp);

        dragableIcon = new GameLabel(null,GUISettings.ITEM_ICON_SIZE,GUISettings.ITEM_ICON_SIZE);
        dragableIcon.setSize(dragableIcon.getPreferredSize());
        this.add(dragableIcon,JLayeredPane.DRAG_LAYER);
    }

    public static MouseMotionAdapter getMouseMotionAdp() {
        return mouseMotionAdp;
    }

    public void setState(int newState) {
        switch (newState) {
            case EquipmentGUIState.EQUIPMENT -> {
                charactersInfoPanel.setEquipmentVisibility(true);
                itemManagementPanel.changeCard("Equipment");
            }
            case EquipmentGUIState.BACKPACK -> {
                charactersInfoPanel.setEquipmentVisibility(false);
                itemManagementPanel.changeCard("Backpack");
            }
        }
    }

    public void refresh() {
        if (GameManager.getEquipment() == null)
            return;
        charactersInfoPanel.refresh();
        itemManagementPanel.refresh();
        itemInfoPanel.refresh();

        //Set pointed item
        ItemSlot it = GameManager.getEquipment().getClickedSlot();
        if(it == null || it.getItem() == null){
            dragableIcon.setIcon(null);
            dragIcon=null;
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        } else if (it.getItem().getIcon()!=dragIcon) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            dragIcon=it.getItem().getIcon();
            dragableIcon.setIcon(GameUtils.resizeIcon(dragIcon,GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE)));
        }
        //Set position
        Point position = MouseInfo.getPointerInfo().getLocation();
        int resizedItemSize = GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE);
        dragableIcon.setLocation((int) position.getX() - resizedItemSize / 2 - this.getLocationOnScreen().x,
                    (int) position.getY() - resizedItemSize / 2 - this.getLocationOnScreen().y);
    }

    public void resize(){
        super.resize();

        itemManagementPanel.resize();
        switchPanel.resize();
        charactersInfoPanel.resize();
        itemInfoPanel.resize();

        dragableIcon.resize();
        dragableIcon.setSize(dragableIcon.getPreferredSize());
        dragableIcon.setIcon(GameUtils.resizeIcon(dragIcon,GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE)));

    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2D = (Graphics2D) g;
//
//        ItemSlot it = GameManager.getEquipment().getClickedSlot();
//        if (it != null && it.getItem() != null) {
//            position = MouseInfo.getPointerInfo().getLocation();
//            g2D.drawImage(
//                    it.getItem().getImage(),
//                    (int) position.getX() - GUISettings.ITEM_ICON_SIZE / 2 - this.getLocationOnScreen().x,
//                    (int) position.getY() - GUISettings.ITEM_ICON_SIZE / 2 - this.getLocationOnScreen().y,
//                    GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE),
//                    GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE),
//                    null
//            );
//        }
//    }

//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        Graphics2D g2D = (Graphics2D) g;
//
//        ItemSlot it = GameManager.getEquipment().getClickedSlot();
//        if (it != null && it.getItem() != null) {
//            position = MouseInfo.getPointerInfo().getLocation();
//            g2D.drawImage(
//                    it.getItem().getImage(),
//                    (int) position.getX() - GUISettings.ITEM_ICON_SIZE / 2 - this.getLocationOnScreen().x,
//                    (int) position.getY() - GUISettings.ITEM_ICON_SIZE / 2 - this.getLocationOnScreen().y,
//                    GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE),
//                    GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE),
//                    null
//                    );
//        }
//    }

    private class DragListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            repaint();
        }
    }
}
