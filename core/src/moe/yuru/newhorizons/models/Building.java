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
    /** Contains the stats per level */
    private IntMap<BuildingStats> statsArray;

    /**
     * @param level The onscreen level minus one
     * @return the correspondant {@link BuildingStats}
     */
    public BuildingStats getStats(int level) {
        return statsArray.get(level);
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
     * @return the building {@link Faction}.
     */
    public Faction getFaction() {
        return faction;
    }

    /**
     * @return the whole {@link IntMap} of stats for all levels
     */
    public IntMap<BuildingStats> getStatsArray() {
        return statsArray;
    }

}
