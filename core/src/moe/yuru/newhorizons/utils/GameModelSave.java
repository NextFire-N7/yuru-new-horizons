package moe.yuru.newhorizons.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

import moe.yuru.newhorizons.models.GameModel;

public class GameModelSave {

    private GameModelSave() {
    }

    public static void save(GameModel model) {
        Json json = new Json();
        json.setOutputType(OutputType.json);
        json.toJson(model, GameModel.class, Gdx.files.internal("save.json"));
    }

    public static GameModel load() {
        return new Json().fromJson(GameModel.class, Gdx.files.internal("save.json"));
    }

}
