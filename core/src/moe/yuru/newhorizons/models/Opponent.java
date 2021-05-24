package moe.yuru.newhorizons.models;

/**
 * Represents player's opponent.
 * 
 * @author NextFire
 */
public interface Opponent {

    /**
     * Updates opponent's statistics since last frame.
     * 
     * @param delta last frametime
     */
    public void update(float delta);

    /**
     * Set the player's Town
     * @param player
     */
    public void setPlayer(Town player);

}
