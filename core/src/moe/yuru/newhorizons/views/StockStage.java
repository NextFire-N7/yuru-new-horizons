package moe.yuru.newhorizons.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.kotcrab.vis.ui.layout.GridGroup;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.Building;
import moe.yuru.newhorizons.models.Faction;
import moe.yuru.newhorizons.utils.AssetHelper;
import moe.yuru.newhorizons.utils.BuildingStockWrapper;

/**
 * New building selector stage.
 * 
 * @author NextFire
 */
public class StockStage extends Stage {

    private YuruNewHorizons game;
    private ObjectSet<Texture> textures;

    /**
     * @param game the game instance
     */
    public StockStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());
        this.game = game;

        // Yes I'm aware of the libGDX asset manager but I'm too lazy to use it...
        textures = new ObjectSet<>();

        // Whole screen table
        VisTable screenTable = new VisTable();
        screenTable.setFillParent(true);
        addActor(screenTable);
        screenTable.pad(10);

        // Put the stock scroling pane at the top
        screenTable.add(getStockPane()).grow();

        // Back button at the bottom
        screenTable.row();
        VisTextButton backButton = new VisTextButton("Back");
        screenTable.add(backButton).pad(10).growX();

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.getScreen().dispose();
                game.setScreen(game.getGameScreen());
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        for (Texture texture : textures) {
            texture.dispose();
        }
    }

    /**
     * @return the stock scrolling pane
     */
    private VisScrollPane getStockPane() {
        // A grid that will automatically arrange our "cards" on screen
        GridGroup group = new GridGroup();
        group.setItemSize(300, 200);
        VisScrollPane stockPane = new VisScrollPane(group);
        stockPane.setScrollingDisabled(true, false); // disable horizontal scrolling

        // Gonna sort buildings by their faction in the group
        ObjectMap<Faction, Array<Building>> buildingStockMap = BuildingStockWrapper.getBuildingStockFactionMap();
        for (Faction faction : Faction.values()) {
            for (Building building : buildingStockMap.get(faction)) {
                // Get building "card" and add it to the grid
                VisTable buildingCell = getBuildingCell(building);
                group.addActor(buildingCell);

                // Controller which sets the chousen building to place in the model then returns
                // on game screen
                buildingCell.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.getGameModel().setToPlace(building);
                        game.getScreen().dispose();
                        game.setScreen(game.getGameScreen());
                    }
                });
            }
        }

        // Set the mouse focus on the scrolling pane, hence your scroll wheel directly
        // speaks to this pane when the mouse is hovering over it
        stockPane.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                setScrollFocus(stockPane);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                setScrollFocus(null);
            }
        });

        return stockPane;
    }

    /**
     * Returns a "card" for the given {@link Building}. You don't need to understand
     * it to not use it, it's private!
     * 
     * @param building from stock
     * @return a {@link VisTable} which acts as an infocard
     */
    private VisTable getBuildingCell(Building building) {
        // Essentially it's labels inside tables inside other tables, horrendious as
        // written below but it's doing the job...

        // "Big" table
        VisTable cell = new VisTable();

        cell.add(new VisLabel(building.getLastName() + " " + building.getFirstName()));
        cell.row();
        cell.addSeparator();

        // The table under character name and 1st separator
        VisTable subTable = new VisTable();
        cell.add(subTable).grow();

        // Subtable on the left: Faction, function and icon
        VisTable leftTable = new VisTable();
        leftTable.defaults().expandY();
        leftTable.add(new VisLabel(building.getFaction().toString()));
        leftTable.row();
        leftTable.add(new VisLabel(building.getFunction()));
        leftTable.row();
        leftTable.addSeparator();
        Texture icon = AssetHelper.getIconTexture(building);
        textures.add(icon);
        Image image = new Image(icon);
        leftTable.add(image).size(100, 100);

        // Subtable on the right: costs and incomes
        VisTable rightTable = new VisTable();
        rightTable.defaults().expandY();
        rightTable.add(new VisLabel("Costs"));
        rightTable.row();
        rightTable.add(new VisLabel(building.getStats(1).getCoinCost() + " Coins"));
        rightTable.row();
        rightTable.add(new VisLabel(building.getStats(1).getResourcesCost() + " " + building.getFaction().toString()));
        rightTable.row();
        rightTable.addSeparator();
        rightTable.add(new VisLabel("Incomes"));
        rightTable.row();
        rightTable.add(new VisLabel("+" + building.getStats(1).getCoinsPerSecond() + " Coins/s"));
        rightTable.row();
        rightTable.add(new VisLabel(
                "+" + building.getStats(1).getResourcesPerSecond() + " " + building.getFaction().toString() + "/s"));

        subTable.add(leftTable).grow();
        subTable.addSeparator(true);
        subTable.add(rightTable).grow();

        return cell;
    }

}
