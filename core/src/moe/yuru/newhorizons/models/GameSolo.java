package moe.yuru.newhorizons.models;

/**
 * Solo {@link GameModel}.
 */
public class GameSolo extends GameModel {

    /**
     * Creates a new solo game.
     * 
     * @param mapName name of the chousen map
     */
    public GameSolo(String mapName) {
        super(mapName);
    }

    @Override
    public void updateEnemy(float delta) {
    }

}
