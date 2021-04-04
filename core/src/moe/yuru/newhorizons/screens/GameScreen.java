package moe.yuru.newhorizons.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.GameSolo;
import moe.yuru.newhorizons.models.Town;
import moe.yuru.newhorizons.stages.LayoutStage;

/**
 * Game {@link Screen}. This is where the player will spend most of his time.
 */
public class GameScreen implements Screen {

    private YuruNewHorizons game;
    private LayoutStage layoutStage;

    /**
     * @param game the game instance
     */
    public GameScreen(YuruNewHorizons game) {
        this.game = game;
        game.setModel(new GameSolo(new Town("east-a1")));
        layoutStage = new LayoutStage(game);
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float delta) {
        game.getFpslogger().log();
        game.getCamera().update();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getModel().getTown().updateBalance(delta);
        game.getModel().updateEnemy(delta);

        layoutStage.act(delta);
        layoutStage.draw();
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
        layoutStage.dispose();
    }

}
