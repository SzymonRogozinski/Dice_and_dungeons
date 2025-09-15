package Trade;

import Equipment.Items.Item;
import Equipment.Items.ItemQuality;
import Generators.ItemGenerators.ArmorGenerator;
import Generators.ItemGenerators.DiceItemGenerator;
import Generators.ItemGenerators.SpellItemGenerator;
import Generators.ItemGenerators.UsableItemGenerator;
import org.json.JSONObject;

import java.util.ArrayList;

public class Trader {

    private final static int pageSize = 42;
    private final ArrayList<Item> items;
    private int pageNumber;

    public Trader(ArrayList<Item> items) {
        this.items = items;
    }

    public Trader(JSONObject traderJson) {
        ItemQuality quality;
        switch (traderJson.getString("quality")){
            case "common" -> quality=ItemQuality.COMMON;
            case "rare" -> quality=ItemQuality.RARE;
            case "legendary" -> quality=ItemQuality.LEGENDARY;
            case null, default -> throw new RuntimeException("Quality not implemented!");
        }
        int itemCount = traderJson.getInt("itemCount");

        ArrayList<Item> items = new ArrayList<>(UsableItemGenerator.generateTrader(quality));
        for (int i = 0; i < itemCount; i++) {
            items.add(ArmorGenerator.generateArmor(quality));
            items.add(DiceItemGenerator.generateItem(quality));
            items.add(SpellItemGenerator.generateItem(quality));
        }

        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Item> getPageOfItems() {
        ArrayList<Item> itemList = new ArrayList<>();
        for (int i = pageNumber * pageSize; i < (pageNumber + 1) * pageSize && i < items.size(); i++) {
            itemList.add(items.get(i));
        }
        return itemList;
    }

    public void setNextPage() {
        if ((pageNumber + 1) * pageSize < items.size())
            pageNumber++;
    }

    public void setPrevPage() {
        pageNumber = Math.max(pageNumber - 1, 0);
    }

}
