package Equipment.Items;

import Game.Tagable;
import Game.Tags;

import javax.swing.*;

public abstract class Item extends Tagable {

    private final ImageIcon icon;
    private final ItemQuality quality;
    public final String name;

    public Item(Tags[] tags, ImageIcon icon,String name, ItemQuality quality) {
        super(tags);
        this.icon=icon;
        this.name=name;
        this.quality=quality;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public ItemQuality getQuality() {
        return quality;
    }

    @Override
    public boolean equals(Object o){
        return o.getClass().equals(this.getClass()) && ((Item) o).name.equals(name);
    }
}
