package Equipment;

import Equipment.Items.EquipableItem;
import Equipment.Items.Item;
import Equipment.Items.UsableItem;
import Equipment.Items.UsedAllOfItemsException;
import Character.PlayerCharacter;

import java.util.ArrayList;

public class PartyBackpack {

    private ArrayList<Item> items;
    private int pageNumber;
    private int maxSmallPages;
    private boolean isEquipment;
    private final static int pageSize = 42;
    private final static int smallPageSize = 12;

    public PartyBackpack(ArrayList<Item> items){
        this.items = items;
    }

    public ArrayList<Item> getAllItems() {
        return items;
    }

    public ArrayList<Item> getPageOfItems() {
        ArrayList<Item> itemList=new ArrayList<>();
        for(int i=pageNumber*pageSize;i<(pageNumber+1)*pageSize && i< items.size();i++){
            itemList.add(items.get(i));
        }
        return itemList;
    }

    public void setNextPage(){
        if(!isEquipment && (pageNumber+1)*pageSize<items.size())
            pageNumber++;
        else if(isEquipment && maxSmallPages>pageNumber)
            pageNumber++;
    }

    public void setPrevPage(){
        pageNumber=Math.max(pageNumber-1,0);
    }

    public void changeMode(boolean isEquipment){
        this.isEquipment=isEquipment;
        pageNumber=0;
        maxSmallPages=0;
    }

    public ArrayList<Item> getPageOfItemsForCharacter(PlayerCharacter player) {
        ArrayList<Item> characterItems = new ArrayList<>();
        for (Item i:items){
            if(i instanceof EquipableItem eItem && eItem.canEquip(player))
                characterItems.add(i);
        }
        maxSmallPages=characterItems.size()/smallPageSize;
        ArrayList<Item> pageItems = new ArrayList<>();
        for(int i = smallPageSize*pageNumber; i< smallPageSize*(pageNumber+1) && i<characterItems.size(); i++){
            pageItems.add(characterItems.get(i));
        }
        return pageItems;
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
        if(item==null)
            return;
        int id=0;
        if(item instanceof UsableItem usableItem && (id=items.indexOf(item))!=-1){
            ((UsableItem) items.get(id)).addNewItems(usableItem.getNumberOfItems());
        }else{
            items.add(item);
        }
    }

    public void removeFromBackpack(Item item){
        int id;
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
