package GUI.WalkingGUI;

public class WalkingGUIState {

    private final WalkingView view;

    public WalkingGUIState(WalkingView view) {
        this.view = view;
    }

    public void refresh(){
        view.refresh();
    }
}
