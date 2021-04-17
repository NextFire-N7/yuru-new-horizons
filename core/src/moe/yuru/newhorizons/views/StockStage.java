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
 * New building selector {@link Stage}.
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
        textures = new ObjectSet<>();

        VisTable screenTable = new VisTable();
        screenTable.setFillParent(true);
        addActor(screenTable);
        screenTable.pad(10);

        screenTable.add(getStockPane()).grow();

        screenTable.row();
        VisTextButton backButton = new VisTextButton("Back");
        screenTable.add(backButton).pad(10);

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
        GridGroup group = new GridGroup();
        group.setItemSize(300, 200);
        VisScrollPane stockPane = new VisScrollPane(group);
        stockPane.setScrollingDisabled(true, false);

        ObjectMap<Faction, Array<Building>> buildingStockMap = BuildingStockWrapper.getBuildingStockFactionMap();
        for (Faction faction : Faction.values()) {
            for (Building building : buildingStockMap.get(faction)) {
                VisTable buildingCell = getBuildingCell(building);
                group.addActor(buildingCell);

                buildingCell.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.getGameModel().getTown().setToPlace(building);
                        game.getScreen().dispose();
                        game.setScreen(game.getGameScreen());
                    }
                });
            }
        }

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
     * Returns a "card" for the given {@link Building}
     * 
     * @param building from stock
     * @return a {@link VisTable} which acts as an infocard
     */
    private VisTable getBuildingCell(Building building) {
        VisTable cell = new VisTable();

        cell.add(new VisLabel(building.getLastName() + " " + building.getFirstName()));
        cell.row();
        cell.addSeparator();

        VisTable subTable = new VisTable();
        cell.add(subTable).grow();

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
