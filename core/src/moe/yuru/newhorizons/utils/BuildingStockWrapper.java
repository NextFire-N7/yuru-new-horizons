package moe.yuru.newhorizons.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import moe.yuru.newhorizons.models.Building;

/**
 * Wrapper class for the building stock json deserialization.
 */
public class BuildingStockWrapper {

    /**
     * Deserialize the {@code building_stock.json} to its {@link Array}
     * buildingStock.
     */
    @SuppressWarnings("unchecked")
    private static Array<Building> buildingStock = new Json().fromJson(Array.class, Building.class,
            Gdx.files.internal("building_stock.json"));

    private BuildingStockWrapper() {
    }

    /**
     * @return the building stock deserialized from {@code building_stock.json}
     */
    public static Array<Building> getBuildingStock() {
        return buildingStock;
    }

}
