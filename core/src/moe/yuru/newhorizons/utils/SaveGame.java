package moe.yuru.newhorizons.utils;

import moe.yuru.newhorizons.models.Town;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class SaveGame {
    /**
     * Save the town into a json file
     * @param town
     */
    public static void save(Town town) {

        Json json = new Json();
        //json.setElementType(jeu.class, "numbers", PhoneNumber.class);
        String jeustr = json.toJson(town);

        FileHandle file = Gdx.files.local("assets/save.json");
        file.writeString(jeustr, true); 

    }

}
