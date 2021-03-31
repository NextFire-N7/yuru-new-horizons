package moe.yuru.newhorizons.screens;

import java.util.Random;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.BuildingModel;
import moe.yuru.newhorizons.models.BuildingStockWrapper;

/**
 * Character stage with character image and sound.
 */
public class BuildingCharacterStage extends Stage {

    private static Random random = new Random();
    private static int lastNb = -1;

    private final YuruNewHorizons game;

    private Texture charaTexture;
    private Image charaImage;
    private Sound charaSound;

    /**
     * Create a random character stage
     */
    public BuildingCharacterStage(final YuruNewHorizons game) {
        this(game, randomCharaDontRepeat(game.getBuildingStockWrapper()));
    }

    /**
     * Create a stage for the character in input
     * 
     * @param buildingModel name of the character
     */
    public BuildingCharacterStage(final YuruNewHorizons game, BuildingModel buildingModel) {
        super(game.getViewport(), game.getBatch());
        this.game = game;
        setChara(buildingModel);
    }

    /**
     * Repeat character sound on each click on the image
     */
    public void addRepeatCharaSoundListener() {
        this.charaImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playCharaSound();
            }
        });
    }

    /**
     * Set a random character on each click on the image
     */
    public void addRandomCharaListener() {
        this.charaImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                charaSound.stop();
                charaImage.remove();
                charaTexture.dispose();
                charaSound.dispose();
                setChara(randomCharaDontRepeat(game.getBuildingStockWrapper()));
                addRandomCharaListener();
                playCharaSound();
            }
        });
    }

    /**
     * Play the character sound
     */
    public void playCharaSound() {
        charaSound.stop();
        charaSound.play(game.getSoundVolume());
    }

    /**
     * Set attributs of the given character to the stage
     * 
     * @param buildingModel name of the character
     */
    private void setChara(BuildingModel buildingModel) {
        charaTexture = buildingModel.getCharaTexture();

        charaImage = new Image(charaTexture);
        adjustImageSize(charaImage);
        charaImage.setPosition(300, 0, Align.bottom);
        addActor(charaImage);

        charaSound = buildingModel.getCharaSound();
    }

    /**
     * Get random character name without repetition.
     * 
     * @return a character name
     */
    private static BuildingModel randomCharaDontRepeat(BuildingStockWrapper buildingStock) {
        int index;
        do {
            index = random.nextInt(buildingStock.getBuildingArray().size);
        } while (index == lastNb);
        lastNb = index;
        return buildingStock.getBuildingArray().get(index);
    }

    /**
     * Fit the image nicely on the stage
     * 
     * @param image image which size is to be adjusted
     */
    private void adjustImageSize(Image image) {
        float scaling = 0.9f * this.getHeight() / image.getHeight();
        image.setSize(scaling * image.getWidth(), scaling * image.getHeight());
    }

    @Override
    public void dispose() {
        super.dispose();
        charaTexture.dispose();
        charaSound.dispose();
    }

}
