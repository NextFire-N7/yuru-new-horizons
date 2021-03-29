package moe.yuru.newhorizons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * Main menu of the game.
 */
public class MainMenuScreen implements Screen {

    private final YuruNewHorizons game;

    private Music yuruTheme;
    private Texture yuruBg;
    private BitmapFont yuruFont;

    private InputMultiplexer inputMultiplexer;

    private CharacterStage characterStage;
    private MainMenuMenuStage mainMenuMenuStage;

    public MainMenuScreen(final YuruNewHorizons game) {
        this.game = game;

        // Scène du perso à gauche
        characterStage = new CharacterStage(game);
        characterStage.addRandomCharaListener();

        // Scène des menus à droite
        mainMenuMenuStage = new MainMenuMenuStage(game);

        // rajoute à l'inputMultiplexer le gestionnaire d'input de characterStage
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(characterStage);
        inputMultiplexer.addProcessor(mainMenuMenuStage);

        // Charger la BGM (sur disque)
        // Les assets sont dans core/assets/
        yuruTheme = Gdx.audio.newMusic(Gdx.files.internal("audio/yuru_theme.mp3"));
        yuruTheme.setLooping(true);

        // Charger les images (en VRAM)
        yuruBg = new Texture(Gdx.files.internal("images/main_menu.png"));
        yuruBg.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        // Génération des fonts du titre (.ttf -> BitmapFont = image)
        FreeTypeFontGenerator yuruFontGenerator = new FreeTypeFontGenerator(
                Gdx.files.internal("fonts/majuro_fino.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 60;
        parameter.borderWidth = 5;
        parameter.borderColor = Color.valueOf("E39256");
        yuruFont = yuruFontGenerator.generateFont(parameter);
        yuruFontGenerator.dispose();
    }

    // Tout ce qui se passe au lancement du menu
    @Override
    public void show() {
        // règle l'inputMultiplexer comme gestionnaire des inputs du menu
        Gdx.input.setInputProcessor(inputMultiplexer);
        characterStage.playCharaSound();
        yuruTheme.setVolume(game.getMusicVolume());
        yuruTheme.play();
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
        game.getBatch().draw(yuruBg, 0, 0, 1280, 720);
        yuruFont.draw(game.getBatch(), "Yuru New Horizons", 600, 650);
        game.getBatch().end();

        // appelle les methodes act des acteurs de la scène si définies
        characterStage.act();
        // dessine la scène
        characterStage.draw();

        // pareil avec le menu
        mainMenuMenuStage.act();
        mainMenuMenuStage.draw();
    }

    // C'est pour gérer la "sauvegarde" de l'aspect ratio quand on resize la fenêtre
    @Override
    public void resize(int width, int height) {
        game.getViewport().update(width, height);
    }

    @Override
    public void pause() {
        yuruTheme.pause();
    }

    @Override
    public void resume() {
        yuruTheme.play();
    }

    @Override
    public void hide() {
        yuruTheme.pause();
    }

    // Evidemment on libère les ressources à la fin
    @Override
    public void dispose() {
        yuruTheme.dispose();
        yuruBg.dispose();
        yuruFont.dispose();
        characterStage.dispose();
        mainMenuMenuStage.dispose();
    }

}
