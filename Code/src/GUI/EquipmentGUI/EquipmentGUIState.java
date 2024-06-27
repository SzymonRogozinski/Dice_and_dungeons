package GUI.EquipmentGUI;

public class EquipmentGUIState {
    public static final int EQUIPMENT=0;
    public static final int BACKPACK=1;
    private final EquipmentView panel;
    private int currentState;

    public EquipmentGUIState(EquipmentView panel) {
        currentState=EQUIPMENT;
        this.panel = panel;
        panel.setState(currentState,currentState);
    }

    public void setState(int newState){
        panel.setState(newState,currentState);
        currentState=newState;
    }

    public void refresh(){
        panel.refresh();
    }
}
