package moe.yuru.newhorizons.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.GameSolo;
import moe.yuru.newhorizons.models.Town;
import moe.yuru.newhorizons.stages.GameStage;
import moe.yuru.newhorizons.stages.MapStage;

/**
 * Game {@link Screen}. This is where the player will spend most of his time.
 */
public class GameScreen implements Screen {

    private YuruNewHorizons game;
    private MapStage mapStage;
    private GameStage gameStage;

    private InputMultiplexer inputMultiplexer;

    /**
     * @param game the game instance
     */
    public GameScreen(YuruNewHorizons game) {
        this.game = game;

        game.setModel(new GameSolo(new Town("east-a1"))); // TODO: for debug purpose heh
        mapStage = new MapStage(game);
        gameStage = new GameStage(game);

        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(mapStage);
        inputMultiplexer.addProcessor(gameStage);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        game.getFpslogger().log();
        game.getCamera().update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getModel().getTown().updateBalance(delta);
        game.getModel().updateEnemy(delta);

        mapStage.act(delta);
        mapStage.draw();

        gameStage.act(delta);
        gameStage.draw();
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
        mapStage.dispose();
        gameStage.dispose();
    }

}
