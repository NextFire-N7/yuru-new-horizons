package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import moe.yuru.newhorizons.YuruNewHorizons;

/**
 * Main menu {@link Screen}.
 */
public class MainMenuScreen implements Screen {

    private YuruNewHorizons game;
    private BuildingCharacterStage buildingCharacterStage;
    private MainMenuStage mainMenuStage;
    private Texture background;
    private Music theme;
    private InputMultiplexer inputMultiplexer;

    /**
     * @param game the game instance
     */
    public MainMenuScreen(YuruNewHorizons game) {
        this.game = game;

        // Scène du perso à gauche
        buildingCharacterStage = new BuildingCharacterStage(game);
        buildingCharacterStage.addRandomCharaListener();

        // Scène des menus à droite
        mainMenuStage = new MainMenuStage(game);

        // rajoute à l'inputMultiplexer le gestionnaire d'input de characterStage
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(buildingCharacterStage);
        inputMultiplexer.addProcessor(mainMenuStage);

        // Charger les images (en VRAM)
        background = new Texture(Gdx.files.internal("main_menu.png"));
        background.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        // Charger la BGM (sur disque)
        // Les assets sont dans core/assets/
        theme = Gdx.audio.newMusic(Gdx.files.internal("yuru_theme.mp3"));
        theme.setLooping(true);
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
        game.getBatch().end();

        // appelle les methodes act des acteurs de la scène si définies
        buildingCharacterStage.act(delta);
        // dessine la scène
        buildingCharacterStage.draw();

        // pareil avec le menu
        mainMenuStage.act(delta);
        mainMenuStage.draw();
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
        buildingCharacterStage.dispose();
        mainMenuStage.dispose();
    }

}
