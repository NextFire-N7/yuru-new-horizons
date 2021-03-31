package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.Array;

/**
 * Building model.
 */
public class Building {

    /**
     * The possible factions for a building.
     */
    public enum Faction {

        SCIENCE, CULTURE, INDUSTRY, POLITIC

    }

    /**
     * Represents the building statistics for a level. The type of the resource
     * depends of the current faction.
     */
    public static class BuildingStats {

        private int coinCost;
        private int resourcesCost;
        private int coinsPerSecond;
        /** Raw number, the type of the resource depends of the current faction */
        private int resourcesPerSecond;

        /**
         * @return the upgrade cost (coins)
         */
        public int getCoinCost() {
            return coinCost;
        }

        /**
         * @return the upgrade cost (resources)
         */
        public int getResourcesCost() {
            return resourcesCost;
        }

        /**
         * @return the coins generated/lost per second
         */
        public int getCoinsPerSecond() {
            return coinsPerSecond;
        }

        /**
         * @return the resources generated/lost per second
         */
        public int getResourcesPerSecond() {
            return resourcesPerSecond;
        }

    }

    private String id;
    private String lastName;
    private String firstName;
    private String function;
    private Faction faction;
    /** Contains the stats per level */
    private Array<BuildingStats> statsArray;

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
     * @return the whole {@link Array} of stats for all levels
     */
    public Array<BuildingStats> getStatsArray() {
        return statsArray;
    }

}
