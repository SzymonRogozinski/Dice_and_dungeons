package GUI.EquipmentGUI;

import Equipment.EquipmentModule;
import GUI.MainPanel;

public class EquipmentView extends MainPanel {

    private SwitchPanel switchPanel;
    private ItemInfoPanel itemInfoPanel;
    private CharactersInfoPanel charactersInfoPanel;
    private ItemManagementPanel itemManagementPanel;

    public EquipmentView() {
        super();
        switchPanel=new SwitchPanel(getBorder());
        itemManagementPanel=new ItemManagementPanel(getBorder());
        itemInfoPanel = new ItemInfoPanel(getBorder());
        charactersInfoPanel=new CharactersInfoPanel(getBorder());

        setPanelsContent(itemManagementPanel,switchPanel,itemInfoPanel,charactersInfoPanel);
    }

    public void setEquipmentModule(EquipmentModule module){
        switchPanel.setEquipment(module);
        itemInfoPanel.setEquipment(module);
        charactersInfoPanel.setEquipment(module);
        itemManagementPanel.setEquipment(module);

        refresh();
    }

    public SwitchPanel getSwitchPanel() {
        return switchPanel;
    }

    public ItemInfoPanel getItemInfoPanel() {
        return itemInfoPanel;
    }

    public CharactersInfoPanel getCharactersInfoPanel() {
        return charactersInfoPanel;
    }

    public ItemManagementPanel getItemManagementPanel() {
        return itemManagementPanel;
    }

    public void setState(int newState,int currentState){
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
        charactersInfoPanel.refresh();
        itemManagementPanel.refresh();
        //Refresh
        this.revalidate();
        this.repaint();
    }
}
