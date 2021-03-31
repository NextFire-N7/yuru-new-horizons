package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.Array;

public class BuildingModel {

    public enum Faction {

        SCIENCE, CULTURE, INDUSTRY, POLITIC

    }

    public static class BuildingStats {

        private int cost;
        private int coinsPerSecond;
        private int resourcesPerSecond;

        public int getCost() {
            return cost;
        }

        public int getCoinsPerSecond() {
            return coinsPerSecond;
        }

        public int getResourcesPerSecond() {
            return resourcesPerSecond;
        }

    }

    private String id;
    private String lastName;
    private String firstName;
    private String function;
    private Faction faction;
    private Array<BuildingStats> statsArray;

    public BuildingStats getStats(int level) {
        return statsArray.get(level);
    }

    public String getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFunction() {
        return function;
    }

    public Faction getFaction() {
        return faction;
    }

    public Array<BuildingStats> getStatsArray() {
        return statsArray;
    }

}
