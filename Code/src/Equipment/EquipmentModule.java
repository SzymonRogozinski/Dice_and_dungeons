package Equipment;

import Character.PlayerCharacter;
import Dice.DiceAction.DiceAction;
import Equipment.Items.Item;
import Equipment.Items.UsableItem;
import Equipment.Items.UsedAllOfItemsException;
import GUI.EquipmentGUI.EquipmentGUIState;
import GUI.EquipmentGUI.ItemSlot;
import Game.GameManager;
import Game.PlayerInfo;
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
        return PlayerInfo.getParty().getCharacters().get(currentCharacter);
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
        PlayerInfo.getParty().getBackpack().changeMode(true);
        currentCharacter+=forward?1:-1;
        if(PlayerInfo.getParty().getCharacters().size()<=currentCharacter)
            currentCharacter=0;
        else if(currentCharacter<0)
            currentCharacter= PlayerInfo.getParty().getCharacters().size()-1;
        state.refresh();
    }

    public void changeViewToBackpack(){
        PlayerInfo.getParty().getBackpack().changeMode(false);
        state.setState(EquipmentGUIState.BACKPACK);
    }

    public void changeViewToEquipment(){
        PlayerInfo.getParty().getBackpack().changeMode(true);
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
        else if(clickedSlot.getSlotType()==CharacterEquipment.BAG_SLOT && PlayerInfo.getParty().getCharacters().get(currentCharacter).getEquipment().equip(clickedSlot.getItem(), pointedItem.getSlotNumber(), pointedItem.getSlotType())){
            PlayerInfo.getParty().getBackpack().putToBackpack(pointedItem.getItem());
            PlayerInfo.getParty().getBackpack().removeFromBackpack(clickedSlot.getItem());
        }else if(pointedItem.getSlotType()==CharacterEquipment.BAG_SLOT){
            PlayerInfo.getParty().getCharacters().get(currentCharacter).getEquipment().deEquip(clickedSlot.getSlotNumber(), clickedSlot.getSlotType());
            PlayerInfo.getParty().getBackpack().putToBackpack(clickedSlot.getItem());
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
            action.doAction(PlayerInfo.getParty().getCharacters().get(currentCharacter));
        }
        GameManager.getFight().clear();
        try {
            item.useItem();
        } catch (UsedAllOfItemsException e) {
            useItem=null;
            PlayerInfo.getParty().getBackpack().removeFromBackpack(item);
        }
        state.refresh();
    }

    public Item getUseItem() {
        return useItem;
    }

    public void changeBackpackPage(boolean forward){
        if(forward)
            PlayerInfo.getParty().getBackpack().setNextPage();
        else
            PlayerInfo.getParty().getBackpack().setPrevPage();
        state.refresh();
    }

    private void swapItems(){
        if(PlayerInfo.getParty().getCharacters().get(currentCharacter).getEquipment().equip(clickedSlot.getItem(), pointedItem.getSlotNumber(), pointedItem.getSlotType())) {
            if(!PlayerInfo.getParty().getCharacters().get(currentCharacter).getEquipment().equip(pointedItem.getItem(), clickedSlot.getSlotNumber(), clickedSlot.getSlotType()))
                PlayerInfo.getParty().getCharacters().get(currentCharacter).getEquipment().deEquip(clickedSlot.getSlotNumber(), clickedSlot.getSlotType());
        }
    }

    private void setUseItem(Item item){
        if(item==null || item.haveTag(Tags.USABLE_OUT_OF_FIGHT))
            useItem=item;
        state.refresh();
    }
}
