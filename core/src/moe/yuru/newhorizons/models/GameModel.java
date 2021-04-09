package moe.yuru.newhorizons.models;

/**
 * Game model.
 */
public interface GameModel {

    /**
     * Updates ennemy statistics since last frame.
     * 
     * @param delta last frametime
     */
    public void updateEnemy(float delta);

    /**
     * @return player {@link Town}
     */
    public Town getTown();

}
