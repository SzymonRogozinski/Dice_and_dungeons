package Walking.Places;

import Walking.Drones.Drone;

import javax.swing.*;
import java.awt.*;

public class TreasureGamePlace extends GamePlace {

    private boolean isOpen;
    private Image openChestIcon;

    public TreasureGamePlace(String path) {
        super("T", path);
        openChestIcon=new ImageIcon(path+"T_open.png").getImage();
        isOpen=false;
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter){
        if(!isOpen && goingToCollideCharacter.getIcon() instanceof PlayerGamePlace){
            System.out.println("You open a chest!");
            isOpen=true;
        }
        return true;
    }

    @Override
    public Image getImage() {
        return isOpen?openChestIcon:super.getImage();
    }
}
