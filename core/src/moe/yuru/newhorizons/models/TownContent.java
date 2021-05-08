package moe.yuru.newhorizons.models;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;

/**
 * This class contains the data of the game and is used to save and load a game
 */
public class TownContent {
    private boolean isSolo;
    private String mapName;

    private ObjectSet<BuildingInstance> buildings;

    private float coins;
    private ObjectMap<Faction, Float> resources;

    private Float coinsPerSecond;
    private ObjectMap<Faction, Float> resourcesPerSecond;

    private Building toPlace;

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public ObjectSet<BuildingInstance> getBuildings() {
        return buildings;
    }

    public void setBuildings(ObjectSet<BuildingInstance> buildings) {
        this.buildings = buildings;
    }

    public float getCoins() {
        return coins;
    }

    public void setCoins(float coins) {
        this.coins = coins;
    }

    public ObjectMap<Faction, Float> getResources() {
        return resources;
    }

    public void setResources(ObjectMap<Faction, Float> resources) {
        this.resources = resources;
    }

    public Float getCoinsPerSecond() {
        return coinsPerSecond;
    }

    public void setCoinsPerSecond(Float coinsPerSecond) {
        this.coinsPerSecond = coinsPerSecond;
    }

    public ObjectMap<Faction, Float> getResourcesPerSecond() {
        return resourcesPerSecond;
    }

    public void setResourcesPerSecond(ObjectMap<Faction, Float> resourcesPerSecond) {
        this.resourcesPerSecond = resourcesPerSecond;
    }

    public Building getToPlace() {
        return toPlace;
    }

    public void setToPlace(Building toPlace) {
        this.toPlace = toPlace;
    }

    public boolean getGameMode() {
        return isSolo;
    }

    public void setGameMode(boolean b) {
        this.isSolo = b;
    }

    
}
