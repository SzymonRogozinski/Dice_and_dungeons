package Trade;

import Equipment.CharacterEquipment;
import Equipment.Items.Item;
import Equipment.Items.UsableItem;
import GUI.Shared.Components.ItemSlot;
import GUI.TradeGUI.TradeGUIState;
import Game.GameManager;
import Game.PlayerInfo;
import Generators.ItemGenerators.UsableItemGenerator;

public class TradeModule {

    private final TradeGUIState state;
    private ItemSlot pointedItem,clickedSlot;
    private Item tradeItem;
    private Trader trader;

    public TradeModule(TradeGUIState state) {
        this.state = state;
    }

    public Item getPointedItem() {
        return pointedItem == null ? null : pointedItem.getItem();
    }

    public void setPointedItem(ItemSlot slot) {
        pointedItem = slot;
    }

    public ItemSlot getClickedSlot() {
        return clickedSlot;
    }

    public void setClickedSlot(ItemSlot clickedSlot) {
        this.clickedSlot = clickedSlot;
    }

    public Item getTradeItem() {
        return tradeItem;
    }

    public void setTradeItem(Item tradeItem) {
        this.tradeItem = tradeItem;
    }

    public void setTradeItem() {
        if(pointedItem.getSlotType()== CharacterEquipment.TRADE_SLOT)
            this.tradeItem = clickedSlot.getItem();
        //After all
        clickedSlot = null;
    }

    public int getCurrentState(){
        return state.getCurrentState();
    }

    public void tradeItem(){
        if(state.getCurrentState()==TradeGUIState.BUY)
            buyItem();
        else
            sellItem();
    }

    public void tradeAllItem(){
        if(state.getCurrentState()==TradeGUIState.BUY)
            buyAllItem();
        else
            sellAllItem();
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public void changeViewToBuy(){
        tradeItem=null;
        state.setState(TradeGUIState.BUY);
    }

    public void changeViewToSell(){
        tradeItem=null;
        state.setState(TradeGUIState.SELL);
    }

    public void changePage(boolean forward){
        if(state.getCurrentState()==TradeGUIState.SELL)
            GameManager.getEquipment().changeBackpackPage(forward);
        else{
            if (forward)
                GameManager.getTradeModule().getTrader().setNextPage();
            else
                GameManager.getTradeModule().getTrader().setPrevPage();
        }
    }

    private void sellItem(){
        if(tradeItem instanceof UsableItem usableItem){
            PlayerInfo.addGold(tradeItem.getCost());
            usableItem.removeOne();
            if(usableItem.getNumberOfItems()<1) {
                PlayerInfo.getParty().getBackpack().removeFromBackpack(tradeItem);
                tradeItem=null;
            }
        }else{
            PlayerInfo.addGold(tradeItem.getCost());
            PlayerInfo.getParty().getBackpack().removeFromBackpack(tradeItem);
            tradeItem=null;
        }
    }

    private void sellAllItem(){
        if(tradeItem instanceof UsableItem usableItem){
            PlayerInfo.addGold(tradeItem.getCost()*usableItem.getNumberOfItems());
            usableItem.removeAll();
            PlayerInfo.getParty().getBackpack().removeFromBackpack(tradeItem);
            tradeItem=null;
        }else
            sellItem();
    }

    private void buyItem(){
        if(PlayerInfo.getGold()<tradeItem.getCost())
            throw new RuntimeException("Player cannot buy item!");
        if(tradeItem instanceof UsableItem usableItem){
            PlayerInfo.spendGold(tradeItem.getCost());
            usableItem.removeOne();
            PlayerInfo.getParty().getBackpack().putToBackpack(
                    usableItem.getOneItem()
            );
            if(usableItem.getNumberOfItems()<1) {
                GameManager.getTradeModule().getTrader().getItems().remove(tradeItem);
                tradeItem=null;
            }
        }else{
            PlayerInfo.spendGold(tradeItem.getCost());
            PlayerInfo.getParty().getBackpack().putToBackpack(tradeItem);
            GameManager.getTradeModule().getTrader().getItems().remove(tradeItem);
            tradeItem=null;
        }
    }

    private void buyAllItem(){
        if(tradeItem instanceof UsableItem usableItem){
            PlayerInfo.spendGold(tradeItem.getCost()*usableItem.getNumberOfItems());
            PlayerInfo.getParty().getBackpack().putToBackpack(tradeItem);
            GameManager.getTradeModule().getTrader().getItems().remove(tradeItem);
            tradeItem=null;
        }else
            buyItem();
    }
}
