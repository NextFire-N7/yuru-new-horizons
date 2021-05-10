package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
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

    /**
     * @param game the game instance
     */
    public GameScreen(YuruNewHorizons game) {
        this.game = game;
        game.setGameScreen(this);

        // Summon stages
        gameStage = new GameStage(game);
        mapStage = new MapStage(game);
        placingStage = new PlacingStage(game);

        // Inputs
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(gameStage);
        inputMultiplexer.addProcessor(mapStage);
        inputMultiplexer.addProcessor(placingStage);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
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
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

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
    }

}
