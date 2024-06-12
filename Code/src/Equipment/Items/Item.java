package Equipment.Items;

import Game.Tagable;
import Game.Tags;

import javax.swing.*;

public abstract class Item extends Tagable {

    private final ImageIcon icon;
    public final String name;

    public Item(Tags[] tags, ImageIcon icon,String name) {
        super(tags);
        this.icon=icon;
        this.name=name;
    }

    @Override
    public boolean equals(Object o){
        return o.getClass().equals(this.getClass()) && ((Item) o).name.equals(name);
    }
}
