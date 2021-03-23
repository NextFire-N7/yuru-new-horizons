package moe.yuru.newhorizons;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class YuruNewHorizons extends ApplicationAdapter {
    // ApplicationAdapter ~= JFrame

    private Music yuruTheme;
    private Sound nyanpasu;
    private Texture yuruBg;
    private Texture renchon;
    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    // Tout ce qui se passe au démarrage du jeu
    // Les assets sont dans core/assets/
    @Override
    public void create() {
        // Charger la BGM (sur disque)
        yuruTheme = Gdx.audio.newMusic(Gdx.files.internal("yuru_theme.mp3"));
        yuruTheme.setLooping(true);
        yuruTheme.play();

        // Charger la hitsound (en RAM)
        nyanpasu = Gdx.audio.newSound(Gdx.files.internal("nyanpasu.mp3"));
        nyanpasu.play();

        // Charger les images (en VRAM)
        yuruBg = new Texture(Gdx.files.internal("menu_bg.jpeg"));
        yuruBg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        renchon = new Texture(Gdx.files.internal("renchon.png"));
        renchon.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        // La Camera fait la traduction entre notre code en 1280x720 et la taille réelle
        // de la fenêtre
        // Le Viewport permet de garder l'aspect ratio quand on resize la fenêtre
        // le SpriteBatch enverra les instructions au moteur de rendu
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        viewport = new FitViewport(1280, 720, camera);
        batch = new SpriteBatch();

        // C'est le listener pour quand on clique sur Renchon ça fait nyanpasu
        // Ressemble pas mal à ce qu'on fait avec Swing en TP/TD (slide 54 du cours).
        // InputAdapter = classe implémentant InputProcessor, permet de n'avoir qu'à
        // @Override ce qui nous intéresse et ne pas avoir à implémenter toutes les
        // methodes dans la classe anonyme utilisée ci-dessous.
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (button == Buttons.LEFT) {
                    Rectangle renchonRectangle = new Rectangle(100, 0, renchon.getWidth() / 2, renchon.getHeight() / 2);
                    Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                    camera.unproject(tmp);
                    if (renchonRectangle.contains(tmp.x, tmp.y)) {
                        nyanpasu.play();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    // Etapes effectuées pour chaque rendu d'une frame (boucle infinie sort of)
    @Override
    public void render() {
        // maj des données de la camera
        camera.update();
        // dit au moteur d'utiliser la camera pour la trad code <-> taille réelle
        batch.setProjectionMatrix(camera.combined);

        // Les instr de rendu entre begin et end seront faites en 1 coup (rendu
        // efficace, vive les fps)
        batch.begin();
        batch.draw(yuruBg, 0, 0, camera.viewportWidth, camera.viewportHeight);
        batch.draw(renchon, 100, 0, renchon.getWidth() / 2, renchon.getHeight() / 2);
        batch.end();

        // Ici on peut ne pas utiliser de listener mais on poll à chaque rendu de
        // frame...
        // if (Gdx.input.justTouched()) {
        //     Rectangle renchonRectangle = new Rectangle(100, 0, renchon.getWidth() / 2, renchon.getHeight() / 2);
        //     Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        //     camera.unproject(tmp);
        //     if (renchonRectangle.contains(tmp.x, tmp.y)) {
        //         nyanpasu.play();
        //     }
        // }
    }

    // C'est pour gérer la "sauvegarde" de l'aspect ratio quand on resize la fenêtre
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
