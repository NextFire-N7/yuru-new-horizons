package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.Building;

/**
 * Shows the stats on the right
 * 
 * @author DinoGurnari
 */
public class BuildingStatsStage extends Stage {

    private YuruNewHorizons game;
    private Building building;
    private int level;

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
        this.level = level;
    }

}
