package moe.yuru.newhorizons.views;

import java.util.Random;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.Building;
import moe.yuru.newhorizons.utils.AssetHelper;
import moe.yuru.newhorizons.utils.BuildingStockWrapper;

/**
 * Building character {@link Stage}.
 */
public class BuildingCharacterStage extends Stage {

    private static Random random = new Random();
    private static int lastNb = -1;

    private YuruNewHorizons game;
    private Texture charaTexture;
    private Image charaImage;
    private Sound charaSound;

    /**
     * Creates a random character stage.
     * 
     * @param game the game instance
     */
    public BuildingCharacterStage(YuruNewHorizons game) {
        this(game, randomCharaDontRepeat(BuildingStockWrapper.getBuildingStock()));
    }

    /**
     * Creates a character stage for the specified building in input.
     * 
     * @param game          the game instance
     * @param buildingModel building of the character
     */
    public BuildingCharacterStage(YuruNewHorizons game, Building buildingModel) {
        super(game.getViewport(), game.getBatch());
        this.game = game;
        setChara(buildingModel);
    }

    @Override
    public void dispose() {
        super.dispose();
        charaTexture.dispose();
        charaSound.dispose();
    }

    /**
     * Repeats character sound on each click on the image.
     */
    public void addRepeatCharaSoundListener() {
        this.charaImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                playCharaSound();
            }
        });
    }

    /**
     * Sets a random character on each click on the image.
     */
    public void addRandomCharaListener() {
        this.charaImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                charaSound.stop();
                charaImage.remove();
                charaTexture.dispose();
                charaSound.dispose();
                setChara(randomCharaDontRepeat(BuildingStockWrapper.getBuildingStock()));
                addRandomCharaListener();
                playCharaSound();
            }
        });
    }

    /**
     * Plays the character sound.
     */
    public void playCharaSound() {
        charaSound.stop();
        charaSound.play(game.getSoundVolume());
    }

    /**
     * Sets attributs of the given character to the stage.
     * 
     * @param buildingModel building of the character
     */
    private void setChara(Building buildingModel) {
        charaTexture = AssetHelper.getCharaTexture(buildingModel);

        charaImage = new Image(charaTexture);
        adjustImageSize(charaImage);
        charaImage.setPosition(300, 0, Align.bottom);
        addActor(charaImage);

        charaSound = AssetHelper.getCharaSound(buildingModel);
    }

    /**
     * Gets random building without repetition.
     * 
     * @param buildingStock the building stock of the game
     * @return a building model
     */
    private static Building randomCharaDontRepeat(Array<Building> buildingStock) {
        int index;
        do {
            index = random.nextInt(buildingStock.size);
        } while (index == lastNb);
        lastNb = index;
        return buildingStock.get(index);
    }

    /**
     * Fits the image nicely on the stage.
     * 
     * @param image the image which size is to be adjusted
     */
    private void adjustImageSize(Image image) {
        float scaling = 0.9f * this.getHeight() / image.getHeight();
        image.setSize(scaling * image.getWidth(), scaling * image.getHeight());
    }

}
