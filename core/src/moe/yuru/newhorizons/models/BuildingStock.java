package moe.yuru.newhorizons.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public class BuildingStock {

    private Array<Building> stock;

    public BuildingStock() {
        stock = new Json().fromJson(Array.class, Building.class, Gdx.files.internal("json/building_stock.json"));
    }

    public Array<Building> getStock() {
        return stock;
    }

}
