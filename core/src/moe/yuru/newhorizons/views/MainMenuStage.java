package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.utils.GameModelSave;

/**
 * Main menu menu. Contains the main menu buttons on the right.
 * 
 * @author NextFire
 */
public class MainMenuStage extends Stage {

    /**
     * @param game the game instance
     */
    public MainMenuStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());

        // Table for the menu buttons
        VisTable table = new VisTable();
        addActor(table);
        table.setBounds(690, 0, 400, 625);
        table.defaults().width(table.getWidth()).height(75).space(50);

        // Buttons
        VisTextButton startButton = new VisTextButton("New Game");
        VisTextButton resumeButton = new VisTextButton("Resume Game");
        VisTextButton optionsButton = new VisTextButton("Options");
        VisTextButton exitButton = new VisTextButton("Quit");

        // Adding them to the menu table
        table.add(startButton);
        table.row();
        table.add(resumeButton);
        table.row();
        table.add(optionsButton);
        table.row();
        table.add(exitButton);

        // Buttons controllers
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.getMainMenuScreen().switchRightStage(new GameModeSelectionStage(game));
            }
        });

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setGameModel(GameModelSave.load());
                game.setScreen(new GameScreen(game));
            }
        });

        // optionsButton.addListener(new ClickListener() {
        // @Override
        // public void clicked(InputEvent event, float x, float y) {
        // super.clicked(event, x, y);
        // game.getScreen().dispose();
        // game.setScreen(new xxxScreen(game)); // TODO: options menu...
        // }
        // });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
            }
        });
    }

}
