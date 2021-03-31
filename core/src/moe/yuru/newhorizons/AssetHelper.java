package moe.yuru.newhorizons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import moe.yuru.newhorizons.models.BuildingModel;
import moe.yuru.newhorizons.models.Town;

public class AssetHelper {

    public static Texture getCharaTexture(BuildingModel model) {
        Texture charaTexture = new Texture(Gdx.files.internal("characters/" + model.getId() + "_chara.png"));
        charaTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return charaTexture;
    }

    public static Texture getCoverTexture(BuildingModel model) {
        Texture coverTexture = new Texture(Gdx.files.internal("characters/" + model.getId() + "_cover.png"));
        coverTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return coverTexture;
    }

    public static Texture getIconTexture(BuildingModel model) {
        Texture iconTexture = new Texture(Gdx.files.internal("characters/" + model.getId() + "_icon.png"));
        iconTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return iconTexture;
    }

    public static Sound getCharaSound(BuildingModel model) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("characters/" + model.getId() + "_sound.mp3"));
        return sound;
    }

    public static Texture getLayoutTexture(Town town) {
        Texture layoutTexture = new Texture(Gdx.files.internal("layouts/" + town.getLayoutName() + ".png"));
        layoutTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return layoutTexture;
    }

}
