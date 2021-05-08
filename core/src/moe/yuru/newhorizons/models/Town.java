package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import moe.yuru.newhorizons.utils.Event;
import moe.yuru.newhorizons.utils.SaveGame;

/**
 * Town model. Linked to a {@link GameModel}, it keeps player's
 * {@link BuildingInstance}s and tracks his resources.
 * 
 * @author NextFire
 */
public class Town implements Json.Serializable {

    private GameModel gameModel;
    // private String mapName;

    // private ObjectSet<BuildingInstance> buildings;

    // private float coins;
    // private ObjectMap<Faction, Float> resources;

    // private Float coinsPerSecond;
    // private ObjectMap<Faction, Float> resourcesPerSecond;

    // private Building toPlace;
    private TownContent content;

    /**
     * Creates a new town linked to the specified {@link GameModel}.
     * 
     * @param gameModel game model
     * @param mapName   name of the town map
     */
    public Town(GameModel gameModel, String mapName) {
        this.gameModel = gameModel;
        this.content = new TownContent();
        if (gameModel instanceof GameSolo) {
                this.content.setGameMode(true);
            } else {
                this.content.setGameMode(false);
            }
        this.content.setMapName(mapName);

        // Init fields
        this.content.setBuildings(new ObjectSet<>());
        this.content.setResources(new ObjectMap<>());
        this.content.setCoinsPerSecond(0f);
        this.content.setResourcesPerSecond(new ObjectMap<>());
        
        // Starting resources
        this.content.setCoins(10000f);
        for (Faction faction : Faction.values()) {
            this.content.getResources().put(faction, 1000f);
        }
    }

    /**
     * Constructor used to load a game
     * @param gameModel
     * @param townContent
     */
    public Town(GameModel gameModel, TownContent townContent) {
        this.gameModel = gameModel;
        SaveGame.testjson(townContent);
        this.content = townContent;
    }

    /**
     * Validates current location for the pending construction and create and add
     * the associated {@link BuildingInstance} to the town. Fires an event when
     * done.
     *
     * @param x X axis position
     * @param y Y axis position
     * @return the building instance created
     */
    public void validateConstruction(float x, float y) {
        addCoins(this.content.getToPlace().getStats(1).getCoinCost());
        addResources(this.content.getToPlace().getFaction(), this.content.getToPlace().getStats(1).getResourcesCost());

        BuildingInstance instance = new BuildingInstance(this.content.getToPlace(), x, y);
        this.content.getBuildings().add(instance);
        this.setToPlace(null);
        gameModel.notifyListeners(new Event(this, "toPlace", null));
        updatePerSecond();
        gameModel.notifyListeners(new Event(this, "validated", instance));
    }

    /**
     * Updates coins and resources balance since the last frame
     *
     * @param delta last frametime
     */
    public void updateBalance(float delta) {
        // Coins
        addCoins(delta * this.content.getCoinsPerSecond());
        // Faction resources
        for (Faction faction : Faction.values()) {
            addResources(faction, delta * this.content.getResourcesPerSecond().get(faction, 0f));
        }
    }

    /**
     * Updates total town coins and resources per second
     */
    public void updatePerSecond() {
        // Reset
        this.content.setCoinsPerSecond(0f);
        this.content.setResourcesPerSecond(new ObjectMap<>());
        // Update
        float rpsPerFaction;
        for (BuildingInstance instance : this.content.getBuildings()) {
            this.content.setCoinsPerSecond(this.content.getCoinsPerSecond() + instance.getStats().getCoinsPerSecond());
            rpsPerFaction = this.content.getResourcesPerSecond().get(instance.getModel().getFaction(), 0f);
            rpsPerFaction += instance.getStats().getResourcesPerSecond();
            this.content.getResourcesPerSecond().put(instance.getModel().getFaction(), rpsPerFaction);
        }
    }

    /**
     * @return true if the game mode is Solo
     */
    public boolean getGameMode() {
        return(this.gameModel instanceof GameSolo);
    }

    /**
     * @return the name of the town map
     */
    public String getMapName() {
        return this.content.getMapName();
    }

    /**
     * @return the {@link BuildingInstance}s of this town
     */
    public ObjectSet<BuildingInstance> getBuildings() {
        return this.content.getBuildings();
    }

    /**
     * @return current coins balance
     */
    public float getCoins() {
        return this.content.getCoins();
    }

    /**
     * @param amount to add/remove
     */
    public void addCoins(float amount) {
        this.content.setCoins(this.content.getCoins() + amount);
    }

    /**
     * @return current resources balance
     */
    public ObjectMap<Faction, Float> getResources() {
        return this.content.getResources();
    }

    /**
     * @param faction {@link Faction}
     * @return {@code faction} ressource balance
     */
    public float getResources(Faction faction) {
        return this.content.getResources().get(faction);
    }

    /**
     * @param faction to add resources
     * @param amount  of resources to add
     */
    public void addResources(Faction faction, float amount) {
        this.content.getResources().put(faction, this.content.getResources().get(faction) + amount);
    }

    /**
     * @return current {@link Building} to be placed
     */
    public Building getToPlace() {
        return this.content.getToPlace();
    }

    /**
     * Sets a pending building to be placed. Fires an event when done.
     *
     * @param building to be placed
     */
    public void setToPlace(Building building) {
        this.content.setToPlace(building);
        gameModel.notifyListeners(new Event(this, "toPlace", this.content.getToPlace()));
    }

    /**
     * Custom write method for Town to make a custom serialization.
     */
    @Override
    public void write(Json json) {
        // if (gameModel instanceof GameSolo) {
        //     json.writeValue("gameModel", "GameSolo");
        // } else {
        //     json.writeValue("gameModel", "GameMulti");
        // }
        // json.writeValue("mapName", mapName);
        // json.writeValue("buildings", buildings);
        // json.writeValue("coins", coins);
        // json.writeValue("resources", resources);
        // json.writeValue("coinsPerSecond", coinsPerSecond);
        // json.writeValue("resourcesPerSecond",resourcesPerSecond);
        // json.writeValue("toPlace", toPlace);

    }

    /**
     * Custom read method for Town to make a custom serialization.
     */
    @Override
    public void read(Json json, JsonValue jsonData) {
        // if (jsonData.child().asString().equals("GameSolo")) {
        //     gameModel = new GameSolo(jsonData.child().next().asString(), this);
        // }
        json.readFields(this, jsonData);
    }

    public TownContent getTownContent() {
        return this.content;
    }

}
