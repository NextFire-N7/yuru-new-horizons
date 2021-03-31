package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.Array;

public class Town {

    private Array<BuildingInstance> buildings;

    public Town() {
        buildings = new Array<>();
    }

    public Array<BuildingInstance> getBuildings() {
        return buildings;
    }

}
