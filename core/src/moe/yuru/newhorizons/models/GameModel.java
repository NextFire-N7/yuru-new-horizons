package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.ObjectSet;

import moe.yuru.newhorizons.utils.Event;
import moe.yuru.newhorizons.utils.EventType;
import moe.yuru.newhorizons.utils.Notifier;

/**
 * Game model. Contains player's town and opponent. Extends {@link Notifier} so
 * it can notify possible registered listeners. Also serves as a {@link Town}
 * proxy.
 * 
 * 
 * @author NextFire
 * @author GurnariD
 */
public class GameModel extends Notifier {

    private Town town;
    private Opponent opponent;

    private Building toPlace;

    /**
     * Do not use. Defined for the JSON deserializer.
     */
    @Deprecated
    public GameModel() {
    }

    /**
     * Initializes the notifier and the game town.
     * 
     * @param mapName  name of the chousen map
     * @param opponent player's {@link Opponent}
     */
    public GameModel(String mapName, Opponent opponent) {
        super();
        town = new Town(mapName);
        this.opponent = opponent;
        this.opponent.setPlayer(town);
        toPlace = null;
    }

    /**
     * Updates town and opponent.
     * 
     * @param delta last frametime
     */
    public void update(float delta) {
        try {
            town.update(delta);
        } catch (NegativeBalanceException e) {
            // TODO Auto-generated catch block: player lost
            e.printStackTrace();
        }
        opponent.update(delta);
    }

    /**
     * Validates current location for the pending construction and create and add
     * the associated {@link BuildingInstance} to the town. Fires events.
     *
     * @param x X axis position of the bottom left corner
     * @param y Y axis position of the bottom left corner
     * @return the building instance created
     * @throws NegativeBalanceException
     * @event {@code EventType.Construction.TO_PLACE}
     */
    public void validateConstruction(float x, float y) throws NegativeBalanceException {
        if (checkPosition(toPlace, x, y)) {
            try {
                construct(toPlace, 1, x, y);
            } finally {
                // Anyway there is nothing to place anymore
                toPlace = null;
                notifyListeners(new Event(this, EventType.Construction.TO_PLACE, null));
            }
        } else {
            // Position was not valid, retry
            setToPlace(toPlace);
        }
    }

    /**
     * Pays and registers a new building.
     * 
     * @param building to construct
     * @param level    of the building
     * @param x        X axis position of the bottom left corner
     * @param yY       axis position of the bottom left corner
     * @throws NegativeBalanceException
     * @event {@code EventType.Construction.VALIDATED} The construction is validated
     *        and the player has paid. Returns instance.
     */
    private void construct(Building building, int level, float x, float y) throws NegativeBalanceException {
        // Pay the construction
        town.addCoins(building.getStats(level).getCoinCost());
        town.addResources(building.getFaction(), building.getStats(level).getResourcesCost());
        // Add homes
        try {
            town.addHouses(building.getStats(level).getHomes());
        } catch (HousingCrisisException e) {
            // We only add houses here, this exception should not be thrown...
            e.printStackTrace();
        }
        // Create the new instance
        BuildingInstance instance = new BuildingInstance(building, level, x, y);
        town.getBuildings().add(instance);
        town.updatePerSecond();
        // Inform listeners
        notifyListeners(new Event(this, EventType.Construction.VALIDATED, instance));
    }

    /**
     * Level up a building.
     * 
     * @param building to level up
     * @throws NegativeBalanceException
     * @event {@code EventType.Construction.VALIDATED} The construction is validated
     *        and the player has paid. Returns instance.
     */
    public void levelUpBuilding(BuildingInstance building) throws NegativeBalanceException {
        int level = building.getLevel();
        // Pay the construction
        town.addCoins(building.getModel().getStats(level + 1).getCoinCost());
        town.addResources(building.getModel().getFaction(), building.getModel().getStats(level + 1).getResourcesCost());
        // Add homes
        try {
            town.addHouses(building.getModel().getStats(level + 1).getHomes() - building.getModel().getStats(level).getHomes());
        } catch (HousingCrisisException e) {
            // We only add houses here, this exception should not be thrown...
            e.printStackTrace();
        }

        building.levelup();
        town.updatePerSecond();
        // Inform listeners
        notifyListeners(new Event(this, EventType.Construction.LEVELED_UP, building));
    }

    /**
     * Ensure that the new building will not overlap existing ones.
     * 
     * @param building to place
     * @param x        X axis position of the origin (bottom left)
     * @param y        Y axis position of the origin (bottom left)
     * @return {@code true} if the position is ok, {@code false} otherwise
     */
    private boolean checkPosition(Building building, float x, float y) {
        for (BuildingInstance instance : town.getBuildings()) {
            if (instance.getPosX() - building.getSizeX() < x && x < instance.getPosX() + instance.getModel().getSizeX()
                    && instance.getPosY() - building.getSizeY() < y
                    && y < instance.getPosY() + instance.getModel().getSizeY()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sets a pending building to be placed. Fires an event when done. by the
     * player. Returns the building model.
     * 
     * @param building to be placed
     * @event {@code EventType.Construction.TO_PLACE} A new building must be placed
     */
    public void setToPlace(Building building) {
        toPlace = building;
        notifyListeners(new Event(this, EventType.Construction.TO_PLACE, toPlace));
    }

    /**
     * @return town map name
     */
    public String getTownMapName() {
        return town.getMapName();
    }

    /**
     * @return town building instances
     */
    public ObjectSet<BuildingInstance> getTownBuildings() {
        return town.getBuildings();
    }

    /**
     * @return town coins
     */
    public float getTownCoins() {
        return town.getCoins();
    }

    /**
     * @param faction
     * @return town resources in this given faction
     */
    public float getTownResources(Faction faction) {
        return town.getResources(faction);
    }

    /**
     * @return if the game is Solo
     */
    public boolean isSolo() {
        return this.opponent instanceof OpponentVoid;
    }

    /**
     * @return oppponent coins
     */
    public float getOpponentCoins() {
        return this.opponent.getCoins();
    }

    /**
     * @param faction a game faction
     * @return oppponent resources in this given faction
     */
    public float getOpponentResources(Faction faction) {
        return this.opponent.getResources(faction);
    }

    /*
     * @return town coins income per second
     */
    public float getCoinsPerSecond() {
        return town.getCoinsPerSecond();
    }

    /**
     * @param faction
     * @return town resource income per second
     */
    public float getResourcesPerSecond(Faction faction) {
        return town.getResourcesPerSecond(faction);
    }

    /**
     * @return town population
     */
    public float getPopulation() {
        return town.getPopulation();
    }

    /**
     * @return "babies" per second if that makes sense
     */
    public float getPopulationPerSecond() {
        return town.getPopulationPerSecond();
    }

    /**
     * @return number of houses in town
     */
    public int getHouses() {
        return town.getHouses();
    }

    /**
     * @return town happinness
     */
    public int getHappiness() {
        return town.getHappiness();
    }

}
