package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.ObjectSet;

import moe.yuru.newhorizons.utils.Event;
import moe.yuru.newhorizons.utils.Notifier;

/**
 * Game model. Contains player's town and opponent. Extends {@link Notifier} so
 * it can notify possible registered listeners. Also serves as a {@link Town}
 * proxy.
 * 
 * @author NextFire
 */
public class GameModel extends Notifier {

    private Town town;
    private Opponent opponent;

    private Building toPlace;

    /**
     * Do not use. Defined for the JSON deserializer.
     */
    @Deprecated
    public GameModel() {
    }

    /**
     * Initializes the notifier and the game town.
     * 
     * @param mapName  name of the chousen map
     * @param opponent player's {@link Opponent}
     */
    public GameModel(String mapName, Opponent opponent) {
        super();
        town = new Town(mapName);
        this.opponent = opponent;
        toPlace = null;
    }

    /**
     * Updates town and opponent. TODO: checks for victory
     * 
     * @param delta last frametime
     */
    public void update(float delta) {
        town.update(delta);
        opponent.update(delta);
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
     * Sets a pending building to be placed. Fires an event when done.
     *
     * @param building to be placed
     */
    public void setToPlace(Building building) {
        toPlace = building;
        notifyListeners(new Event(this, "toPlace", toPlace));
    }

    /**
     * @return town map name
     */
    public String getTownMapName() {
        return town.getMapName();
    }

    /**
     * @return town building instances
     */
    public ObjectSet<BuildingInstance> getTownBuildings() {
        return town.getBuildings();
    }

    /**
     * @return town coins
     */
    public float getTownCoins() {
        return town.getCoins();
    }

    /**
     * @param faction a game faction
     * @return town resources in this given faction
     */
    public float getTownResources(Faction faction) {
        return town.getResources(faction);
    }

}
