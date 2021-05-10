package moe.yuru.newhorizons.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

import moe.yuru.newhorizons.models.GameModel;

/**
 * {@link GameModel} JSON serializer/dezerialiser.
 * 
 * @author DinoGurnari
 * @author NextFire
 */
public class GameModelSave {

    private static Json json = new Json();
    // Save file: ~/.prefs/YuruNewHorizons
    private static Preferences prefs = Gdx.app.getPreferences("YuruNewHorizons");

    private GameModelSave() {
    }

    /**
     * Saves the given model to a JSON string in preferences. Town + Opponent.
     * 
     * @param model {@link GameModel} to save
     */
    public static void save(GameModel model) {
        json.setOutputType(OutputType.json);
        String prettySave = json.prettyPrint(model);
        prefs.putString("save", prettySave);
        prefs.flush();
    }

    /**
     * @return previously saved {@link GameModel}
     */
    public static GameModel load() {
        return json.fromJson(GameModel.class, prefs.getString("save"));
    }

}
