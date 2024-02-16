package Dice.DiceAction;

public class DamageAction implements DiceAction{
    private static final String id="Damage";
    private final int value;

    public DamageAction(int value){
        this.value=value;
    }

    @Override
    public DiceAction sumAction(DiceAction action) {
        int newValue=value+ action.getValue();
        return new DamageAction(newValue);
    }

    @Override
    public String action() {
        return "Cios zadał " +value + " obrażeń!";
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getIdentification() {
        return id;
    }
}
