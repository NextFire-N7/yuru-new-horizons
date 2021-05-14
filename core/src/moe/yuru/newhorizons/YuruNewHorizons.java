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

import moe.yuru.newhorizons.models.GameModel;
import moe.yuru.newhorizons.views.GameScreen;
import moe.yuru.newhorizons.views.SplashScreen;

/**
 * Yuru New Horizons {@link Game} entrypoint for all plateforms.
 * 
 * @author NextFire
 */
public class YuruNewHorizons extends Game {

    // Graphics
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private FPSLogger fpslogger;

    // Assets
    private FreeTypeFontGenerator fontGenerator;

    // Game model and screen
    private GameModel gameModel;
    private GameScreen gameScreen;

    // Volumes
    private float musicVolume;
    private float soundVolume;

    /**
     * Sole constructor. Should not be called. This class is only instantiated once
     * in libGDX starters classes, which directly call {@link #create} afterwards.
     */
    public YuruNewHorizons() {
        super();
    }

    @Override
    public void create() {
        // In libGDX, visual elements (labels, buttons,...) are provided by skins such
        // as VisUI
        VisUI.load();

        // The camera ensures we can render using our target resolution of 1280 by 720
        // no matter what the actual screen resolution is
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        // The viewport ensures our game looks good on whatever screen resolution or
        // aspect ratio
        viewport = new FitViewport(1280, 720, camera);

        // The SpriteBatch is a special class that is used to draw 2D images
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        // Only to flex our 60 fps
        fpslogger = new FPSLogger();

        // Using custom fonts in libGDX is really a pain, check their wiki
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("majuro_fino.ttf"));

        // (Un)Define(d) model and screen
        gameModel = null;
        gameScreen = null;

        // Default volume
        setMusicVolume(0.25f);
        setSoundVolume(0.50f);

        // Game window shows a new splash screen, time to play!
        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        VisUI.dispose();
        batch.dispose();
    }

    /**
     * If you need a Camera for any method call, use this one.
     * 
     * @return a {@link OrthographicCamera} defined once for all
     */
    public OrthographicCamera getCamera() {
        return camera;
    }

    /**
     * If you need a Viewport for any method call, use this one.
     * 
     * @return a {@link Viewport} defined once for all
     */
    public Viewport getViewport() {
        return viewport;
    }

    /**
     * Inside a Screen {@code render} method, drawing methods should be called
     * between this {@link SpriteBatch} {@code begin} and {@code end} method calls.
     * 
     * @return a SpriteBatch defined once for all
     */
    public SpriteBatch getBatch() {
        return batch;
    }

    /**
     * Its {@code log} method is to be used inside each Screen {@code render}
     * method.
     * 
     * @return the {@link FPSLogger}
     */
    public FPSLogger getFpslogger() {
        return fpslogger;
    }

    /**
     * @return {@code majuro_fino.ttf} also known as Yuru Camp manga font
     *         {@link FreeTypeFontGenerator}
     */
    public FreeTypeFontGenerator getFontGenerator() {
        return fontGenerator;
    }

    /**
     * @return current {@link GameModel}, can be {@code null}
     */
    public GameModel getGameModel() {
        return gameModel;
    }

    /**
     * Defines the model.
     * 
     * @param gameModel current {@link GameModel}
     */
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    /**
     * @return player's {@link GameScreen}, can be {@code null}
     */
    public GameScreen getGameScreen() {
        return gameScreen;
    }

    /**
     * Saves an access to the game screen of the game.
     * 
     * @param gameScreen player's {@link GameScreen}
     */
    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    /**
     * Should be used as argument for Sound's {@code play} method.
     * 
     * @return the music volume
     */
    public float getMusicVolume() {
        return musicVolume;
    }

    /**
     * @param musicVolume between {@code 0.0f} and {@code 1.0f}
     */
    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }

    /**
     * Should be used as argument for Music's {@code setVolume} method.
     * 
     * @return the sound effects volume
     */
    public float getSoundVolume() {
        return soundVolume;
    }

    /**
     * @param soundVolume between {@code 0.0f} and {@code 1.0f}
     */
    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
    }

}
