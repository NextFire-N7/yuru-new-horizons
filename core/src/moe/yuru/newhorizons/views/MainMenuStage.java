package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import moe.yuru.newhorizons.YuruNewHorizons;

/**
 * Main menu menu {@link Stage}.
 */
public class MainMenuStage extends Stage {

    private BitmapFont font;

    /**
     * @param game the game instance
     */
    public MainMenuStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());

        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 60;
        parameter.borderWidth = 5;
        parameter.borderColor = Color.valueOf("E39256");
        font = game.getFontGenerator().generateFont(parameter);
        VisLabel title = new VisLabel("Yuru New Horizons", new LabelStyle(font, Color.WHITE));

        VisTable table = new VisTable();
        addActor(table);
        table.setBounds(690, 0, 400, 720);

        table.add(title).space(50);
        table.row();

        VisTable menuTable = new VisTable();
        table.add(menuTable);
        menuTable.defaults().width(table.getWidth()).height(75).space(50);

        VisTextButton startButton = new VisTextButton("New Game");
        VisTextButton resumeButton = new VisTextButton("Resume Game");
        VisTextButton optionsButton = new VisTextButton("Options");
        VisTextButton exitButton = new VisTextButton("Quit");

        menuTable.add(startButton);
        menuTable.row();
        menuTable.add(resumeButton);
        menuTable.row();
        menuTable.add(optionsButton);
        menuTable.row();
        menuTable.add(exitButton);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.getScreen().dispose();
                game.setScreen(new GameScreen(game)); // A changer évidemment...
            }
        });

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.getScreen().dispose();
                game.setScreen(new MainMenuScreen(game)); // A changer évidemment...
            }
        });

        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.getScreen().dispose();
                game.setScreen(new MainMenuScreen(game)); // A changer évidemment...
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }

}