package moe.yuru.newhorizons.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Wrapper class to make easier the json deserialization.
 */
public class BuildingStockWrapper {

    private ObjectMap<String, Building> buildingStock;

    /**
     * Deserialize the {@code building_stock.json} to its {@link ObjectMap}
     * buildingStock
     */
    public BuildingStockWrapper() {
        @SuppressWarnings("unchecked")
        Array<Building> buildingArray = new Json().fromJson(Array.class, Building.class,
                Gdx.files.internal("building_stock.json"));

        buildingStock = new ObjectMap<>();
        for (Building model : buildingArray) {
            buildingStock.put(model.getId(), model);
        }
    }

    /**
     * @return an {@link ObjectMap} which keys are {@link Building#id} and values
     *         the models themselves
     */
    public ObjectMap<String, Building> getBuildingStock() {
        return buildingStock;
    }

}
