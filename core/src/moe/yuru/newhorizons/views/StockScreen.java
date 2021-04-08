package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import moe.yuru.newhorizons.YuruNewHorizons;

/**
 * New building selector {@link Screen}.
 */
public class StockScreen implements Screen {

    private YuruNewHorizons game;
    private StockStage stockStage;

    private Texture background;

    /**
     * @param game the game instance
     */
    public StockScreen(YuruNewHorizons game) {
        this.game = game;
        stockStage = new StockStage(game);

        background = new Texture(Gdx.files.internal("main_menu.png"));
        background.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stockStage);
    }

    @Override
    public void render(float delta) {
        game.getFpslogger().log();
        game.getCamera().update();

        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, 1280, 720);
        game.getBatch().end();

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
