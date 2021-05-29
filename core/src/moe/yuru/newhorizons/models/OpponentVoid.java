package moe.yuru.newhorizons.models;

/**
 * Void opponent for solo games.
 * 
 * @author NextFire
 * @author DinoGurnari
 */
public class OpponentVoid implements Opponent {

    /**
     * Creates a void opponent.
     */
    public OpponentVoid() {
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void setPlayer(Town player) {
        // TODO Auto-generated method stub

    }

    @Override
    public float getCoins() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getResources(Faction faction) {
        // TODO Auto-generated method stub
        return 0;
    }

}
