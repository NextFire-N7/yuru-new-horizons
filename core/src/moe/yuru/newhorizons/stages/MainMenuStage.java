package moe.yuru.newhorizons.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.screens.GameScreen;
import moe.yuru.newhorizons.screens.MainMenuScreen;

/**
 * Main menu menu {@link Stage}.
 */
public class MainMenuStage extends Stage {

    /**
     * @param game the game instance
     */
    public MainMenuStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());

        VisTable table = new VisTable();
        addActor(table);
        table.setBounds(690, 50, 400, 500);
        table.defaults().expandX().height(75).space(50).fill();

        VisTextButton startButton = new VisTextButton("New Game");
        VisTextButton resumeButton = new VisTextButton("Resume Game");
        VisTextButton optionsButton = new VisTextButton("Options");
        VisTextButton exitButton = new VisTextButton("Quit");

        table.add(startButton);
        table.row();
        table.add(resumeButton);
        table.row();
        table.add(optionsButton);
        table.row();
        table.add(exitButton);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new GameScreen(game)); // A changer évidemment...
            }
        });

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MainMenuScreen(game)); // A changer évidemment...
            }
        });

        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MainMenuScreen(game)); // A changer évidemment...
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

}
