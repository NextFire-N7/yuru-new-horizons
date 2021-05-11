package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import moe.yuru.newhorizons.YuruNewHorizons;

/**
 * Game mode selection.
 * @author DinoGurnari
 */
public class GameModeSelectionScreen implements Screen {

    private YuruNewHorizons game;
    private GameModeSelectionStage gModeSelectionStage;
    private Music theme;
    private Texture background;

    public GameModeSelectionScreen(YuruNewHorizons game) {
        this.game = game;

        this.gModeSelectionStage = new GameModeSelectionStage(game);

        background = new Texture(Gdx.files.internal("main_menu.png"));
        background.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        // Charger la BGM (sur disque)
        // Les assets sont dans core/assets/
        theme = Gdx.audio.newMusic(Gdx.files.internal("yuru_theme.mp3"));
        theme.setLooping(true);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(gModeSelectionStage);
        theme.setVolume(game.getMusicVolume());
        theme.play();
        
    }

    @Override
    public void render(float delta) {
        game.getFpslogger().log();
        // maj des données de la camera
        game.getCamera().update();

        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, 1280, 720);
        game.getBatch().end();
        
        // appelle les methodes act des acteurs de la scène si définies
        gModeSelectionStage.act(delta);
        // dessine la scène
        gModeSelectionStage.draw();
        
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
        theme.pause();
        
    }

    @Override
    public void dispose() {
        theme.dispose();
        background.dispose();
        gModeSelectionStage.dispose();
        
    }
    
}
