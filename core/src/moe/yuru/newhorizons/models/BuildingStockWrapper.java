package moe.yuru.newhorizons.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Wrapper class to make easier the json deserialization.
 */
public class BuildingStockWrapper {

    private ObjectMap<String, BuildingModel> buildingStock;

    /**
     * Deserialize the {@code building_stock.json} to its {@link ObjectMap}
     * {@code buildingStock}
     */
    public BuildingStockWrapper() {
        Array<BuildingModel> buildingArray = new Json().fromJson(Array.class, BuildingModel.class,
                Gdx.files.internal("building_stock.json"));

        buildingStock = new ObjectMap<>();
        for (BuildingModel model : buildingArray) {
            buildingStock.put(model.getId(), model);
        }
    }

    /**
     * @return an {@link ObjectMap} which keys are {@link BuildingModel#id} and
     *         values the models themselves
     */
    public ObjectMap<String, BuildingModel> getBuildingStock() {
        return buildingStock;
    }

}
