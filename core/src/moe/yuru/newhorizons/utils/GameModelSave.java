package moe.yuru.newhorizons.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

import moe.yuru.newhorizons.models.GameModel;

/**
 * {@link GameModel} JSON serializer/dezerialiser.
 * 
 * @author NextFire
 */
public class GameModelSave {

    private GameModelSave() {
    }

    /**
     * Saves the given model to a JSON file. Town + Opponent.
     * 
     * @param model {@link GameModel} to save
     */
    public static void save(GameModel model) {
        Json json = new Json();
        json.setOutputType(OutputType.json);
        String prettySave = json.prettyPrint(model);
        Gdx.files.local("save.json").writeString(prettySave, false);
    }

    /**
     * @return previously saved {@link GameModel}
     */
    public static GameModel load() {
        return new Json().fromJson(GameModel.class, Gdx.files.local("save.json"));
    }

}
