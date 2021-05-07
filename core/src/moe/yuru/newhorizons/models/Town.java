package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import moe.yuru.newhorizons.utils.Event;

/**
 * Town model. Linked to a {@link GameModel}, it keeps player's
 * {@link BuildingInstance}s and tracks his resources.
 * 
 * @author NextFire
 */
public class Town implements Json.Serializable {

    private GameModel gameModel;
    private String mapName;

    private ObjectSet<BuildingInstance> buildings;

    private float coins;
    private ObjectMap<Faction, Float> resources;

    private Float coinsPerSecond;
    private ObjectMap<Faction, Float> resourcesPerSecond;

    private Building toPlace;

    /**
     * Creates a new town linked to the specified {@link GameModel}.
     * 
     * @param gameModel game model
     * @param mapName   name of the town map
     */
    public Town(GameModel gameModel, String mapName) {
        this.gameModel = gameModel;
        this.mapName = mapName;

        // Init fields
        buildings = new ObjectSet<>();
        resources = new ObjectMap<>();
        coinsPerSecond = 0f;
        resourcesPerSecond = new ObjectMap<>();
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
     * @return the building instance created
     */
    public void validateConstruction(float x, float y) {
        addCoins(toPlace.getStats(1).getCoinCost());
        addResources(toPlace.getFaction(), toPlace.getStats(1).getResourcesCost());

        BuildingInstance instance = new BuildingInstance(toPlace, x, y);
        buildings.add(instance);
        toPlace = null;
        gameModel.notifyListeners(new Event(this, "toPlace", null));
        updatePerSecond();
        gameModel.notifyListeners(new Event(this, "validated", instance));
    }

    /**
     * Updates coins and resources balance since the last frame
     *
     * @param delta last frametime
     */
    public void updateBalance(float delta) {
        // Coins
        addCoins(delta * coinsPerSecond);
        // Faction resources
        for (Faction faction : Faction.values()) {
            addResources(faction, delta * resourcesPerSecond.get(faction, 0f));
        }
    }

    /**
     * Updates total town coins and resources per second
     */
    public void updatePerSecond() {
        // Reset
        coinsPerSecond = 0f;
        resourcesPerSecond = new ObjectMap<>();
        // Update
        float rpsPerFaction;
        for (BuildingInstance instance : buildings) {
            coinsPerSecond += instance.getStats().getCoinsPerSecond();
            rpsPerFaction = resourcesPerSecond.get(instance.getModel().getFaction(), 0f);
            rpsPerFaction += instance.getStats().getResourcesPerSecond();
            resourcesPerSecond.put(instance.getModel().getFaction(), rpsPerFaction);
        }
    }

    /**
     * @return the name of the town map
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * @return the {@link BuildingInstance}s of this town
     */
    public ObjectSet<BuildingInstance> getBuildings() {
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
     * @param amount  of resources to add
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

    /**
     * Custom write methode for Town to make a custom serialization.
     */
    @Override
    public void write(Json json) {
        json.writeValue("MapName", mapName);
        json.writeValue("Buildings", buildings);
        json.writeValue("Coins", coins);
        json.writeValue("Ressources", resources);
        json.writeValue("Coins/s", coinsPerSecond);
        json.writeValue("Ressources/s",resourcesPerSecond);
        json.writeValue("Buildings to place", toPlace);

    }

    /**
     * Custom read methode for Town to make a custom serialization.
     */
    @Override
    public void read(Json json, JsonValue jsonData) {
        //TODO
        
    }

}
