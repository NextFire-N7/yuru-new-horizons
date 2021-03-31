package moe.yuru.newhorizons.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.utils.Array;

public class BuildingModel {

    public enum Faction {

        SCIENCE, CULTURE, INDUSTRY, POLITIC

    }

    public static class BuildingStats {

        private int cost;
        private int coinsPerSecond;
        private int resourcesPerSecond;

        public int getCost() {
            return cost;
        }

        public int getCoinsPerSecond() {
            return coinsPerSecond;
        }

        public int getResourcesPerSecond() {
            return resourcesPerSecond;
        }

    }

    private String id;
    private String lastName;
    private String firstName;
    private String function;
    private Faction faction;
    private Array<BuildingStats> statsArray;

    public Texture getCharaTexture() {
        Texture charaTexture = new Texture(Gdx.files.internal("images/chara_" + id));
        charaTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return charaTexture;
    }

    public Texture getCoverTexture() {
        Texture coverTexture = new Texture(Gdx.files.internal("images/cover_" + id));
        coverTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return coverTexture;
    }

    public Texture getIconTexture() {
        Texture iconTexture = new Texture(Gdx.files.internal("images/icon_" + id));
        iconTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return iconTexture;
    }

    public BuildingStats getStats(int level) {
        return statsArray.get(level);
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFunction() {
        return function;
    }

    public Faction getFaction() {
        return faction;
    }

    public Array<BuildingStats> getStatsArray() {
        return statsArray;
    }

}
