package moe.yuru.newhorizons.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public final class BuildingStock {

    private static final Array<BuildingModel> stock = new Json().fromJson(Array.class, BuildingModel.class,
            Gdx.files.internal("json/building_stock.json"));

    private BuildingStock() {
    }

    public static Array<BuildingModel> getStock() {
        return stock;
    }

}
