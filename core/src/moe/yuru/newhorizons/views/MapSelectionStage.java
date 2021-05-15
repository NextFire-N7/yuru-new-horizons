package moe.yuru.newhorizons.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.GameModel;
import moe.yuru.newhorizons.models.OpponentNormal;
import moe.yuru.newhorizons.models.OpponentVoid;

/**
 * Map selection menu.
 * @author DinoGurnari
 */
public class MapSelectionStage extends Stage {

    private BitmapFont font;

    /**
     * @param game the game instance
     */
    public MapSelectionStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());

        // Custom font
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 60;
        parameter.borderWidth = 5;
        parameter.borderColor = Color.valueOf("E39256");
        font = game.getFontGenerator().generateFont(parameter);

        // Main table for the title and menu buttons
        VisTable table = new VisTable();
        addActor(table);
        table.setBounds(440, 0, 400, 720);

        // Title label
        VisLabel title = new VisLabel("Yuru New Horizons", new LabelStyle(font, Color.WHITE));
        table.add(title).space(50);
        table.row();

        // Table for the buttons
        VisTable menuTable = new VisTable();
        table.add(menuTable);
        menuTable.defaults().width(table.getWidth()).height(75).space(50);

        // Buttons
        VisTextButton soloButton = new VisTextButton("Solo Game Mode");
        VisTextButton versusButton = new VisTextButton("Versus Game Mode");
        VisTextButton exitButton = new VisTextButton("Back");

        // Adding them to the menu table
        menuTable.add(soloButton);
        menuTable.row();
        menuTable.add(versusButton);
        menuTable.row();
        menuTable.add(exitButton);

         // Buttons controllers
         soloButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.getScreen().dispose();
                game.setGameModel(new GameModel("east-a1", new OpponentVoid()));
                game.setScreen(new GameScreen(game)); // TODO: game personalization screen
            }
        });

        versusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.getScreen().dispose();
                game.setGameModel(new GameModel("east-a1", new OpponentNormal()));
                game.setScreen(new GameScreen(game));
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
    
}
