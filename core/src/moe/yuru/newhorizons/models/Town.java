package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;

/**
 * Town model. It keeps player's {@link BuildingInstance}s and tracks his
 * resources.
 * 
 * @author NextFire
 */
public class Town {

    private String mapName;

    private ObjectSet<BuildingInstance> buildings;

    private float coins;
    private ObjectMap<String, Float> resources;

    private float coinsPerSecond;
    private ObjectMap<String, Float> resourcesPerSecond;

    public Town() {
    }

    /**
     * Creates a new town.
     * 
     * @param mapName name of the town map
     */
    public Town(String mapName) {
        this.mapName = mapName;

        // Init fields
        buildings = new ObjectSet<>();
        resources = new ObjectMap<>();
        coinsPerSecond = 0f;
        resourcesPerSecond = new ObjectMap<>();

        // Starting resources
        coins = 10000f;
        for (Faction faction : Faction.values()) {
            resources.put(faction.name(), 1000f);
        }
    }

    /**
     * Updates coins and resources balance since the last frame
     *
     * @param delta last frametime
     */
    public void update(float delta) {
        // Coins
        addCoins(delta * coinsPerSecond);
        // Faction resources
        for (Faction faction : Faction.values()) {
            addResources(faction, delta * resourcesPerSecond.get(faction.name(), 0f));
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
            rpsPerFaction = resourcesPerSecond.get(instance.getModel().getFaction().name(), 0f);
            rpsPerFaction += instance.getStats().getResourcesPerSecond();
            resourcesPerSecond.put(instance.getModel().getFaction().name(), rpsPerFaction);
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
    public ObjectMap<String, Float> getResources() {
        return resources;
    }

    /**
     * @param faction {@link Faction}
     * @return {@code faction} ressource balance
     */
    public float getResources(Faction faction) {
        return resources.get(faction.name());
    }

    /**
     * @param faction to add resources
     * @param amount  of resources to add
     */
    public void addResources(Faction faction, float amount) {
        resources.put(faction.name(), resources.get(faction.name()) + amount);
    }

}
