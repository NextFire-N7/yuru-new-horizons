package moe.yuru.newhorizons.models;

import java.util.Random;

import com.badlogic.gdx.utils.ObjectMap;

/**
 * Normal difficulty opponent for versus games.
 * 
 * @author DinoGurnari
 */
public class OpponentNormal implements Opponent {

    private Town player;

    private float coins;
    private ObjectMap<String, Float> resources;

    public OpponentNormal() {
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
        if (Math.abs(player.getCoins() - this.coins) > 0.1f * player.getCoins()) {
            this.coins = player.getCoins() * 0.95f;
        } else {
            this.coins = this.coins + delta * (0.75f + rand.nextFloat() / 2) * player.getCoinsPerSecond();
        }

        // Faction resources
        for (Faction faction : Faction.values()) {
            float randResource;

            if (Math.abs(player.getResources(faction) - getResources(faction)) > 0.1f * player.getResources(faction)) {
                randResource = player.getResources(faction) * 0.95f;
            } else {
                randResource = getResources(faction)
                        + delta * (0.5f + rand.nextFloat() / 2) * player.getResourcesPerSecond(faction);
            }
            setResources(faction, randResource);
        }

    }

    public void setPlayer(Town player) {
        this.player = player;
    }

    /**
     * @param faction a game faction
     * @param amount  of resources to add in this faction
     */
    public void setResources(Faction faction, float amount) {
        if (amount >= 0) {
            resources.put(faction.name(), amount);
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
