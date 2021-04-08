package moe.yuru.newhorizons.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.stages.StockStage;

public class StockScreen implements Screen {

    private YuruNewHorizons game;
    private StockStage stockStage;

    public StockScreen(YuruNewHorizons game) {
        this.game = game;
        stockStage = new StockStage(game);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stockStage);
    }

    @Override
    public void render(float delta) {
        game.getFpslogger().log();
        game.getCamera().update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stockStage.act(delta);
        stockStage.draw();
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
        stockStage.dispose();
    }

}
