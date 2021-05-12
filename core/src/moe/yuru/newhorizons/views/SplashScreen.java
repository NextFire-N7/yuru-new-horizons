package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import moe.yuru.newhorizons.YuruNewHorizons;

/**
 * Dumb splash {@link Screen}. Necessary on html to be able to play music in
 * modern browsers.
 * 
 * @author NextFire
 */
public class SplashScreen implements Screen {

    private YuruNewHorizons game;
    private BitmapFont font;

    /**
     * @param game the game instance
     */
    public SplashScreen(YuruNewHorizons game) {
        this.game = game;

        // Fonts...
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 40;
        parameter.borderWidth = 5;
        parameter.borderColor = Color.valueOf("90CCDB");
        font = game.getFontGenerator().generateFont(parameter);
    }

    @Override
    public void show() {
        // Click anywhere to access the main menu
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                super.touchDown(screenX, screenY, pointer, button);
                dispose();
                game.setScreen(game.getMainMenuScreen());
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        game.getFpslogger().log();
        game.getCamera().update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Drawing
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
