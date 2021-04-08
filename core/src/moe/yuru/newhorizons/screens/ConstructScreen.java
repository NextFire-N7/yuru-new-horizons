package moe.yuru.newhorizons.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.stages.ConstructStage;

public class ConstructScreen implements Screen {

    private YuruNewHorizons game;
    private ConstructStage constructStage;

    public ConstructScreen(YuruNewHorizons game) {
        this.game = game;
        constructStage = new ConstructStage(game);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(constructStage);
    }

    @Override
    public void render(float delta) {
        game.getFpslogger().log();
        game.getCamera().update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        constructStage.act(delta);
        constructStage.draw();
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
        constructStage.dispose();
    }

}
