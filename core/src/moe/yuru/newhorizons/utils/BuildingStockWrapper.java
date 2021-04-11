package moe.yuru.newhorizons.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;

import moe.yuru.newhorizons.models.Building;
import moe.yuru.newhorizons.models.Faction;

/**
 * Wrapper class for the building stock json deserialization.
 */
public final class BuildingStockWrapper {

    /**
     * Deserializes the {@code building_stock.json}.
     */
    @SuppressWarnings("unchecked")
    private static Array<Building> buildingStock = new Json().fromJson(Array.class, Building.class,
            Gdx.files.internal("building_stock.json"));

    private BuildingStockWrapper() {
    }

    /**
     * @return the building stock array
     */
    public static Array<Building> getBuildingStock() {
        return buildingStock;
    }

    /**
     * @return a building stock mapped by ID
     */
    public static ObjectMap<String, Building> getBuildingStockIdMap() {
        ObjectMap<String, Building> buildingsMap = new ObjectMap<>();
        for (Building building : buildingStock) {
            buildingsMap.put(building.getId(), building);
        }
        return buildingsMap;
    }

    /**
     * @return a building stock mapped by {@link Faction}
     */
    public static ObjectMap<Faction, Array<Building>> getBuildingStockFactionMap() {
        ObjectMap<Faction, Array<Building>> buildingsMap = new ObjectMap<>();
        for (Building building : buildingStock) {
            Array<Building> factionArray = buildingsMap.get(building.getFaction(), new Array<>());
            factionArray.add(building);
            buildingsMap.put(building.getFaction(), factionArray);
        }
        return buildingsMap;
    }

}
