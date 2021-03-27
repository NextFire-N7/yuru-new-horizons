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
 * Main menu of the game
 */
public class MainMenuScreen implements Screen {

    private YuruNewHorizons game;

    private Music yuruTheme;
    private Texture yuruBg;
    private BitmapFont yuruFont;

    private CharacterStage characterStage;
    private InputMultiplexer inputMultiplexer;

    public MainMenuScreen(YuruNewHorizons game) {
        this.game = game;

        // Scène du perso à gauche
        characterStage = new CharacterStage();
        characterStage.addRandomCharaListener();

        // rajoute à l'inputMultiplexer le gestionnaire d'input de characterStage
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(characterStage);

        // Charger la BGM (sur disque)
        // Les assets sont dans core/assets/
        yuruTheme = Gdx.audio.newMusic(Gdx.files.internal("audio/yuru_theme.mp3"));
        yuruTheme.setLooping(true);

        // Charger les images (en VRAM)
        yuruBg = new Texture(Gdx.files.internal("images/MainMenu.png"));
        yuruBg.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        // Génération des fonts du titre (.ttf -> BitmapFont = image)
        FreeTypeFontGenerator yuruFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/majuro_fino.ttf"));
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
        yuruTheme.play();
    }

    // Etapes effectuées pour chaque rendu d'une frame (boucle infinie sort of)
    @Override
    public void render(float delta) {
        game.getFpslogger().log();

        // maj des données de la camera
        game.getCamera().update();
        // dit au moteur d'utiliser le système de cood de la camera
        game.getBatch().setProjectionMatrix(game.getCamera().combined);

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
    }

    // C'est pour gérer la "sauvegarde" de l'aspect ratio quand on resize la fenêtre
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

    // Evidemment on libère les ressources à la fin
    @Override
    public void dispose() {
        yuruTheme.dispose();
        yuruBg.dispose();
        yuruFont.dispose();
        characterStage.dispose();
    }

}
