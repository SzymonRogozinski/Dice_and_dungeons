package Equipment;

import GUI.EquipmentGUI.EquipmentGUIState;
import GUI.EquipmentGUI.EquipmentView;
import Character.*;

public class EquipmentModule {
    private EquipmentGUIState state;
    private PlayerParty party;
    private int currentCharacter=0;

    public EquipmentModule(EquipmentView view, EquipmentGUIState state, PlayerParty party) {
        this.state=state;
        this.party = party;
        view.setEquipmentModule(this);
    }

    public PlayerParty getParty() {
        return party;
    }

    public PlayerCharacter getCurrentCharacter(){
        return party.getCharacters().get(currentCharacter);
    }

    public void changeCharacter(boolean forward){
        currentCharacter+=forward?1:-1;
        if(party.getCharacters().size()<=currentCharacter)
            currentCharacter=0;
        else if(currentCharacter<0)
            currentCharacter=party.getCharacters().size()-1;
        state.refresh();
    }

    public void changeViewToBackpack(){
        state.setState(EquipmentGUIState.BACKPACK);
    }

    public void changeViewToEquipment(){
        state.setState(EquipmentGUIState.EQUIPMENT);
    }
}
