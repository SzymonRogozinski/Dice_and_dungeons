package Equipment;

import Equipment.Items.Item;
import Equipment.Items.SpellItem;
import Equipment.Items.UsableItem;
import Equipment.Items.UsedAllOfItemsException;
import Fight.GameActions.SpellAction;
import Fight.GameActions.UsableItemAction;

import java.util.ArrayList;

public class PartyBackpack {

    public ArrayList<Item> items;

    public PartyBackpack(){
        items = new ArrayList<>();
    }

    public PartyBackpack(ArrayList<Item> items){
        this.items = items;
    }

    public ArrayList<UsableItem> getUsableItems(){
        ArrayList<UsableItem> usableItems=new ArrayList<>();
        for(Item item:items){
            if(item instanceof UsableItem usItem)
                usableItems.add(usItem);
        }
        return usableItems;
    }

    public void putToBackpack(Item item){
        int id=0;
        if(item instanceof UsableItem usableItem && (id=items.indexOf(item))!=-1){
            ((UsableItem) items.get(id)).addNewItems(usableItem.getNumberOfItems());
        }else{
            items.add(item);
        }
    }

    public void removeFromBackpack(Item item){
        int id=0;
        if(item instanceof UsableItem && (id=items.indexOf(item))!=-1){
            try {
                ((UsableItem) items.get(id)).useItem();
            }catch (UsedAllOfItemsException e){
                items.remove(id);
            }
        }else{
            items.remove(item);
        }
    }
}
