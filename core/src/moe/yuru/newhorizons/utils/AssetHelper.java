package moe.yuru.newhorizons.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import moe.yuru.newhorizons.models.Building;
import moe.yuru.newhorizons.models.Town;

/**
 * Helper class, returns assets linked to models.
 */
public class AssetHelper {

    private static final String CHARACTERS_PATH = "characters/";
    private static final String MAPS_PATH = "maps/";

    private AssetHelper() {
    }

    /**
     * Get the character {@link Texture} of the given {@link Building}.
     * 
     * @param model the building model
     * @return the {@link Texture} of its character
     */
    public static Texture getCharaTexture(Building model) {
        Texture charaTexture = new Texture(Gdx.files.internal(CHARACTERS_PATH + model.getId() + "_chara.png"));
        charaTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return charaTexture;
    }

    /**
     * Get the cover background {@link Texture} of the given {@link Building}.
     * 
     * @param model the building model
     * @return the {@link Texture} of its cover background
     */
    public static Texture getCoverTexture(Building model) {
        Texture coverTexture = new Texture(Gdx.files.internal(CHARACTERS_PATH + model.getId() + "_cover.png"));
        coverTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return coverTexture;
    }

    /**
     * Get the icon {@link Texture} of the given {@link Building}.
     * 
     * @param model the building model
     * @return the {@link Texture} of its icon
     */
    public static Texture getIconTexture(Building model) {
        Texture iconTexture = new Texture(Gdx.files.internal(CHARACTERS_PATH + model.getId() + "_icon.png"));
        iconTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return iconTexture;
    }

    /**
     * Get the character {@link Sound} of the given {@link Building}.
     * 
     * @param model the building model
     * @return the {@link Sound} of its character
     */
    public static Sound getCharaSound(Building model) {
        return Gdx.audio.newSound(Gdx.files.internal(CHARACTERS_PATH + model.getId() + "_sound.mp3"));
    }

    /**
     * Get the map {@link Texture} of the given {@link Town}.
     * 
     * @param town the town
     * @return the {@link Texture} of its map
     */
    public static Texture getMapTexture(Town town) {
        Texture mapTexture = new Texture(Gdx.files.internal(MAPS_PATH + town.getMapName() + ".png"));
        mapTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return mapTexture;
    }

}
