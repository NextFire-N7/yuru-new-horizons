package moe.yuru.newhorizons.views;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.GameModel;
import moe.yuru.newhorizons.models.OpponentNormal;
import moe.yuru.newhorizons.models.OpponentVoid;

/**
 * Game mode selection menu.
 * 
 * @author DinoGurnari
 * @author NextFire
 */
public class GameModeSelectionStage extends Stage {

    /**
     * @param game the game instance
     */
    public GameModeSelectionStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());

        // Table for the menu buttons
        VisTable table = new VisTable();
        addActor(table);
        table.setBounds(690, 0, 400, 625);
        table.defaults().width(table.getWidth()).height(75).space(50);

        // Buttons
        VisTextButton soloButton = new VisTextButton("Solo Game Mode");
        VisTextButton versusButton = new VisTextButton("Versus Game Mode");
        VisTextButton exitButton = new VisTextButton("Back");

        // Adding them to the menu table
        table.add(soloButton);
        table.row();
        table.add(versusButton);
        table.row();
        table.add(exitButton);

        // Buttons controllers
        soloButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setGameModel(new GameModel("east-a1", new OpponentVoid()));
                game.setScreen(new GameScreen(game)); // TODO: game personalization screen
            }
        });

        versusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setGameModel(new GameModel("east-a1", new OpponentNormal()));
                game.setScreen(new GameScreen(game));
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getMainMenuScreen().switchRightStage(new MainMenuStage(game));
            }
        });
    }

}
