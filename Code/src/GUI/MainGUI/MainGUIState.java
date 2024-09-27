package GUI.MainGUI;

import Game.GameStates;

public class MainGUIState {

    private final MainFrame frame;

    public MainGUIState(MainFrame frame) {
        this.frame=frame;
    }

    public void refresh(GameStates state){
        frame.refresh(state);
    }
}
