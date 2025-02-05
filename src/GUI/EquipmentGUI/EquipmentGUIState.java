package GUI.EquipmentGUI;

public class EquipmentGUIState {
    public static final int EQUIPMENT = 0;
    public static final int BACKPACK = 1;
    private final EquipmentView panel;

    public EquipmentGUIState(EquipmentView panel) {
        this.panel = panel;
    }

    public void setState(int newState) {
        panel.setState(newState);
    }

    public void refresh() {
        panel.refresh();
    }
}
