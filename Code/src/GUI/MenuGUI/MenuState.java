package GUI.MenuGUI;

public class MenuState {

    public static final int START = 0;
    public static final int CHOOSING_CHARACTER = 1;
    public static final int LOADING_GAME=2;

    private final MenuView view;

    public MenuState(MenuView view) {
        this.view = view;
    }

    public void changeState(int newState){
        view.changeState(newState);
    }

    public void refresh(){
        view.refresh();
    }
}
