package Walking.Places;

import Quest.Quest;
import Walking.Collision.CollisionException;
import Walking.Drones.Drone;
import Walking.Drones.PlayerDrone;
import dg.generator.dungeon.Place;

import java.awt.*;

public class QuestPlace extends GamePlace{

    private final GamePlace superPlace;
    private final Quest quest;

    public QuestPlace(GamePlace superPlace, Quest quest) {
        super(' ', ""); //Null
        this.superPlace = superPlace;
        this.quest=quest;
    }

    @Override
    public Image getImage() {
        return superPlace.getImage();
    }

    @Override
    public Drone getReference() {
        return superPlace.getReference();
    }

    @Override
    public void setReference(Drone reference) {
        superPlace.setReference(reference);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws CollisionException {
        if(goingToCollideCharacter instanceof PlayerDrone)
            quest.questWasDone();
        return superPlace.getCollision(goingToCollideCharacter);
    }
}
