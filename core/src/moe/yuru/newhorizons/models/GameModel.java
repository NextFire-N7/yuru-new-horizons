package moe.yuru.newhorizons.models;

import moe.yuru.newhorizons.utils.Event;
import moe.yuru.newhorizons.utils.Notifier;

/**
 * Abstract game model. Contains player town and ennemy. Extends
 * {@link Notifier} so it can notify possible registered listeners.
 * 
 * @author NextFire
 */
public abstract class GameModel extends Notifier {

    private Town town;
    protected Ennemy ennemy;

    private Building toPlace;

    /**
     * Initializes the notifier and the game town.
     * 
     * @param mapName name of the chousen map
     */
    public GameModel(String mapName) {
        super();
        town = new Town(mapName);
        toPlace = null;
    }

    /**
     * Updates town and ennemy. TODO: checks for victory
     * 
     * @param delta last frametime
     */
    public void update(float delta) {
        town.update(delta);
        ennemy.update(delta);
    }

    /**
     * Validates current location for the pending construction and create and add
     * the associated {@link BuildingInstance} to the town. Fires an event when
     * done.
     *
     * @param x X axis position
     * @param y Y axis position
     * @return the building instance created
     */
    public void validateConstruction(float x, float y) {
        town.addCoins(toPlace.getStats(1).getCoinCost());
        town.addResources(toPlace.getFaction(), toPlace.getStats(1).getResourcesCost());

        BuildingInstance instance = new BuildingInstance(toPlace, x, y);
        town.getBuildings().add(instance);
        toPlace = null;
        notifyListeners(new Event(this, "toPlace", null));
        town.updatePerSecond();
        notifyListeners(new Event(this, "validated", instance));
    }

    /**
     * @return player {@link Town}
     */
    public Town getTown() {
        return town;
    }

    /**
     * @return game {@link Ennemy}
     */
    public Ennemy getEnnemy() {
        return ennemy;
    }

    /**
     * Sets a pending building to be placed. Fires an event when done.
     *
     * @param building to be placed
     */
    public void setToPlace(Building building) {
        toPlace = building;
        notifyListeners(new Event(this, "toPlace", toPlace));
    }

}
