package moe.yuru.newhorizons.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.Faction;
import moe.yuru.newhorizons.utils.AssetHelper;

public class GameStage extends Stage {

    private YuruNewHorizons game;
    private VisLabel coinsLabel;
    private VisLabel scienceLabel;
    private VisLabel cultureLabel;
    private VisLabel industryLabel;
    private VisLabel politicsLabel;
    private Texture mapTexture;

    public GameStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());
        this.game = game;

        VisTable screenTable = new VisTable();
        screenTable.setFillParent(true);
        addActor(screenTable);

        mapTexture = AssetHelper.getMapTexture(game.getModel().getTown());
        TextureRegion mapTextureRegion = new TextureRegion(mapTexture, 480, 375, 1680, 1375);
        Image mapImage = new Image(mapTextureRegion);
        screenTable.add(mapImage).size(960, 720);

        VisTable rightTable = new VisTable();
        screenTable.add(rightTable).grow();
        rightTable.defaults().grow();

        VisTable statsTable = new VisTable();
        rightTable.add(statsTable).padTop(10);
        statsTable.top();
        statsTable.columnDefaults(0).left().width(120);
        statsTable.columnDefaults(1).width(180);

        statsTable.add(new VisLabel("Coins"));
        coinsLabel = new VisLabel("");
        statsTable.add(coinsLabel);
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

        rightTable.row();
        VisTable menuTable = new VisTable();
        rightTable.add(menuTable).padBottom(10);
        menuTable.bottom();

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

    @Override
    public void dispose() {
        super.dispose();
        mapTexture.dispose();
    }

}
