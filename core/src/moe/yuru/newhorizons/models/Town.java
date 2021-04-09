package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import moe.yuru.newhorizons.utils.Event;

/**
 * Town model.
 */
public class Town {

    private GameModel gameModel;
    private String mapName;
    private Array<BuildingInstance> buildings;
    private float coins;
    private ObjectMap<Faction, Float> resources;
    private Building toPlace;
    private Float coinsPerSecond;
    private ObjectMap<Faction, Float> resourcesPerSecond;

    /**
     * @param mapName name of the town map
     */
    public Town(GameModel gameModel, String mapName) {
        this.gameModel = gameModel;
        this.mapName = mapName;
        buildings = new Array<>();
        resources = new ObjectMap<>();
        toPlace = null;

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
        gameModel.notifyListeners(new Event(this, "validated", instance));
    }

    /**
     * Updates coins and resources balance since the last frame
     * 
     * @param delta last frametime
     */
    public void updateBalance(float delta) {
        addCoins(delta * coinsPerSecond);
        for (Faction faction : Faction.values()) {
            addResources(faction, delta * resourcesPerSecond.get(faction));
        }
    }

    /**
     * Updates total town coins and resources per second
     */
    public void updatePerSecond() {
        coinsPerSecond = 0f;
        for (BuildingInstance instance : buildings) {
            coinsPerSecond += instance.getStats().getCoinsPerSecond();
        }

        resourcesPerSecond = new ObjectMap<>();
        float rpspf;
        for (BuildingInstance instance : buildings) {
            rpspf = resourcesPerSecond.get(instance.getModel().getFaction(), 0f);
            rpspf += instance.getStats().getResourcesPerSecond();
            resourcesPerSecond.put(instance.getModel().getFaction(), rpspf);
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
    public float getResources(Faction faction) {
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
        gameModel.notifyListeners(new Event(this, "toPlace", toPlace));
    }

}
