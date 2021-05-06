package moe.yuru.newhorizons.utils;

import moe.yuru.newhorizons.models.GameModel;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class SaveGame {
    
    public static void save(GameModel jeu) {

        Json json = new Json();
        //json.setElementType(jeu.class, "numbers", PhoneNumber.class);
        String jeustr = json.toJson(jeu);

        FileHandle file = Gdx.files.local("save.json");
        file.writeString(jeustr, true); 

    }

}
