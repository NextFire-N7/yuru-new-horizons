package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

import moe.yuru.newhorizons.YuruNewHorizons;

/**
 * Game screen. This is where the player will spend most of his time.
 * 
 * @author NextFire
 */
public class GameScreen implements Screen {

    private YuruNewHorizons game;
    private GameStage gameStage;
    private MapStage mapStage;
    private PlacingStage placingStage;
    private InputMultiplexer inputMultiplexer;
    private Music theme;

    /**
     * @param game the game instance
     */
    public GameScreen(YuruNewHorizons game) {
        this.game = game;

        // Summon stages
        gameStage = new GameStage(game);
        mapStage = new MapStage(game);
        placingStage = new PlacingStage(game);

        // Inputs
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(gameStage);
        inputMultiplexer.addProcessor(mapStage);
        inputMultiplexer.addProcessor(placingStage);

        // Audio
        theme = Gdx.audio.newMusic(Gdx.files.internal("theme2.mp3"));
        theme.setLooping(true);
        theme.setVolume(game.getMusicVolume());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
        theme.play();
    }

    @Override
    public void render(float delta) {
        game.getFpslogger().log();
        game.getCamera().update();

        // Black background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update model
        game.getGameModel().update(delta);

        gameStage.act(delta);
        gameStage.draw();

        mapStage.act(delta);
        mapStage.draw();

        placingStage.act(delta);
        placingStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.getViewport().update(width, height);
    }

    @Override
    public void pause() {
        theme.pause();
    }

    @Override
    public void resume() {
        theme.play();
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        gameStage.dispose();
        mapStage.dispose();
        placingStage.dispose();
        theme.dispose();
    }

}
