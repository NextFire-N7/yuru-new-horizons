package moe.yuru.newhorizons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.VisUI.SkinScale;

import moe.yuru.newhorizons.models.BuildingModel;
import moe.yuru.newhorizons.models.BuildingStockWrapper;
import moe.yuru.newhorizons.models.Player;
import moe.yuru.newhorizons.models.Town;
import moe.yuru.newhorizons.screens.SplashScreen;

/**
 * Yuru New Horizons inherited {@link Game} class.
 */
public class YuruNewHorizons extends Game {

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private FPSLogger fpslogger;

    private FreeTypeFontGenerator fontGenerator;

    private Float musicVolume;
    private Float soundVolume;

    private BuildingStockWrapper buildingStockWrapper;
    private Player player;
    private Town town;

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
        
        buildingStockWrapper = new BuildingStockWrapper();

        this.setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        VisUI.dispose();
        batch.dispose();
    }

    public ObjectMap<String, BuildingModel> getBuildingStock() {
        return buildingStockWrapper.getBuildingStock();
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

    public FreeTypeFontGenerator getFontGenerator() {
        return fontGenerator;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

}
