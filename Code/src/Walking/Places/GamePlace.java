package Walking.Places;

import Walking.Collision.CollisionException;
import Walking.Drones.Drone;

import javax.swing.*;
import java.awt.*;

public abstract class GamePlace {

    private final Image image;
    private Drone reference;

    public GamePlace(char pic, String path) {
        image=new ImageIcon(path+pic+".png").getImage();
    }

    public GamePlace(String name, String path){
        image=new ImageIcon(path+name+".png").getImage();
    }

    public Image getImage() {
        return image;
    }

    public Drone getReference() {
        return reference;
    }

    public boolean getCollision(Drone goingToCollideCharacter) throws CollisionException {
        return false;
    }
    public void setReference(Drone reference){
        this.reference=reference;
    }
}
