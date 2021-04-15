package moe.yuru.newhorizons.models;

/**
 * The possible factions for a building.
 */
public enum Faction {

    SCIENCE("Science"), CULTURE("Culture"), INDUSTRY("Industry"), POLITICS("Politics");

    private String name;

    private Faction(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
