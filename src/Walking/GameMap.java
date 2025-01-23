package Walking;

import Game.GameManager;
import Game.PlayerInfo;
import Walking.Drones.Drone;
import Walking.Collision.*;
import Walking.Drones.EnemyDrone;
import Walking.Places.*;

import dg.generator.dungeon.Map;

public class GameMap {
    private final int height, width;
    private final GamePlace[][] currentGamePlaces;
    private final GamePlace[][] originalGamePlaces;
    private final int startX, startY;
    private final String path;

    public GameMap(Map map, String path, boolean bossLevel) {
        this.path = path;
        this.width = map.getWidth();
        this.height = map.getHeight();
        this.currentGamePlaces = new GamePlace[height][width];
        this.originalGamePlaces = new GamePlace[height][width];

        GamePlace place;
        //Load terrain
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                switch (map.getTerrain(x, y)) {
                    case null -> place = new SpaceGamePlace(this.path);
                    case FLOOR, VOID, ENTRIES, ENEMY -> place = new SpaceGamePlace(this.path);
                    case DOOR -> place = new DoorGamePlace(this.path);
                    case TREASURE -> place = new TreasureGamePlace(this.path);
                    case KEY -> place = new KeyGamePlace(this.path);
                    case WALL -> place = new WallGamePlace('W', this.path);
                    default -> throw new RuntimeException("Something goes wrong while writing map!");
                }
                currentGamePlaces[y][x] = place;
                originalGamePlaces[y][x] = place;
            }
        }
        //Entry
        place = new EntryGamePlace(this.path, true, false);
        int x, y;
        x = map.getEntries()[0].x;
        y = map.getEntries()[0].y;
        currentGamePlaces[y][x] = place;
        originalGamePlaces[y][x] = place;
        startX = x;
        startY = y;
        //Exit
        place = new EntryGamePlace(this.path, false, bossLevel);
        x = map.getEntries()[1].x;
        y = map.getEntries()[1].y;
        currentGamePlaces[y][x] = place;
        originalGamePlaces[y][x] = place;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getPath() {
        return path;
    }

    public GamePlace getPlace(int x, int y) {
        return currentGamePlaces[y][x];
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public void addCharacterPlace(GamePlace place, int x, int y) {
        try {
            if (currentGamePlaces[y][x] instanceof SpaceGamePlace || (currentGamePlaces[y][x] instanceof EntryGamePlace && place instanceof PlayerGamePlace))
                currentGamePlaces[y][x] = place;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Character has illegal coordinates, cannot be placed!");
        }
    }

    //true means, that character was moved
    public boolean changeCharacterPlace(Drone gc, int dx, int dy) throws EnemyFightException, EnterExitException {
        boolean collisionDetected = false;
        //If collision
        try {
            collisionDetected = currentGamePlaces[gc.getPosY() + dy][gc.getPosX() + dx].getCollision(gc);
        } catch (EnterExitException e) {
            throw e;
        } catch (EnemyFightException e) {
            if (gc instanceof EnemyDrone) {
                collisionDetected = true;
                currentGamePlaces[gc.getPosY()][gc.getPosX()] = originalGamePlaces[gc.getPosY()][gc.getPosX()];
            }
            throw e;
        } catch (KeyCollectedException e) {
            PlayerInfo.collectKey();
            originalGamePlaces[gc.getPosY() + dy][gc.getPosX() + dx] = new SpaceGamePlace(path);
        } catch (DoorOpenException e) {
            originalGamePlaces[gc.getPosY() + dy][gc.getPosX() + dx] = new SpaceGamePlace(path);
        } catch (ChestOpenException e) {
            GameManager.getLootModule().getLoot(GameManager.getCurrentLevel().lootSettings(), false);
            collisionDetected = true;
        } catch (CollisionException e) {
            //Should not happen!
            throw new RuntimeException(e);
        } finally {
            if (!collisionDetected) {
                currentGamePlaces[gc.getPosY()][gc.getPosX()] = originalGamePlaces[gc.getPosY()][gc.getPosX()];
                currentGamePlaces[gc.getPosY() + dy][gc.getPosX() + dx] = gc.getIcon();
                gc.setPos(dx, dy);
            }
        }
        return !collisionDetected;
    }
}


