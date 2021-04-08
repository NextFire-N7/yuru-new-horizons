package moe.yuru.newhorizons.views;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.Faction;

public class GameStage extends Stage {

    private YuruNewHorizons game;
    private VisLabel coinsLabel;
    private VisLabel scienceLabel;
    private VisLabel cultureLabel;
    private VisLabel industryLabel;
    private VisLabel politicsLabel;

    public GameStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());
        this.game = game;

        VisTable statsTable = new VisTable();
        addActor(statsTable);
        statsTable.setBounds(880, 710, 400, 0);
        statsTable.top();
        statsTable.columnDefaults(0).width(150);
        statsTable.debug();

        statsTable.add(new VisLabel("Coins")).spaceBottom(10);
        coinsLabel = new VisLabel("");
        statsTable.add(coinsLabel).space(10);
        statsTable.row();
        statsTable.add(new VisLabel("Science"));
        scienceLabel = new VisLabel("");
        statsTable.add(scienceLabel);
        statsTable.row();
        statsTable.add(new VisLabel("Culture"));
        cultureLabel = new VisLabel("");
        statsTable.add(cultureLabel);
        statsTable.row();
        statsTable.add(new VisLabel("Industry"));
        industryLabel = new VisLabel("");
        statsTable.add(industryLabel);
        statsTable.row();
        statsTable.add(new VisLabel("Politics"));
        politicsLabel = new VisLabel("");
        statsTable.add(politicsLabel);

        VisTable menuTable = new VisTable();
        addActor(menuTable);
        menuTable.setBounds(880, 10, 400, 0);
        menuTable.bottom();
        menuTable.debug();

        VisTextButton constructButton = new VisTextButton("Construct");
        menuTable.add(constructButton);

        constructButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new StockScreen(game, game.getScreen()));
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        coinsLabel.setText(String.valueOf(game.getModel().getTown().getCoins()));
        scienceLabel.setText(String.valueOf(game.getModel().getTown().getResources(Faction.SCIENCE)));
        cultureLabel.setText(String.valueOf(game.getModel().getTown().getResources(Faction.CULTURE)));
        industryLabel.setText(String.valueOf(game.getModel().getTown().getResources(Faction.INDUSTRY)));
        politicsLabel.setText(String.valueOf(game.getModel().getTown().getResources(Faction.POLITICS)));
    }

}
