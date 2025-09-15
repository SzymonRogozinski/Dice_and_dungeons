package Equipment.Items;

import Fight.GameActions.UsableItemAction;
import Game.Tags;

import javax.swing.*;

public class UsableItem extends Item {

    public final static double COST_MOD = 0.3;
    private final UsableItemAction action;
    private int numberOfItems;

    public UsableItem(UsableItemAction action, int numberOfItems, Tags[] tags, ImageIcon icon, String name, String shortName, ItemQuality quality, int cost) {
        super(tags, icon, name, shortName, quality, cost);
        this.action = action;
        this.numberOfItems = numberOfItems;
        if (numberOfItems < 1) {
            throw new IllegalArgumentException("Number of items cannot be less than 1!");
        }
    }

    public UsableItemAction getAction() {
        return action;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void addNewItems(int numberOfNewItems) {
        numberOfItems += numberOfNewItems;
    }

    public void useItem() throws UsedAllOfItemsException {
        numberOfItems--;
        if (numberOfItems < 1) {
            throw new UsedAllOfItemsException();
        }
    }

    public void removeOne(){
        numberOfItems--;
    }

    public void removeAll(){
        numberOfItems=0;
    }

    public UsableItem getOneItem(){
        return new UsableItem(action,1,tags,getIcon(),name,shortName,getQuality(),getCost());
    }
}
