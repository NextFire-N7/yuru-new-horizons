package moe.yuru.newhorizons.models;

import java.util.Random;

import com.badlogic.gdx.utils.ObjectMap;

/**
 * Hard difficulty opponent for versus games.
 * 
 * @author DinoGurnari
 */
public class OpponentHard implements Opponent {

    private Town player;

    private float coins;
    private ObjectMap<String, Float> resources;

    public OpponentHard() {
        this.resources = new ObjectMap<>();

        // Starting resources
        coins = 10000f;
        for (Faction faction : Faction.values()) {
            resources.put(faction.name(), 1000f);
        }
    }

    @Override
    public void update(float delta) {
        Random rand = new Random();

        // Coins
        float randCoins = player.getCoins() * 0.8f + rand.nextInt((int) (player.getCoins() / 5));
        addCoins(randCoins);

        // Faction resources
        for (Faction faction : Faction.values()) {
            float randResource = player.getResources(faction) * 0.8f
                    + rand.nextInt((int) (player.getResources(faction) / 5));
            addResources(faction, randResource);
        }

    }

    public void setPlayer(Town player) {
        this.player = player;
    }

    /**
     * @param amount to add/remove
     */
    public void addCoins(float amount) {
        if (coins + amount >= 0) {
            coins += amount;
        }
    }

    /**
     * @param faction a game faction
     * @param amount  of resources to add in this faction
     */
    public void addResources(Faction faction, float amount) {
        if (resources.get(faction.name()) + amount >= 0) {
            resources.put(faction.name(), resources.get(faction.name()) + amount);
        }
    }

    /**
     * @return current coins balance
     */
    public float getCoins() {
        return coins;
    }

    /**
     * @param faction a game faction
     * @return ressource balance in this faction
     */
    public float getResources(Faction faction) {
        return resources.get(faction.name());
    }
}
