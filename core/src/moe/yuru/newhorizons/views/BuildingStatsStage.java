package moe.yuru.newhorizons.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.Building;
import moe.yuru.newhorizons.models.BuildingStats;
import moe.yuru.newhorizons.models.GameModel;

/**
 * Shows the stats on the right
 * 
 * @author DinoGurnari
 */
public class BuildingStatsStage extends Stage {

    //private YuruNewHorizons game;
    private Building building;
    private BuildingStats stats;


    /**
     * Create a Building's stats stage for the correct building and level
     * @param game the game instance
     * @param building the building
     * @param level its level
     */
    BuildingStatsStage(YuruNewHorizons game, Building building, int level) {
        super(game.getViewport(), game.getBatch());
        this.building = building;
        //this.game = game;
        this.stats = building.getStats(level);

        // A full height table just at the right
        VisTable menuTable = new VisTable();
        addActor(menuTable);
        menuTable.setSize(512, 720);
        menuTable.setPosition(768, 0);
        menuTable.defaults().grow().pad(10);

        // Stats table at the top of menu table
        VisTable statsTable = new VisTable(true);
        menuTable.add(statsTable);
        statsTable.top();
        statsTable.columnDefaults(0).left();
        statsTable.columnDefaults(1).expandX();

        // Name and function labels
        VisLabel name = new VisLabel(building.getFirstName() + " " + building.getLastName());
        name.setColor(Color.BLACK);
        statsTable.add(name);
        statsTable.row();

        VisLabel function = new VisLabel(building.getFunction());
        function.setColor(Color.BLACK);
        statsTable.add(function);
        statsTable.row();
        
        // Level label
        VisLabel lvl = new VisLabel("Level :");
        lvl.setColor(Color.BLACK);
        statsTable.add(lvl);
        VisLabel lvltxt= new VisLabel(String.valueOf(level));
        lvltxt.setColor(Color.BLACK);
        statsTable.add(lvltxt);
        statsTable.row();

        // Coins balance
        VisLabel coins = new VisLabel("Coins per Seconds :");
        coins.setColor(Color.BLACK);
        VisLabel coinstxt = new VisLabel(String.valueOf(this.stats.getCoinsPerSecond()));
        coinstxt.setColor(Color.BLACK);
        statsTable.add(coins);
        statsTable.add(coinstxt);
        statsTable.row();

        // Resource label
        VisLabel resource = new VisLabel(this.building.getFaction().toString() + " per Seconds :");
        resource.setColor(Color.BLACK);
        VisLabel resourcetxt = new VisLabel(String.valueOf(this.stats.getResourcesPerSecond()));
        resourcetxt.setColor(Color.BLACK);
        statsTable.add(resource);
        statsTable.add(resourcetxt);
        statsTable.row();

        // Houses label
        VisLabel homes = new VisLabel("Number of Homes :");
        homes.setColor(Color.BLACK);
        VisLabel homestxt = new VisLabel(String.valueOf(this.stats.getHomes()));
        homestxt.setColor(Color.BLACK);
        statsTable.add(homes);
        statsTable.add(homestxt);

        if (level < 5) {
            // Costs table at the top of menu table
            VisTable costsTable = new VisTable(true);
            menuTable.row();
            menuTable.add(costsTable);
            costsTable.top();
            costsTable.columnDefaults(0).left();
            costsTable.columnDefaults(1).expandX();

            // Leveling up costs label
            BuildingStats leveledUpStats = this.building.getStats(level + 1);
            // Coins
            VisLabel coinsCostLabel = new VisLabel("Coins to level up :");
            coinsCostLabel.setColor(Color.BLACK);
            VisLabel coinstxtCostLabel = new VisLabel(String.valueOf(Math.abs(leveledUpStats.getCoinCost())));
            coinstxtCostLabel.setColor(Color.BLACK);
            costsTable.add(coinsCostLabel);
            costsTable.add(coinstxtCostLabel);
            costsTable.row();

            // Resource
            VisLabel resourceCostLabel = new VisLabel(this.building.getFaction().toString() + " to level up :");
            resourceCostLabel.setColor(Color.BLACK);
            VisLabel resourcetxtCostLabel = new VisLabel(String.valueOf(Math.abs(leveledUpStats.getResourcesCost())));
            resourcetxtCostLabel.setColor(Color.BLACK);
            costsTable.add(resourceCostLabel);
            costsTable.add(resourcetxtCostLabel);

            // Menu table at the bottom of the right table
            menuTable.row();
            VisTable buttonTable = new VisTable(true);
            menuTable.add(buttonTable);
            buttonTable.bottom();
            buttonTable.defaults().growX().height(30);

            // Adding button to the table
            VisTextButton lvlupButton = new VisTextButton("Level up");
            buttonTable.add(lvlupButton);
            GameModel GameModel = game.getGameModel();

            //Disable button if not enough coins or resources
            if ((GameModel.getTownCoins() < Math.abs(leveledUpStats.getCoinCost())) || 
                (GameModel.getTownResources(building.getFaction()) < Math.abs(leveledUpStats.getResourcesCost())))
            {
                lvlupButton.setDisabled(true);
            }

            lvlupButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!lvlupButton.isDisabled()) {
                        super.clicked(event, x, y);
                        // TODO
                        System.out.println("Pouf level up");
                    }
                }
            });
        }

    }

}
