package Walking.Places;

import Walking.Collision.ChestOpenException;
import Walking.Drones.Drone;

import javax.swing.*;
import java.awt.*;

public class TreasureGamePlace extends GamePlace {

    private final Image openChestIcon;
    private boolean isOpen;

    public TreasureGamePlace(String path) {
        super("T", path);
        openChestIcon = new ImageIcon(path + "T_open.png").getImage();
        isOpen = false;
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws ChestOpenException {
        if (!isOpen && goingToCollideCharacter.getIcon() instanceof PlayerGamePlace) {
            isOpen = true;
            throw new ChestOpenException();
        }
        return true;
    }

    @Override
    public Image getImage() {
        return isOpen ? openChestIcon : super.getImage();
    }
}
