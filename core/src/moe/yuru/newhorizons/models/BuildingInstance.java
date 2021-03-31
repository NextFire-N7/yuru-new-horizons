package moe.yuru.newhorizons.models;

import moe.yuru.newhorizons.models.BuildingModel.BuildingStats;

public class BuildingInstance {

    private BuildingModel model;
    private int level;
    private float posX;
    private float posY;

    public BuildingInstance(BuildingModel model, float posX, float posY) {
        this.model = model;
        level = 0;
        this.posX = posX;
        this.posY = posY;
    }

    public BuildingStats getStats() {
        return model.getStats(level);
    }

    public BuildingModel getModel() {
        return model;
    }

    public void setModel(BuildingModel model) {
        this.model = model;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

}
