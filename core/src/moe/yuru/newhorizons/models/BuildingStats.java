package moe.yuru.newhorizons.models;

public class BuildingStats {

    private int cost;
    private int coinsPerSecond;
    private int resourcesPerSecond;

    private BuildingStats() {
    }

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
