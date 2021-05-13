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
import moe.yuru.newhorizons.utils.EventType;
import moe.yuru.newhorizons.utils.GameModelSave;
import moe.yuru.newhorizons.utils.Listener;

/**
 * Contains the town map layout, town stats at the upper right and menus on the
 * bottom right. It listens to the game model to dynamically modify menu
 * buttons.
 * 
 * @author NextFire
 */
public class GameStage extends Stage implements Listener {

    private YuruNewHorizons game;

    private Texture mapTexture;

    private VisLabel coinsLabel;
    private VisLabel scienceLabel;
    private VisLabel cultureLabel;
    private VisLabel industryLabel;
    private VisLabel politicsLabel;

    private VisTable menuTable;

    /**
     * @param game the game instance
     */
    public GameStage(YuruNewHorizons game) {
        super(game.getViewport(), game.getBatch());
        this.game = game;

        // Register to the game model
        game.getGameModel().addListener(this);

        // Add map actor to the stage
        mapTexture = AssetHelper.getTownMapTexture(game.getGameModel());
        TextureRegion mapTextureRegion = new TextureRegion(mapTexture, 480, 375, 1680, 1375);
        Image mapImage = new Image(mapTextureRegion);
        mapImage.setBounds(0, 144, 768, 576);
        addActor(mapImage);

        // A full height table just at the right of the map
        VisTable rightTable = new VisTable();
        addActor(rightTable);
        rightTable.setSize(512, 720);
        rightTable.setPosition(768, 0);
        rightTable.defaults().grow().pad(10);

        // Stats table at the top of rightTable
        VisTable statsTable = new VisTable(true);
        rightTable.add(statsTable);
        statsTable.top();
        statsTable.columnDefaults(0).left();
        statsTable.columnDefaults(1).expandX();

        // Labels whose going to display town balance
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

        // Menu table at the bottom of the right table
        rightTable.row();
        menuTable = new VisTable(true);
        rightTable.add(menuTable);
        menuTable.bottom();
        menuTable.defaults().growX().height(30);

        // Adding buttons to the menu table
        VisTextButton constructButton = new VisTextButton("Construct");
        menuTable.add(constructButton);

        constructButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new StockScreen(game)); // new construction screen
            }
        });

        menuTable.row();
        VisTextButton saveButton = new VisTextButton("Save");
        menuTable.add(saveButton);

        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GameModelSave.save(game.getGameModel());
            }
        });

        menuTable.row();
        VisTextButton exitButton = new VisTextButton("Exit");
        menuTable.add(exitButton);

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // kill game screen and model then return to main menu
                game.getScreen().dispose();
                game.setGameModel(null);
                game.setGameScreen(null);
                game.setScreen(new MainMenuScreen(game));
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // Update labels according to the town balance
        coinsLabel.setText(String.valueOf((int) game.getGameModel().getTownCoins()));
        scienceLabel.setText(String.valueOf((int) game.getGameModel().getTownResources(Faction.SCIENCE)));
        cultureLabel.setText(String.valueOf((int) game.getGameModel().getTownResources(Faction.CULTURE)));
        industryLabel.setText(String.valueOf((int) game.getGameModel().getTownResources(Faction.INDUSTRY)));
        politicsLabel.setText(String.valueOf((int) game.getGameModel().getTownResources(Faction.POLITICS)));
    }

    @Override
    public void dispose() {
        super.dispose();
        mapTexture.dispose();
    }

    @Override
    public void processEvent(Event event) {
        // Hide menuTable if a building is currently placed
        if (event.getSource() == game.getGameModel() && event.getType() == EventType.Construction.TO_PLACE) {
            if (event.getValue() != null) {
                menuTable.setVisible(false);
            } else {
                menuTable.setVisible(true);
            }
        }
    }

}
