package moe.yuru.newhorizons.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;

public class BuildingStockWrapper {

    private ObjectMap<String, BuildingModel> buildingStock;

    public BuildingStockWrapper() {
        Array<BuildingModel> buildingArray = new Json().fromJson(Array.class, BuildingModel.class,
                Gdx.files.internal("building_stock.json"));

        buildingStock = new ObjectMap<>();
        for (BuildingModel model : buildingArray) {
            buildingStock.put(model.getId(), model);
        }
    }

    public ObjectMap<String, BuildingModel> getBuildingStock() {
        return buildingStock;
    }

}
