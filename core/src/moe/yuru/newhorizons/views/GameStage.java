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

/**
 * GameScreen {@link Stage}.
 */
public class GameStage extends Stage {

    private YuruNewHorizons game;
    private VisLabel coinsLabel;
    private VisLabel scienceLabel;
    private VisLabel cultureLabel;
    private VisLabel industryLabel;
    private VisLabel politicsLabel;
    private Texture mapTexture;

    /**
     * @param game the game instance
     */
    public GameStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());
        this.game = game;

        VisTable screenTable = new VisTable();
        screenTable.setFillParent(true);
        addActor(screenTable);

        VisTable leftTable = new VisTable();
        screenTable.add(leftTable).left().size(768, 720);

        mapTexture = AssetHelper.getMapTexture(game.getModel().getTown());
        TextureRegion mapTextureRegion = new TextureRegion(mapTexture, 480, 375, 1680, 1375);
        Image mapImage = new Image(mapTextureRegion);
        leftTable.add(mapImage).size(768, 576);

        leftTable.row();
        VisTable leftBottomTable = new VisTable();
        leftTable.add(leftBottomTable).grow();

        leftBottomTable.add(new VisLabel("Placeholder."));

        VisTable rightTable = new VisTable();
        screenTable.add(rightTable).right().grow();
        rightTable.defaults().grow().pad(10);

        VisTable statsTable = new VisTable();
        rightTable.add(statsTable);
        statsTable.top();
        statsTable.columnDefaults(0).left();
        statsTable.columnDefaults(1).expandX();

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
                super.clicked(event, x, y);
                game.setScreen(new StockScreen(game));
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        coinsLabel.setText(String.valueOf((int) game.getModel().getTown().getCoins()));
        scienceLabel.setText(String.valueOf(game.getModel().getTown().getResources(Faction.SCIENCE).intValue()));
        cultureLabel.setText(String.valueOf(game.getModel().getTown().getResources(Faction.CULTURE).intValue()));
        industryLabel.setText(String.valueOf(game.getModel().getTown().getResources(Faction.INDUSTRY).intValue()));
        politicsLabel.setText(String.valueOf(game.getModel().getTown().getResources(Faction.POLITICS).intValue()));
    }

    @Override
    public void dispose() {
        super.dispose();
        mapTexture.dispose();
    }

}
