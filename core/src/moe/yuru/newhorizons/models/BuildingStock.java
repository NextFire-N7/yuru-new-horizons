package moe.yuru.newhorizons.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public class BuildingStock {

    private Array<BuildingModel> stock;

    public BuildingStock() {
        stock = new Json().fromJson(Array.class, BuildingModel.class, Gdx.files.internal("json/building_stock.json"));
    }

    public Array<BuildingModel> getStock() {
        return stock;
    }

}
