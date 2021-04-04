package moe.yuru.newhorizons.models;

/**
 * Game model.
 */
public class GameModel {

    private Town town;

    public GameModel(Town town) {
        this.town = town;
    }

    public Town getTown() {
        return town;
    }

}
