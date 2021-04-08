package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.IntMap;

/**
 * Building model.
 */
public class Building {

    private String id;
    private String lastName;
    private String firstName;
    private String function;
    private Faction faction;
    private float sizeX;
    private float sizeY;
    /** Contains the stats per level */
    private IntMap<BuildingStats> stats;

    public Building() {
        // Must be defined for the json deserializer.
    }

    /**
     * @return the building id
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
     * @return the building size on the X axis
     */
    public float getSizeX() {
        return sizeX;
    }

    /**
     * @return the building size on the Y axis
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
     * @param level The onscreen level
     * @return the correspondant {@link BuildingStats}
     */
    public BuildingStats getStats(int level) {
        return stats.get(level);
    }

}
