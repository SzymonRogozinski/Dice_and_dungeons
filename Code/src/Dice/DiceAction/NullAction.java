package Dice.DiceAction;

public class NullAction implements DiceAction{
    private static final String id=null;

    public NullAction(){}

    @Override
    public DiceAction sumAction(DiceAction action) {
        return new NullAction();
    }

    @Override
    public String action() {
        return null;
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public String getIdentification() {
        return id;
    }
}
