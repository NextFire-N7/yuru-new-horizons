package moe.yuru.newhorizons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MainMenuScreen implements Screen {

    private YuruNewHorizons game;

    private Music yuruTheme;
    private Sound nyanpasu;
    private Texture yuruBg;
    private Texture renchon;
    private BitmapFont yuruFont;

    private Vector3 touchPos;
    private Rectangle renchonRectangle;

    public MainMenuScreen(YuruNewHorizons game) {
        this.game = game;

        // Charger la BGM (sur disque)
        // Les assets sont dans core/assets/
        yuruTheme = Gdx.audio.newMusic(Gdx.files.internal("audio/yuru_theme.mp3"));
        yuruTheme.setLooping(true);

        // Charger la hitsound (en RAM)
        nyanpasu = Gdx.audio.newSound(Gdx.files.internal("audio/nyanpasu.mp3"));

        // Charger les images (en VRAM)
        yuruBg = new Texture(Gdx.files.internal("images/menu_bg.jpeg"));
        yuruBg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        renchon = new Texture(Gdx.files.internal("images/renchon.png"));
        renchon.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        
        // Génération des fonts du titre (.ttf -> BitmapFont = image)
        FreeTypeFontGenerator yuruFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/majuro_fino.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 60;
        parameter.borderWidth = 5;
        parameter.borderColor = Color.valueOf("E39256");
        yuruFont = yuruFontGenerator.generateFont(parameter);
        yuruFontGenerator.dispose();

        // Pour l'input nyanpasu
        touchPos = new Vector3();
        renchonRectangle = new Rectangle(100, 0, renchon.getWidth() / 2, renchon.getHeight() / 2);
    }

    // Tout ce qui se passe au lancement du menu
    @Override
    public void show() {
        yuruTheme.play();
        nyanpasu.play();

        // C'est le listener pour quand on clique sur Renchon ça fait nyanpasu
        // Ressemble pas mal à ce qu'on fait avec Swing en TP/TD (slide 54 du cours).
        // InputAdapter = classe implémentant InputProcessor, permet de n'avoir qu'à
        // @Override ce qui nous intéresse et ne pas avoir à implémenter toutes les
        // methodes dans la classe anonyme utilisée ci-dessous.
        // Gdx.input.setInputProcessor(new InputAdapter() {
        //     @Override
        //     public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //         if (button == Buttons.LEFT) {
        //             touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        //             game.getCamera().unproject(touchPos);
        //             if (renchonRectangle.contains(touchPos.x, touchPos.y)) {
        //                 nyanpasu.play();
        //                 return true;
        //             }
        //         }
        //         return false;
        //     }
        // });
    }

    // Etapes effectuées pour chaque rendu d'une frame (boucle infinie sort of)
    @Override
    public void render(float delta) {
        // maj des données de la camera
        game.getCamera().update();
        // dit au moteur d'utiliser la camera pour la trad code <-> taille réelle
        game.getBatch().setProjectionMatrix(game.getCamera().combined);

        // Les instr de rendu entre begin et end seront faites en 1 coup (rendu
        // efficace, vive les fps)
        game.getBatch().begin();
        game.getBatch().draw(yuruBg, 0, 0, game.getCamera().viewportWidth, game.getCamera().viewportHeight);
        game.getBatch().draw(renchon, 100, 0, renchon.getWidth() / 2, renchon.getHeight() / 2);
        yuruFont.draw(game.getBatch(), "Yuru New Horizons", 600, 650);
        game.getBatch().end();

        // Ici on peut ne pas utiliser de listener mais on poll à chaque rendu de frame... (c'est plus simple)
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getCamera().unproject(touchPos);
            if (renchonRectangle.contains(touchPos.x, touchPos.y)) {
                nyanpasu.play();
            }
        }
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

    // Evidemment on libère les ressources à la fin du programme
    @Override
    public void dispose() {
        yuruTheme.dispose();
        nyanpasu.dispose();
        yuruBg.dispose();
        renchon.dispose();
    }

}
