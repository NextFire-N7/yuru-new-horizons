package moe.yuru.newhorizons.utils;

import moe.yuru.newhorizons.models.TownContent;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class SaveGame {
    /**
     * Save the town into a json file
     * @param town
     */
    public static void save(TownContent town) {

        // Creation of a json containing the town
        Json json = new Json();
        json.setOutputType(OutputType.json);
        //json.setElementType(Ress.class, "value", float.class);
        String jeustr = json.prettyPrint(town);

        // Save the json in a file
        FileHandle file = Gdx.files.local("core/assets/save.json");
        file.writeString(jeustr, false); 

    }

    /**
     * Test method used to check that json are well saved and loaded
     * @param townContent
     */
    public static void testjson(TownContent townContent) {
        Json json = new Json();
        json.setOutputType(OutputType.json);
        //json.setElementType(Ress.class, "value", float.class);
        System.out.println(json.prettyPrint(townContent));
    }

    /**
     * Load a saved game
     */
    public static TownContent load() {
        Json json = new Json();
        FileHandle file = Gdx.files.local("core/assets/save.json");
        TownContent town = json.fromJson(TownContent.class, file.readString());
        System.out.println("load oui");
        return town;
    }

}
