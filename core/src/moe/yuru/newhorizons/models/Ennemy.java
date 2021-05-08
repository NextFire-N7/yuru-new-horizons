package moe.yuru.newhorizons.models;

public abstract class Ennemy {

    public Ennemy() {
    }

    /**
     * Updates ennemy statistics since last frame.
     * 
     * @param delta last frametime
     */
    abstract public void update(float delta);

}
