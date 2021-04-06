package moe.yuru.newhorizons.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.Faction;

public class GameStage extends Stage {

    public class ConstructWindow extends VisWindow {

        public ConstructWindow(YuruNewHorizons game) {
            super("Construct");
            setBounds((Gdx.graphics.getWidth() - getWidth()) / 2, (Gdx.graphics.getHeight() - getHeight()) / 2,
                    getWidth(), getHeight());
        }

    }

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

        statsTable.add(new VisLabel("Coins", Color.BLACK)).spaceBottom(10);
        coinsLabel = new VisLabel("", Color.BLACK);
        statsTable.add(coinsLabel).space(10);
        statsTable.row();
        statsTable.add(new VisLabel("Science", Color.BLACK));
        scienceLabel = new VisLabel("", Color.BLACK);
        statsTable.add(scienceLabel);
        statsTable.row();
        statsTable.add(new VisLabel("Culture", Color.BLACK));
        cultureLabel = new VisLabel("", Color.BLACK);
        statsTable.add(cultureLabel);
        statsTable.row();
        statsTable.add(new VisLabel("Industry", Color.BLACK));
        industryLabel = new VisLabel("", Color.BLACK);
        statsTable.add(industryLabel);
        statsTable.row();
        statsTable.add(new VisLabel("Politics", Color.BLACK));
        politicsLabel = new VisLabel("", Color.BLACK);
        statsTable.add(politicsLabel);

        VisTable menuTable = new VisTable();
        addActor(menuTable);
        menuTable.setBounds(880, 10, 400, 0);
        menuTable.bottom();
        menuTable.debug();

        VisTextButton constructButton = new VisTextButton("Construct");
        menuTable.add(constructButton);

        ConstructWindow constructWindow = new ConstructWindow(game);
        constructWindow.setVisible(false);
        addActor(constructWindow);

        constructButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                constructWindow.setVisible(true);
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
