package moe.yuru.newhorizons.stages;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.Faction;

public class StockStage extends Stage {

    public StockStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());
        Screen backScreen = game.getScreen();

        VisTable screenTable = new VisTable();
        screenTable.setFillParent(true);
        addActor(screenTable);
        screenTable.debug();

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
        factionTable.columnDefaults(0).width(200);
        factionTable.columnDefaults(1).width(1000);
        for (Faction faction : Faction.values()) {
            factionTable.add(new VisLabel(faction.toString()));
            populate(factionTable, faction);
            factionTable.row();
        }
    }

    private void populate(VisTable factionTable, Faction faction) {
        VisTable buildingsTable = new VisTable();
        VisScrollPane buildingsPane = new VisScrollPane(buildingsTable);
        factionTable.add(buildingsPane);

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

        buildingsTable.defaults().space(50);
        for (int i = 0; i <= 20; i++) {
            buildingsTable.add(new VisLabel("" + i));
        }
    }

}
