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
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.Building;
import moe.yuru.newhorizons.models.Faction;
import moe.yuru.newhorizons.utils.AssetHelper;
import moe.yuru.newhorizons.utils.BuildingStockWrapper;

public class StockStage extends Stage {

    private YuruNewHorizons game;
    private Array<Texture> textures;

    public StockStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());
        this.game = game;
        textures = new Array<>();

        VisTable screenTable = new VisTable();
        screenTable.setFillParent(true);
        addActor(screenTable);
        screenTable.pad(20);

        screenTable.add(getStockPane());

        screenTable.row();
        VisTextButton backButton = new VisTextButton("Back");
        screenTable.add(backButton);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
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

    private VisScrollPane getStockPane() {
        VisTable stockTable = new VisTable();
        VisScrollPane stockPane = new VisScrollPane(stockTable);

        ObjectMap<Faction, Array<Building>> buildingStockMap = BuildingStockWrapper.getBuildingStockFactionMap();
        int i = 0;
        for (Faction faction : Faction.values()) {
            for (Building building : buildingStockMap.get(faction)) {
                VisTable buildingCell = getBuildingCell(building);
                stockTable.defaults().height(300).width(250).pad(5);
                stockTable.add(buildingCell);
                if (++i == 4) {
                    stockTable.row();
                    i = 0;
                }
                buildingCell.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        // TODO: A changer évidemment...
                        game.getScreen().dispose();
                        game.setScreen(game.getGameScreen());
                        game.getGameScreen().addBuildingTask(building);
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

    private VisTable getBuildingCell(Building building) {
        VisTable cell = new VisTable();
        cell.setSize(200, 200);
        cell.add(new VisLabel(building.getId()));
        cell.row();
        Texture icon = AssetHelper.getIconTexture(building);
        textures.add(icon);
        Image image = new Image(icon);
        cell.add(image).size(100, 100);
        cell.row();
        VisTable statsCell = new VisTable();
        cell.add(statsCell).grow();
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
