package moe.yuru.newhorizons.models;

/**
 * Solo {@link GameModel}.
 */
public class GameSolo implements GameModel {

    private Town town;

    public GameSolo(Town town) {
        this.town = town;
    }

    @Override
    public void updateEnemy(float delta) {
    }

    @Override
    public Town getTown() {
        return town;
    }

}
