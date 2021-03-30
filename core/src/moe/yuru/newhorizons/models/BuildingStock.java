package moe.yuru.newhorizons.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import moe.yuru.newhorizons.YuruNewHorizons;

public class BuildingStock {

    private Array<Building> stock;

    public BuildingStock(YuruNewHorizons game) {
        stock = new Json().fromJson(Array.class, Gdx.files.internal("json/building_stock.json"));
    }

    public Array<Building> getStock() {
        return stock;
    }

}
