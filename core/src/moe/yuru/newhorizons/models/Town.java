package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.Array;

public class Town {

    private String layoutName;
    private Array<BuildingInstance> buildings;

    public Town(String layoutName) {
        this.layoutName = layoutName;
        buildings = new Array<>();
    }

    public String getLayoutName() {
        return this.layoutName;
    }

    public Array<BuildingInstance> getBuildings() {
        return buildings;
    }

}
