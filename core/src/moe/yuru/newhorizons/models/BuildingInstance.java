package moe.yuru.newhorizons.models;

/**
 * {@link Building} instance and attributes once it has been built by the
 * player.
 */
public class BuildingInstance {

    /** The {@link Building} of this current instance */
    private Building model;
    /** The actual building level */
    private int level;
    /** Position on screen: X axis */
    private float posX;
    /** Position on screen: Y axis */
    private float posY;

    /**
     * @param model of the instance
     * @param posX  on screen
     * @param posY  on screen
     */
    public BuildingInstance(Building model, float posX, float posY) {
        this.model = model;
        level = 0;
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * @return the building stats for its current {@link BuildingInstance#level}
     */
    public BuildingStats getStats() {
        return model.getStats(level);
    }

    /**
     * @return the {@link Building} of the instance
     */
    public Building getModel() {
        return model;
    }

    /**
     * @return the current building model
     */
    public int getLevel() {
        return level;
    }

    /**
     * Level up this building.
     */
    public void levelUp() {
        level++;
    }

    /**
     * @return the X position of the building
     */
    public float getPosX() {
        return posX;
    }

    /**
     * @param posX the new X position of the building
     */
    public void setPosX(float posX) {
        this.posX = posX;
    }

    /**
     * @return the Y position of the building
     */
    public float getPosY() {
        return posY;
    }

    /**
     * @param posY the new Y position of the building
     */
    public void setPosY(float posY) {
        this.posY = posY;
    }

}
