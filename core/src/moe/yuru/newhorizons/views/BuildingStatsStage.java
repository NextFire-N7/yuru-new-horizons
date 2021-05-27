package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.Building;
import moe.yuru.newhorizons.models.BuildingStats;

/**
 * Shows the stats on the right
 * 
 * @author DinoGurnari
 */
public class BuildingStatsStage extends Stage {

    private YuruNewHorizons game;
    private Building building;
    private BuildingStats stats;

    private VisTable statsTable;

    /**
     * Create a Building's stats stage for the correct building and level
     * @param game the game instance
     * @param building the building
     * @param level its level
     */
    BuildingStatsStage(YuruNewHorizons game, Building building, int level) {
        super(game.getViewport(), game.getBatch());
        this.building = building;
        this.game = game;
        this.stats = building.getStats(level);

        // A full height table just at the right of the map
        statsTable = new VisTable();
        addActor(statsTable);
        statsTable.setSize(512, 720);
        statsTable.setPosition(768, 0);
        //statsTable.defaults().grow().pad(10);

        // Coins balance
        statsTable.add(new VisLabel("Coins per Seconds :"));
        statsTable.add(new VisLabel(String.valueOf(this.stats.getCoinsPerSecond())));
        statsTable.row();

        // Resource label
        statsTable.add(new VisLabel(this.building.getFaction().toString() + " per Seconds :"));
        statsTable.add(new VisLabel(String.valueOf(this.stats.getResourcesPerSecond())));
        statsTable.row();

        // Houses label
        statsTable.add(new VisLabel("Number of Homes :"));
        statsTable.add(new VisLabel(String.valueOf(this.stats.getHomes())));
    }

}
