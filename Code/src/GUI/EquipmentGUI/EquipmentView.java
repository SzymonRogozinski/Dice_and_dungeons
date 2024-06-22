package GUI.EquipmentGUI;

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
}
