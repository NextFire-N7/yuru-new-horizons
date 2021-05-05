package moe.yuru.newhorizons.models;

import moe.yuru.newhorizons.utils.Notifier;

/**
 * Abstract game model. Contains player town and ennemy. Extends
 * {@link Notifier} so it can notify possible registered listeners.
 * 
 * @author NextFire
 */
public abstract class GameModel extends Notifier {

    private Town town;

    /**
     * Initializes the notifier and the game town.
     * 
     * @param mapName name of the chousen map
     */
    public GameModel(String mapName) {
        super();
        this.town = new Town(this, mapName);
    }

    /**
     * Updates ennemy statistics since last frame.
     * 
     * @param delta last frametime
     */
    public abstract void updateEnemy(float delta);

    /**
     * @return player {@link Town}
     */
    public Town getTown() {
        return town;
    }

}
