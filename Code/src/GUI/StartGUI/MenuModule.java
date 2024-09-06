package GUI.StartGUI;

import java.util.ArrayList;
import Character.PlayerCharacter;

public class MenuModule {

    private final MenuState state;
    private final ArrayList<PlayerCharacter> party;
    private PlayerCharacter selectedCharacter;

    public MenuModule(MenuState state) {
        party = new ArrayList<>();
        this.state = state;
    }

    public ArrayList<PlayerCharacter> getParty() {
        return party;
    }

    public void addToParty(PlayerCharacter character){
        party.add(character);
        state.refresh();
    }

    public void removeFromParty(PlayerCharacter character){
        party.remove(character);
        state.refresh();
    }

    public PlayerCharacter getSelectedCharacter() {
        return selectedCharacter;
    }

    public void setSelectedCharacter(PlayerCharacter selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
        state.refresh();
    }

    public void changeToStart(){
        state.changeState(MenuState.START);
    }

    public void changeToChoose(){
        state.changeState(MenuState.CHOOSING_CHARACTER);
    }

    public void changeToLoad(){
        state.changeState(MenuState.LOADING_GAME);
    }
}
