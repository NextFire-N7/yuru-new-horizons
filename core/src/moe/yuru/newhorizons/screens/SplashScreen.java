package moe.yuru.newhorizons.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import moe.yuru.newhorizons.YuruNewHorizons;

/**
 * Splash screen of the {@link YuruNewHorizons}.
 * 
 * Necessary on html to be able to play music in modern browsers.
 */
public class SplashScreen implements Screen {

    private final YuruNewHorizons game;
    private BitmapFont font;

    public SplashScreen(final YuruNewHorizons game) {
        this.game = game;
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 40;
        parameter.borderWidth = 5;
        parameter.borderColor = Color.valueOf("90CCDB");
        font = game.getFontGenerator().generateFont(parameter);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                game.getScreen().dispose();
                game.setScreen(new MainMenuScreen(game));
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        game.getFpslogger().log();
        game.getCamera().update();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();
        font.draw(game.getBatch(), "Click to continue...", 850, 50);
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        game.getViewport().update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        font.dispose();
    }

}
