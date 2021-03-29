package moe.yuru.newhorizons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.VisUI.SkinScale;

/**
 * Yuru New Horizons game class.
 */
public class YuruNewHorizons extends Game {

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;

    private FPSLogger fpslogger;

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

        setMusicVolume(0.25f);
        setSoundVolume(0.50f);

        this.setScreen(new SplashScreen(this));
    }

    @Override
    public void render() {
        super.render(); // important!
    }

    @Override
    public void dispose() {
        VisUI.dispose();
        batch.dispose();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public FPSLogger getFpslogger() {
        return fpslogger;
    }

    public Float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(Float musicVolume) {
        this.musicVolume = musicVolume;
    }

    public Float getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(Float soundVolume) {
        this.soundVolume = soundVolume;
    }

}
