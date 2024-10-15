package Equipment.Items;

import Game.Taggable;
import Game.Tags;

import javax.swing.*;

public abstract class Item extends Taggable {

    public final String name;
    public final String shortName;
    private final ImageIcon icon;
    private final ItemQuality quality;

    public Item(Tags[] tags, ImageIcon icon, String name, String shortName, ItemQuality quality) {
        super(tags);
        this.icon = icon;
        this.name = name;
        this.shortName = shortName;
        this.quality = quality;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public ItemQuality getQuality() {
        return quality;
    }

    @Override
    public boolean equals(Object o) {
        return o.getClass().equals(this.getClass()) && ((Item) o).name.equals(name);
    }
}
