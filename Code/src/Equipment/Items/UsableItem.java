package Equipment.Items;

import Fight.GameActions.ItemAction;
import Fight.GameActions.UsableItemAction;
import Game.Tags;

import javax.swing.*;

public class UsableItem extends Item {

    private final UsableItemAction action;
    private int numberOfItems;

    public UsableItem(UsableItemAction action,int numberOfItems,Tags[] tags, ImageIcon icon,String name,ItemQuality quality) {
        super(tags,icon,name,quality);
        this.action=action;
        this.numberOfItems=numberOfItems;
        if(numberOfItems<1){
            throw new IllegalArgumentException("Number of items cannot be less than 1!");
        }
    }

    public UsableItemAction getAction() {
        return action;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void addNewItems(int numberOfNewItems){
        numberOfItems+=numberOfNewItems;
    }

    public void useItem() throws UsedAllOfItemsException{
        numberOfItems--;
        if(numberOfItems<1){
            throw new UsedAllOfItemsException();
        }
    }
}
