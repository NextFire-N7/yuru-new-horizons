package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.Array;

public class BuildingModel {

    public enum Faction {
        SCIENCE, CULTURE, INDUSTRY, POLITIC
    }

    private CharacterModel character;
    private String function;
    private Faction faction;
    private Array<BuildingStats> statsPerLevel;

    private BuildingModel() {
    }

}
