package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.IntMap;

/**
 * Model which contains generic attributes related to a building.
 * 
 * @author NextFire
 */
public class Building {

    private String id;

    private String lastName;
    private String firstName;

    private String function;
    private Faction faction;

    private float sizeX;
    private float sizeY;

    private IntMap<BuildingStats> stats;

    /**
     * Do not use. Defined for the JSON deserializer.
     */
    @Deprecated
    public Building() {
    }

    /**
     * @return the building unique id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the building character last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the building character first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the raison d'être of the building
     */
    public String getFunction() {
        return function;
    }

    /**
     * @return the building {@link Faction}
     */
    public Faction getFaction() {
        return faction;
    }

    /**
     * @return the building size on the X axis (width)
     */
    public float getSizeX() {
        return sizeX;
    }

    /**
     * @return the building size on the Y axis (height)
     */
    public float getSizeY() {
        return sizeY;
    }

    /**
     * @return the whole {@link IntMap} of stats for all levels
     */
    public IntMap<BuildingStats> getStats() {
        return stats;
    }

    /**
     * @param level between {@code 1} and {@code 5}
     * @return the correspondant {@link BuildingStats}
     */
    public BuildingStats getStats(int level) {
        return stats.get(level);
    }

}
