package moe.yuru.newhorizons.models;

/**
 * Game model.
 */
public interface GameModel {

    /**
     * Update ennemy statistics since last frame.
     * 
     * @param delta last frametime
     */
    public void updateEnemy(float delta);

    /**
     * @return player {@link Town}
     */
    public Town getTown();

}
