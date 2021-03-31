package moe.yuru.newhorizons.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.stages.BuildingCharacterStage;
import moe.yuru.newhorizons.stages.MainMenuMenuStage;

/**
 * Main menu {@link Screen}.
 */
public class MainMenuScreen implements Screen {

    private YuruNewHorizons game;

    private Music theme;
    private Texture background;
    private BitmapFont font;

    private InputMultiplexer inputMultiplexer;

    private BuildingCharacterStage buildingCharacterStage;
    private MainMenuMenuStage mainMenuMenuStage;

    /**
     * @param game the game instance
     */
    public MainMenuScreen(YuruNewHorizons game) {
        this.game = game;

        // Scène du perso à gauche
        buildingCharacterStage = new BuildingCharacterStage(game);
        buildingCharacterStage.addRandomCharaListener();

        // Scène des menus à droite
        mainMenuMenuStage = new MainMenuMenuStage(game);

        // rajoute à l'inputMultiplexer le gestionnaire d'input de characterStage
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(buildingCharacterStage);
        inputMultiplexer.addProcessor(mainMenuMenuStage);

        // Charger la BGM (sur disque)
        // Les assets sont dans core/assets/
        theme = Gdx.audio.newMusic(Gdx.files.internal("yuru_theme.mp3"));
        theme.setLooping(true);

        // Charger les images (en VRAM)
        background = new Texture(Gdx.files.internal("main_menu.png"));
        background.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        // Génération des fonts du titre (.ttf -> BitmapFont = image)
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 60;
        parameter.borderWidth = 5;
        parameter.borderColor = Color.valueOf("E39256");
        font = game.getFontGenerator().generateFont(parameter);
    }

    // Tout ce qui se passe au lancement du menu
    @Override
    public void show() {
        // règle l'inputMultiplexer comme gestionnaire des inputs du menu
        Gdx.input.setInputProcessor(inputMultiplexer);
        buildingCharacterStage.playCharaSound();
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
        font.draw(game.getBatch(), "Yuru New Horizons", 600, 650);
        game.getBatch().end();

        // appelle les methodes act des acteurs de la scène si définies
        buildingCharacterStage.act();
        // dessine la scène
        buildingCharacterStage.draw();

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

    // Evidemment on libère les ressources à la fin
    @Override
    public void dispose() {
        theme.dispose();
        background.dispose();
        font.dispose();
        buildingCharacterStage.dispose();
        mainMenuMenuStage.dispose();
    }

}
