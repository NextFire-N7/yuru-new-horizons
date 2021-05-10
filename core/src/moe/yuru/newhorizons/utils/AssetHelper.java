package moe.yuru.newhorizons.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import moe.yuru.newhorizons.models.Building;
import moe.yuru.newhorizons.models.GameModel;
import moe.yuru.newhorizons.models.Town;

/**
 * Helper class, returns assets linked to models.
 * 
 * @author NextFire
 */
public final class AssetHelper {

    private static final String CHARACTERS_PATH = "characters/";
    private static final String MAPS_PATH = "maps/";

    private AssetHelper() {
    }

    /**
     * Gets the character {@link Texture} of the given {@link Building}.
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
     * Gets the cover background {@link Texture} of the given {@link Building}.
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
     * Gets the icon {@link Texture} of the given {@link Building}.
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
     * Gets the character {@link Sound} of the given {@link Building}.
     * 
     * @param model the building model
     * @return the {@link Sound} of its character
     */
    public static Sound getCharaSound(Building model) {
        return Gdx.audio.newSound(Gdx.files.internal(CHARACTERS_PATH + model.getId() + "_sound.mp3"));
    }

    /**
     * Gets the map {@link Texture} of the {@link Town} of a given
     * {@link GameModel}.
     * 
     * @param model the game model
     * @return the {@link Texture} of its map
     */
    public static Texture getTownMapTexture(GameModel model) {
        Texture mapTexture = new Texture(Gdx.files.internal(MAPS_PATH + model.getTownMapName() + ".png"));
        mapTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return mapTexture;
    }

}
