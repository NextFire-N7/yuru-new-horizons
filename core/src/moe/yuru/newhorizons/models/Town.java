package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Town model.
 */
public class Town {

    private String mapName;
    private Array<BuildingInstance> buildings;
    private float coins;
    private ObjectMap<Faction, Float> resources;
    private Building toConstruct;

    /**
     * @param mapName name of the town map
     */
    public Town(String mapName) {
        this.mapName = mapName;
        buildings = new Array<>();
        resources = new ObjectMap<>();
        toConstruct = null;

        // Starting resources
        coins = 10000f;
        for (Faction faction : Faction.values()) {
            resources.put(faction, 1000f);
        }
    }

    /**
     * Validate current location for the pending construction and create and add the
     * associated {@link BuildingInstance} to the town.
     * 
     * @param x X axis position
     * @param y Y axis position
     * @return the {@link BuildingInstance} created
     */
    public BuildingInstance validateConstruction(float x, float y) {
        addCoins(toConstruct.getStats(1).getCoinCost());
        addResources(toConstruct.getFaction(), toConstruct.getStats(1).getResourcesCost());
        BuildingInstance instance = new BuildingInstance(toConstruct, x, y);
        buildings.add(new BuildingInstance(toConstruct, x, y));
        toConstruct = null;
        return instance;
    }

    /**
     * Update coins and resources balance since the last frame
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
     * @return the name of the town map
     */
    public String getMapName() {
        return this.mapName;
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
    public Building getToConstruct() {
        return toConstruct;
    }

    /**
     * @param building to be placed
     */
    public void setToConstruct(Building building) {
        toConstruct = building;
    }

}
