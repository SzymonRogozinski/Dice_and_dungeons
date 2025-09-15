package GUI.TradeGUI;

public class TradeGUIState {
    public static final int BUY = 0;
    public static final int SELL = 1;
    private final TradeView view;
    private int currentState = 0;

    public TradeGUIState(TradeView view) {
        this.view=view;
    }

    public void setState(int newState) {
        currentState=newState;
        view.setState(newState);
    }

    public int getCurrentState() {
        return currentState;
    }
}
