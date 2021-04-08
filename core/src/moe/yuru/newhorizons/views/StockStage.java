package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    private Screen backScreen;

    public StockStage(YuruNewHorizons game, Screen backScreen) {
        super(game.getViewport(), game.getBatch());
        this.game = game;
        this.backScreen = backScreen;

        VisTable screenTable = new VisTable();
        screenTable.setFillParent(true);
        addActor(screenTable);
        screenTable.pad(20);

        VisTable factionTable = new VisTable();

        screenTable.add(factionTable);
        screenTable.row();
        VisTextButton backButton = new VisTextButton("Back");
        screenTable.add(backButton);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getScreen().dispose();
                game.setScreen(backScreen);
            }
        });

        factionTable.defaults().height(160);
        factionTable.columnDefaults(0).left().padRight(10);
        factionTable.columnDefaults(1).width(1100);
        for (Faction faction : Faction.values()) {
            factionTable.add(new VisLabel(faction.toString()));
            factionTable.add(getBuildingsPane(faction));
            factionTable.row();
        }
    }

    private VisScrollPane getBuildingsPane(Faction faction) {
        VisTable buildingsTable = new VisTable();
        VisScrollPane buildingsPane = new VisScrollPane(buildingsTable);
        buildingsTable.defaults().size(160).left();
        buildingsTable.debug();

        buildingsPane.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                setScrollFocus(buildingsPane);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                setScrollFocus(null);
            }
        });

        for (Building building : BuildingStockWrapper.getBuildingStockFactionMap().get(faction)) {
            VisTable buildingCell = getBuildingCell(building);
            buildingsTable.add(buildingCell);
            buildingCell.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    // TODO: A changer évidemment...
                    game.getScreen().dispose();
                    game.setScreen(backScreen);
                }
            });
        }

        return buildingsPane;
    }

    private VisTable getBuildingCell(Building building) {
        VisTable cell = new VisTable();
        cell.add(new VisLabel(building.getId()));
        cell.row();
        Texture icon = AssetHelper.getIconTexture(building);
        Image image = new Image(icon);
        cell.add(image);
        return cell;
    }

}
