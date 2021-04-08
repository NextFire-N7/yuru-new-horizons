package moe.yuru.newhorizons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.VisUI.SkinScale;

import moe.yuru.newhorizons.models.GameModel;
import moe.yuru.newhorizons.views.GameScreen;
import moe.yuru.newhorizons.views.SplashScreen;

/**
 * Yuru New Horizons {@link Game} view.
 */
public class YuruNewHorizons extends Game {

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private FPSLogger fpslogger;

    private FreeTypeFontGenerator fontGenerator;

    private GameModel model;
    private GameScreen gameScreen;

    private Float musicVolume;
    private Float soundVolume;

    @Override
    public void create() {
        VisUI.load(SkinScale.X2);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        viewport = new FitViewport(1280, 720, camera);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        fpslogger = new FPSLogger();

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("majuro_fino.ttf"));

        setMusicVolume(0.25f);
        setSoundVolume(0.50f);

        this.setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        VisUI.dispose();
        batch.dispose();
    }

    /**
     * @return the {@link OrthographicCamera} of the game
     */
    public OrthographicCamera getCamera() {
        return camera;
    }

    /**
     * @return the {@link Viewport} of the game
     */
    public Viewport getViewport() {
        return viewport;
    }

    /**
     * @return the {@link SpriteBatch} of the game
     */
    public SpriteBatch getBatch() {
        return batch;
    }

    /**
     * @return the {@link FPSLogger} of the game
     */
    public FPSLogger getFpslogger() {
        return fpslogger;
    }

    /**
     * @return the {@code majuro_fino.ttf} aka the Yuru Camp manga font
     *         {@link FreeTypeFontGenerator} of the game
     */
    public FreeTypeFontGenerator getFontGenerator() {
        return fontGenerator;
    }

    /**
     * @return the {@link GameModel} of the current game
     */
    public GameModel getModel() {
        return model;
    }

    /**
     * @param model the {@link GameModel} of the current game
     */
    public void setModel(GameModel model) {
        this.model = model;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    /**
     * @return the music volume of the game
     */
    public Float getMusicVolume() {
        return musicVolume;
    }

    /**
     * Set the music volume of the game.
     * 
     * @param musicVolume a {@link Float} between {@code 0.0f} and {@code 1.0f}
     */
    public void setMusicVolume(Float musicVolume) {
        this.musicVolume = musicVolume;
    }

    /**
     * @return the sound effects volume of the game
     */
    public Float getSoundVolume() {
        return soundVolume;
    }

    /**
     * Set the sound effects volume of the game.
     * 
     * @param soundVolume a {@link Float} between {@code 0.0f} and {@code 1.0f}
     */
    public void setSoundVolume(Float soundVolume) {
        this.soundVolume = soundVolume;
    }

}
