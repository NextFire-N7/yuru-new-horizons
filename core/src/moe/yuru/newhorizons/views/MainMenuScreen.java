package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;

import moe.yuru.newhorizons.YuruNewHorizons;

/**
 * Main menu. English readers, sorry for French comments in this one.
 * 
 * @author NextFire
 */
public class MainMenuScreen implements Screen {

    private YuruNewHorizons game;
    private CharacterStage characterStage;
    private Stage rightStage;
    private Texture background;
    private BitmapFont font;
    private Music theme;
    private InputMultiplexer inputMultiplexer;

    /**
     * @param game the game instance
     */
    public MainMenuScreen(YuruNewHorizons game) {
        this.game = game;

        // See, it's a pain to use custom fonts...
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 60;
        parameter.borderWidth = 5;
        parameter.borderColor = Color.valueOf("E39256");
        font = game.getFontGenerator().generateFont(parameter);

        // Scène du perso à gauche
        characterStage = new CharacterStage(game);
        characterStage.addRandomCharaListener();

        // Scène à droite
        rightStage = null;

        // rajoute à l'inputMultiplexer le gestionnaire d'input de characterStage
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(characterStage);

        // Charger les images (en VRAM)
        background = new Texture(Gdx.files.internal("main_menu.png"));
        background.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        // Charger la BGM (sur disque)
        // Les assets sont dans core/assets/
        theme = Gdx.audio.newMusic(Gdx.files.internal("theme1.mp3"));
        theme.setLooping(true);
    }

    // Tout ce qui se passe au lancement du menu
    @Override
    public void show() {
        // Scène des menus à droite
        switchRightStage(new MainMenuStage(game, this));

        // règle l'inputMultiplexer comme gestionnaire des inputs du menu
        Gdx.input.setInputProcessor(inputMultiplexer);
        characterStage.playCharaSound();
        theme.setVolume(game.getMusicVolume());
        theme.play();
    }

    // Etapes effectuées pour chaque rendu d'une frame (boucle infinie sort of)
    @Override
    public void render(float delta) {
        game.getFpslogger().log();
        // maj des données de la camera
        game.getCamera().update();

        // Les instr de rendu entre begin et end seront faites en 1 coup (rendu
        // efficace, vive les fps)
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, 1280, 720);
        font.draw(game.getBatch(), "Yuru New Horizons", 600, 625);
        game.getBatch().end();

        // appelle les methodes act des acteurs de la scène si définies
        characterStage.act(delta);
        // dessine la scène
        characterStage.draw();

        // pareil avec le menu
        rightStage.act(delta);
        rightStage.draw();
    }

    // C'est pour gérer la "sauvegarde" de l'aspect ratio quand on resize la fenêtre
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
        theme.stop();
        characterStage.switchRandom(); // Comme ça c'est pas le même perso la prochaine fois
    }

    // Evidemment on libère les ressources à la fin
    @Override
    public void dispose() {
        theme.dispose();
        background.dispose();
        characterStage.dispose();
        rightStage.dispose();
        font.dispose();
    }

    /**
     * Disposes the old right stage and sets visible a new one.
     * 
     * @param newRightStage which will be drawn on the right
     */
    public void switchRightStage(Stage newRightStage) {
        if (rightStage != null) {
            inputMultiplexer.removeProcessor(rightStage);
            rightStage.dispose();
        }
        rightStage = newRightStage;
        inputMultiplexer.addProcessor(newRightStage);
    }

}
