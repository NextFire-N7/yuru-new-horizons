package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.Array;

import moe.yuru.newhorizons.stages.LayoutStage;

/**
 * Town model.
 */
public class Town {

    private String layoutName;
    private Array<BuildingInstance> buildings;

    /**
     * @param layoutName name of the image used on {@link LayoutStage}
     */
    public Town(String layoutName) {
        this.layoutName = layoutName;
        buildings = new Array<>();
    }

    /**
     * @return the name of the layout image
     */
    public String getLayoutName() {
        return this.layoutName;
    }

    /**
     * @return the {@link BuildingInstance}s of this town as an {@link Array}
     */
    public Array<BuildingInstance> getBuildings() {
        return buildings;
    }

}
