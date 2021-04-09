package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import moe.yuru.newhorizons.utils.GameEvent;
import moe.yuru.newhorizons.utils.GameListener;
import moe.yuru.newhorizons.utils.GameNotifier;

/**
 * Town model.
 */
public class Town implements GameNotifier {

    private String mapName;
    private Array<BuildingInstance> buildings;
    private float coins;
    private ObjectMap<Faction, Float> resources;
    private Building toPlace;
    private Array<GameListener> listeners;

    /**
     * @param mapName name of the town map
     */
    public Town(String mapName) {
        this.mapName = mapName;
        buildings = new Array<>();
        resources = new ObjectMap<>();
        toPlace = null;
        listeners = new Array<>();

        // Starting resources
        coins = 10000f;
        for (Faction faction : Faction.values()) {
            resources.put(faction, 1000f);
        }
    }

    /**
     * Validates current location for the pending construction and create and add
     * the associated {@link BuildingInstance} to the town. Fires an event when
     * done.
     * 
     * @param x X axis position
     * @param y Y axis position
     * @return the {@link BuildingInstance} created
     */
    public void validateConstruction(float x, float y) {
        addCoins(toPlace.getStats(1).getCoinCost());
        addResources(toPlace.getFaction(), toPlace.getStats(1).getResourcesCost());
        BuildingInstance instance = new BuildingInstance(toPlace, x, y);
        buildings.add(new BuildingInstance(toPlace, x, y));
        toPlace = null;
        nofify(new GameEvent(this, "validated", instance));
    }

    /**
     * Updates coins and resources balance since the last frame
     * 
     * @param delta last frametime
     */
    public void updateBalance(float delta) {
        for (BuildingInstance building : buildings) {
            addCoins(delta * building.getStats().getCoinsPerSecond());
            addResources(building.getModel().getFaction(), delta * building.getStats().getResourcesPerSecond());
        }
    }

    /**
     * Sends an event to all registered listeners.
     * 
     * @param name       of the event
     * @param attachment any object to be attached
     */
    private void nofify(GameEvent event) {
        for (GameListener listener : listeners) {
            listener.processEvent(event);
        }
    }

    /**
     * @return the name of the town map
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * @return the {@link BuildingInstance}s of this town as an {@link Array}
     */
    public Array<BuildingInstance> getBuildings() {
        return buildings;
    }

    /**
     * @return current coins balance
     */
    public float getCoins() {
        return coins;
    }

    /**
     * @param amount to add/remove
     */
    public void addCoins(float amount) {
        coins += amount;
    }

    /**
     * @return current resources balance
     */
    public ObjectMap<Faction, Float> getResources() {
        return resources;
    }

    /**
     * @param faction {@link Faction}
     * @return {@code faction} ressource balance
     */
    public Float getResources(Faction faction) {
        return resources.get(faction);
    }

    /**
     * @param faction to add resources
     * @param amount  of resources to addsss
     */
    public void addResources(Faction faction, float amount) {
        resources.put(faction, resources.get(faction) + amount);
    }

    /**
     * @return current {@link Building} to be placed
     */
    public Building getToPlace() {
        return toPlace;
    }

    /**
     * Sets a pending building to be placed. Fires an event when done.
     * 
     * @param building to be placed
     */
    public void setToPlace(Building building) {
        toPlace = building;
        nofify(new GameEvent(this, "toPlace", toPlace));
    }

    /**
     * @param listener to be registered
     */
    public void addListener(GameListener listener) {
        listeners.add(listener);
    }

}
