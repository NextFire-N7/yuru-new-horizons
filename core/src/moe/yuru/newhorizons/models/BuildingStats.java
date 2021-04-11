package moe.yuru.newhorizons.models;

/**
 * Represents the building statistics for a level. The type of the resource
 * depends of the current faction.
 */
public class BuildingStats {

    private float coinCost;
    private float resourcesCost;
    private float coinsPerSecond;
    /** Raw number, the type of the resource depends of the current faction */
    private float resourcesPerSecond;

    /**
     * Do not use. It is defined for the json deserializer.
     */
    public BuildingStats() {
    }

    /**
     * @return the upgrade cost (coins)
     */
    public float getCoinCost() {
        return coinCost;
    }

    /**
     * @return the upgrade cost (resources)
     */
    public float getResourcesCost() {
        return resourcesCost;
    }

    /**
     * @return the coins generated/lost per second
     */
    public float getCoinsPerSecond() {
        return coinsPerSecond;
    }

    /**
     * @return the resources generated/lost per second
     */
    public float getResourcesPerSecond() {
        return resourcesPerSecond;
    }

}
