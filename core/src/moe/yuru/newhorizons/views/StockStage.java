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
        screenTable.pad(20);

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
        VisTable stockTable = new VisTable();
        stockTable.defaults().height(400).width(250).pad(20);
        VisScrollPane stockPane = new VisScrollPane(stockTable);

        ObjectMap<Faction, Array<Building>> buildingStockMap = BuildingStockWrapper.getBuildingStockFactionMap();
        int i = 0;
        for (Faction faction : Faction.values()) {
            for (Building building : buildingStockMap.get(faction)) {
                VisTable buildingCell = getBuildingCell(building);
                stockTable.add(buildingCell);
                if (++i == 4) {
                    stockTable.row();
                    i = 0;
                }
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
        cell.debug();
        cell.setSize(200, 200);
        cell.add(new VisLabel(building.getLastName() + " " + building.getFirstName()));
        cell.row();
        cell.add(new VisLabel(building.getFaction().toString()));
        cell.row();
        cell.add(new VisLabel(building.getFunction()));
        cell.row();
        Texture icon = AssetHelper.getIconTexture(building);
        textures.add(icon);
        Image image = new Image(icon);
        cell.add(image).size(100, 100);
        cell.row();
        VisTable statsCell = new VisTable();
        cell.add(statsCell);
        statsCell.add(new VisLabel(building.getStats(1).getCoinCost() + " coins"));
        statsCell.row();
        statsCell.add(new VisLabel(building.getStats(1).getResourcesCost() + " resources"));
        statsCell.row();
        statsCell.add(new VisLabel(building.getStats(1).getCoinsPerSecond() + " CPS"));
        statsCell.row();
        statsCell.add(new VisLabel(building.getStats(1).getResourcesPerSecond() + " RPS"));
        return cell;
    }

}
