package moe.yuru.newhorizons.utils;

import moe.yuru.newhorizons.models.Town;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class SaveGame {
    /**
     * Save the town into a json file
     * @param town
     */
    public static void save(Town town) {

        Json json = new Json();
        json.setOutputType(OutputType.json);
        //json.setElementType(Ress.class, "value", float.class);
        String jeustr = json.prettyPrint(town);

        FileHandle file = Gdx.files.local("core/assets/save.json");
        file.writeString(jeustr, false); 

    }

}
