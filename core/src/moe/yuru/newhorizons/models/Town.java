package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import moe.yuru.newhorizons.stages.LayoutStage;

/**
 * Town model.
 */
public class Town {

    private String layoutName;
    private Array<BuildingInstance> buildings;
    private float coins;
    private ObjectMap<Building.Faction, Float> resources;

    /**
     * @param layoutName name of the image used on {@link LayoutStage}
     */
    public Town(String layoutName) {
        this.layoutName = layoutName;
        buildings = new Array<>();
        resources = new ObjectMap<>();

        // Starting resources
        coins = 10000f;
        for (Building.Faction faction : Building.Faction.values()) {
            resources.put(faction, 1000f);
        }
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
     * @return the name of the layout image
     */
    public String getLayoutName() {
        return this.layoutName;
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
    public ObjectMap<Building.Faction, Float> getResources() {
        return resources;
    }

    /**
     * @param faction to add resources
     * @param amount  of resources to add
     */
    public void addResources(Building.Faction faction, float amount) {
        resources.put(faction, resources.get(faction) + amount);
    }

}
