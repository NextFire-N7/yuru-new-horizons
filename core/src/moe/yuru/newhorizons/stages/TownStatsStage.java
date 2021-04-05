package moe.yuru.newhorizons.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kotcrab.vis.ui.widget.VisLabel;

import moe.yuru.newhorizons.YuruNewHorizons;

public class TownStatsStage extends Stage {

    private YuruNewHorizons game;
    private VisLabel coinsLabel;

    public TownStatsStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());
        this.game = game;

        coinsLabel = new VisLabel("Coins : " + game.getModel().getTown().getCoins(), Color.BLACK);
        coinsLabel.setPosition(940, 680);

        addActor(coinsLabel);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        coinsLabel.setText("Coins: " + game.getModel().getTown().getCoins());
    }

}
