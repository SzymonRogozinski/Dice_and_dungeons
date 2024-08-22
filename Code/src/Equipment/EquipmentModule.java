package Equipment;

import Dice.DiceAction.DiceAction;
import Equipment.Items.Item;
import Equipment.Items.UsableItem;
import Equipment.Items.UsedAllOfItemsException;
import GUI.EquipmentGUI.EquipmentGUIState;
import GUI.EquipmentGUI.EquipmentView;
import Character.*;
import GUI.EquipmentGUI.ItemSlot;
import Game.GameCollection;
import Game.Tags;

public class EquipmentModule {
    private EquipmentGUIState state;
    private int currentCharacter=0;
    private ItemSlot pointedItem;
    private ItemSlot clickedSlot;
    private Item useItem;

    public EquipmentModule(EquipmentGUIState state) {
        this.state=state;
    }

    public PlayerCharacter getCurrentCharacter(){
        return GameCollection.getParty().getCharacters().get(currentCharacter);
    }

    public void setPointedItem(ItemSlot slot){
        pointedItem=slot;
        state.refresh();
    }

    public Item getPointedItem() {
        return pointedItem==null?null:pointedItem.getItem();
    }

    public ItemSlot getClickedSlot() {
        return clickedSlot;
    }

    public void changeCharacter(boolean forward){
        GameCollection.getParty().getBackpack().changeMode(true);
        currentCharacter+=forward?1:-1;
        if(GameCollection.getParty().getCharacters().size()<=currentCharacter)
            currentCharacter=0;
        else if(currentCharacter<0)
            currentCharacter=GameCollection.getParty().getCharacters().size()-1;
        state.refresh();
    }

    public void changeViewToBackpack(){
        GameCollection.getParty().getBackpack().changeMode(false);
        state.setState(EquipmentGUIState.BACKPACK);
    }

    public void changeViewToEquipment(){
        GameCollection.getParty().getBackpack().changeMode(true);
        state.setState(EquipmentGUIState.EQUIPMENT);
    }

    public void setClickedItem(ItemSlot slot){
        clickedSlot=slot;
        state.refresh();
    }

    public void equipItem(){
        if(clickedSlot==null || pointedItem==null || clickedSlot.getItem()==null)
            ;
        else if(clickedSlot.getSlotType()==pointedItem.getSlotType())
            swapItems();
        else if (clickedSlot.getSlotType()==CharacterEquipment.BAG_SLOT && pointedItem.getSlotType()==CharacterEquipment.USE_SLOT)
            setUseItem(clickedSlot.getItem());
        else if (clickedSlot.getSlotType()==CharacterEquipment.USE_SLOT && pointedItem.getSlotType()==CharacterEquipment.BAG_SLOT)
            setUseItem(null);
        else if(clickedSlot.getSlotType()==CharacterEquipment.BAG_SLOT && GameCollection.getParty().getCharacters().get(currentCharacter).getEquipment().equip(clickedSlot.getItem(), pointedItem.getSlotNumber(), pointedItem.getSlotType())){
            GameCollection.getParty().getBackpack().putToBackpack(pointedItem.getItem());
            GameCollection.getParty().getBackpack().removeFromBackpack(clickedSlot.getItem());
        }else if(pointedItem.getSlotType()==CharacterEquipment.BAG_SLOT){
            GameCollection.getParty().getCharacters().get(currentCharacter).getEquipment().deEquip(clickedSlot.getSlotNumber(), clickedSlot.getSlotType());
            GameCollection.getParty().getBackpack().putToBackpack(clickedSlot.getItem());
        }
        //After all
        clickedSlot=null;
        state.refresh();
    }

    public void useChosenItem(){
        if(useItem==null)
            return;
        UsableItem item=(UsableItem) useItem;
        for(DiceAction action:item.getAction().getActionFactories()){
            action.doAction(GameCollection.getParty().getCharacters().get(currentCharacter));
        }
        GameCollection.getFight().clear();
        try {
            item.useItem();
        } catch (UsedAllOfItemsException e) {
            useItem=null;
            GameCollection.getParty().getBackpack().removeFromBackpack(item);
        }
        state.refresh();
    }

    public Item getUseItem() {
        return useItem;
    }

    public void changeBackpackPage(boolean forward){
        if(forward)
            GameCollection.getParty().getBackpack().setNextPage();
        else
            GameCollection.getParty().getBackpack().setPrevPage();
        state.refresh();
    }

    private void swapItems(){
        if(GameCollection.getParty().getCharacters().get(currentCharacter).getEquipment().equip(clickedSlot.getItem(), pointedItem.getSlotNumber(), pointedItem.getSlotType())) {
            if(!GameCollection.getParty().getCharacters().get(currentCharacter).getEquipment().equip(pointedItem.getItem(), clickedSlot.getSlotNumber(), clickedSlot.getSlotType()))
                GameCollection.getParty().getCharacters().get(currentCharacter).getEquipment().deEquip(clickedSlot.getSlotNumber(), clickedSlot.getSlotType());
        }
    }

    private void setUseItem(Item item){
        if(item==null || item.haveTag(Tags.USABLE_OUT_OF_FIGHT))
            useItem=item;
        state.refresh();
    }
}
