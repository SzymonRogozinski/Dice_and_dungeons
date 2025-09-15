package GUI.EquipmentGUI;

public class EquipmentGUIState {
    public static final int EQUIPMENT = 0;
    public static final int BACKPACK = 1;
    private final EquipmentView view;

    public EquipmentGUIState(EquipmentView view) {
        this.view = view;
    }

    public void setState(int newState) {
        view.setState(newState);
    }
}
