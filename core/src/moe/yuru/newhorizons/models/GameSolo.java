package moe.yuru.newhorizons.models;

/**
 * Implementation of {@link GameModel} for a solo game.
 * 
 * @author NextFire
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
        // No ennemy in a solo game
    }

}
