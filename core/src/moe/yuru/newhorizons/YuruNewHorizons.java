package moe.yuru.newhorizons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class YuruNewHorizons extends Game {

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private FPSLogger fpslogger;

    @Override
    public void create() {
        // La Camera fait la traduction entre notre code en 1280x720 et la taille réelle
        // de la fenêtre
        // Le Viewport permet de garder l'aspect ratio quand on resize la fenêtre
        // le SpriteBatch enverra les instructions au moteur de rendu
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        viewport = new FitViewport(1280, 720, camera);
        batch = new SpriteBatch();

        fpslogger = new FPSLogger();

        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render(); // important!
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public FPSLogger getFpslogger() {
        return fpslogger;
    }

    public Viewport getViewport() {
        return viewport;
    }

}
