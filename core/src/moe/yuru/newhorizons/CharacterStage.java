package moe.yuru.newhorizons;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

/**
 * Character stage with character image and sound
 */
public class CharacterStage extends Stage {

    private static String[] names = { "renchon", "shimarin" };
    private static Random random = new Random();

    private Texture charaTexture;
    private Image charaImage;
    private Sound charaSound;

    /**
     * Create a random character stage
     */
    public CharacterStage() {
        this(names[random.nextInt(names.length)]);
    }

    /**
     * Create a stage for the character in input
     * @param name name of the character
     */
    public CharacterStage(String name) {
        super();
        setChara(name);
    }

    /**
     * Repeat character sound on each click on the image
     */
    public void addRepeatCharaSoundListener() {
        this.charaImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                charaSound.stop();
                charaSound.play();
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
                charaImage.remove();
                charaTexture.dispose();
                charaSound.dispose();
                setChara(names[random.nextInt(names.length)]);
                addRandomCharaListener();
            }
        });
    }

    /**
     * Set attributs of the given character to the stage
     * @param name name of the character
     */
    private void setChara(String name) {
        charaTexture = new Texture(Gdx.files.internal("images/" + name + ".png"));
        charaTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        charaImage = new Image(charaTexture);
        adjustImageSize(charaImage);
        charaImage.setPosition(300, 0, Align.bottom);
        addActor(charaImage);

        charaSound = Gdx.audio.newSound(Gdx.files.internal("audio/" + name + ".mp3"));
        charaSound.play();
    }

    /**
     * Fit the image nicely on the stage
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
