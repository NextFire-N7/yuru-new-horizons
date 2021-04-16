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
import moe.yuru.newhorizons.utils.Event;
import moe.yuru.newhorizons.utils.Listener;

/**
 * GameScreen {@link Stage}.
 */
public class GameStage extends Stage implements Listener {

    private YuruNewHorizons game;
    private VisLabel coinsLabel;
    private VisLabel scienceLabel;
    private VisLabel cultureLabel;
    private VisLabel industryLabel;
    private VisLabel politicsLabel;
    private Texture mapTexture;
    private VisTextButton constructButton;

    /**
     * @param game the game instance
     */
    public GameStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());
        this.game = game;
        game.getGameModel().addListener(this);

        mapTexture = AssetHelper.getMapTexture(game.getGameModel().getTown());
        TextureRegion mapTextureRegion = new TextureRegion(mapTexture, 480, 375, 1680, 1375);
        Image mapImage = new Image(mapTextureRegion);
        mapImage.setBounds(0, 144, 768, 576);
        addActor(mapImage);

        VisTable rightTable = new VisTable();
        addActor(rightTable);
        rightTable.setSize(512, 720);
        rightTable.setPosition(768, 0);
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
        rightTable.add(menuTable);
        menuTable.bottom();

        constructButton = new VisTextButton("Construct");
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
        coinsLabel.setText(String.valueOf((int) game.getGameModel().getTown().getCoins()));
        scienceLabel.setText(String.valueOf((int) game.getGameModel().getTown().getResources(Faction.SCIENCE)));
        cultureLabel.setText(String.valueOf((int) game.getGameModel().getTown().getResources(Faction.CULTURE)));
        industryLabel.setText(String.valueOf((int) game.getGameModel().getTown().getResources(Faction.INDUSTRY)));
        politicsLabel.setText(String.valueOf((int) game.getGameModel().getTown().getResources(Faction.POLITICS)));
    }

    @Override
    public void dispose() {
        super.dispose();
        mapTexture.dispose();
    }

    @Override
    public void processEvent(Event event) {
        if (event.getSource() == game.getGameModel().getTown() && event.getName().equals("toPlace")) {
            constructButton.setVisible((event.getValue() != null) ? false : true);
        }
    }

}
