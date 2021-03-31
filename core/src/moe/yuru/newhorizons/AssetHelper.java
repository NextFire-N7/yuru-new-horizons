package moe.yuru.newhorizons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import moe.yuru.newhorizons.models.BuildingModel;
import moe.yuru.newhorizons.models.Town;

/**
 * Helper class, returns assets linked to models.
 */
public class AssetHelper {

    /**
     * Get the character {@link Texture} of the given {@link BuildingModel}.
     * 
     * @param model the building model
     * @return the {@link Texture} of its character
     */
    public static Texture getCharaTexture(BuildingModel model) {
        Texture charaTexture = new Texture(Gdx.files.internal("characters/" + model.getId() + "_chara.png"));
        charaTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return charaTexture;
    }

    /**
     * Get the cover background {@link Texture} of the given {@link BuildingModel}.
     * 
     * @param model the building model
     * @return the {@link Texture} of its cover background
     */
    public static Texture getCoverTexture(BuildingModel model) {
        Texture coverTexture = new Texture(Gdx.files.internal("characters/" + model.getId() + "_cover.png"));
        coverTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return coverTexture;
    }

    /**
     * Get the icon {@link Texture} of the given {@link BuildingModel}.
     * 
     * @param model the building model
     * @return the {@link Texture} of its icon
     */
    public static Texture getIconTexture(BuildingModel model) {
        Texture iconTexture = new Texture(Gdx.files.internal("characters/" + model.getId() + "_icon.png"));
        iconTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return iconTexture;
    }

    /**
     * Get the character {@link Sound} of the given {@link BuildingModel}.
     * 
     * @param model the building model
     * @return the {@link Sound} of its character
     */
    public static Sound getCharaSound(BuildingModel model) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("characters/" + model.getId() + "_sound.mp3"));
        return sound;
    }

    /**
     * Get the layout {@link Texture} of the given {@link Town}.
     * 
     * @param town the town
     * @return the {@link Texture} of its layout
     */
    public static Texture getLayoutTexture(Town town) {
        Texture layoutTexture = new Texture(Gdx.files.internal("layouts/" + town.getLayoutName() + ".png"));
        layoutTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return layoutTexture;
    }

}
