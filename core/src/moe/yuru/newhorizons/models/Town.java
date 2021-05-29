package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;

/**
 * Town model. It keeps player's {@link BuildingInstance}s and tracks his
 * resources.
 * 
 * @author NextFire
 * @author DinoGurnari
 */
public class Town {

    private String mapName;

    private ObjectSet<BuildingInstance> buildings;

    private float coins;
    private ObjectMap<String, Float> resources; // Keys are strings as in JSON

    private float coinsPerSecond;
    private ObjectMap<String, Float> resourcesPerSecond; // Keys are strings as in JSON

    private float population;
    private float populationPerSecond;
    private int houses;
    private int happiness;

    /**
     * Do not use. Defined for the JSON deserializer.
     */
    @Deprecated
    public Town() {
    }

    /**
     * Creates a new town.
     * 
     * @param mapName name of the town map
     */
    public Town(String mapName) {
        this.mapName = mapName;

        // Init fields
        buildings = new ObjectSet<>();
        resources = new ObjectMap<>();
        coinsPerSecond = 0f;
        resourcesPerSecond = new ObjectMap<>();

        population = 0;
        populationPerSecond = 0f;
        houses = 0;
        happiness = 0;

        // Starting resources
        coins = 10000f;
        for (Faction faction : Faction.values()) {
            resources.put(faction.name(), 1000f);
        }
    }

    /**
     * Updates coins and resources balance since the last frame
     *
     * @param delta last frametime
     * @throws NegativeBalanceException one of the town resources is in debt
     */
    public void update(float delta) throws NegativeBalanceException {
        // Coins
        addCoins(delta * coinsPerSecond);
        // Faction resources
        for (Faction faction : Faction.values()) {
            addResources(faction, delta * resourcesPerSecond.get(faction.name(), 0f));
        }
        // Population
        try {
            addPopulation(delta * populationPerSecond);
        } catch (HousingCrisisException e) {
            // Can't have more people in town
            population = houses;
            populationPerSecond = 0f;
        }
        updateHappiness();
    }

    /**
     * Black magic, not as polished as a Pokémon damage formula but it will do the
     * trick.
     */
    private void updateHappiness() {
        float happinessf = 0f;
        for (BuildingInstance instance : buildings) {
            happinessf += instance.getModel().getHappiness();
        }
        // Take town size in account
        happinessf /= buildings.size;
        // Take housing offer in account
        happinessf *= 1 + (houses - population) / houses;
        happiness = (int) happinessf;
    }

    /**
     * Updates total town coins and resources per second
     */
    public void updatePerSecond() {
        // Reset
        coinsPerSecond = 0f;
        resourcesPerSecond = new ObjectMap<>();
        // Update
        float rpsPerFaction;
        for (BuildingInstance instance : buildings) {
            coinsPerSecond += instance.getStats().getCoinsPerSecond();
            rpsPerFaction = resourcesPerSecond.get(instance.getModel().getFaction().name(), 0f);
            rpsPerFaction += instance.getStats().getResourcesPerSecond();
            resourcesPerSecond.put(instance.getModel().getFaction().name(), rpsPerFaction);
        }
        populationPerSecond = buildings.size / 50f;
        // Account happiness
        populationPerSecond *= 1 + happiness / 100f;
    }

    /**
     * @return the name of the town map
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * @return the {@link BuildingInstance}s of this town
     */
    public ObjectSet<BuildingInstance> getBuildings() {
        return buildings;
    }

    /**
     * @return current coins balance
     */
    public float getCoins() {
        return coins;
    }

    /**
     * @param amount to add/remove
     * @throws NegativeBalanceException if balance would become negative after this
     *                                  operation
     */
    public void addCoins(float amount) throws NegativeBalanceException {
        if (coins + amount < 0) {
            throw new NegativeBalanceException();
        }
        coins += amount;
    }

    /**
     * @return coins income per second
     */
    public float getCoinsPerSecond() {
        return coinsPerSecond;
    }

    /**
     * @param faction a game faction
     * @return ressource balance in this faction
     */
    public float getResources(Faction faction) {
        return resources.get(faction.name());
    }

    /**
     * @param faction a game faction
     * @param amount  of resources to add in this faction
     * @throws NegativeBalanceException if balance would become negative after this
     *                                  operation
     */
    public void addResources(Faction faction, float amount) throws NegativeBalanceException {
        if (resources.get(faction.name()) + amount < 0) {
            throw new NegativeBalanceException();
        }
        resources.put(faction.name(), resources.get(faction.name()) + amount);
    }

    /**
     * @param faction
     * @return resource income per second
     */
    public float getResourcesPerSecond(Faction faction) {
        return resourcesPerSecond.get(faction.name(), 0f);
    }

    /**
     * @return town population
     */
    public float getPopulation() {
        return population;
    }

    /**
     * @param population new people in town
     * @throws HousingCrisisException when more people than empty houses
     */
    public void addPopulation(float population) throws HousingCrisisException {
        if (this.population + population > houses) {
            throw new HousingCrisisException();
        }
        this.population += population;
    }

    /**
     * @return "babies" per second if that makes sense
     */
    public float getPopulationPerSecond() {
        return populationPerSecond;
    }

    /**
     * @return number of houses in town
     */
    public int getHouses() {
        return houses;
    }

    /**
     * @param houses new houses in town (+/-)
     * @throws HousingCrisisException when too much houses have been removed
     */
    public void addHouses(int houses) throws HousingCrisisException {
        if (this.houses + houses < population) {
            throw new HousingCrisisException();
        }
        this.houses += houses;
    }

    /**
     * @return happiness level of the town
     */
    public int getHappiness() {
        return happiness;
    }

}
