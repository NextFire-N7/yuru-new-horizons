package moe.yuru.newhorizons.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;

public class BuildingStockWrapper {

    private Array<BuildingModel> buildingArray;
    private ObjectMap<String, BuildingModel> buildingStock;

    public BuildingStockWrapper() {
        buildingArray = new Json().fromJson(Array.class, BuildingModel.class,
                Gdx.files.internal("json/building_stock.json"));

        buildingStock = new ObjectMap<>();
        for (BuildingModel model : buildingArray) {
            buildingStock.put(model.getId(), model);
        }
    }

    public Array<BuildingModel> getBuildingArray() {
        return buildingArray;
    }

    public ObjectMap<String, BuildingModel> getBuildingStock() {
        return buildingStock;
    }

}
