package moe.yuru.newhorizons.models;

import com.badlogic.gdx.utils.Array;

import moe.yuru.newhorizons.utils.Event;
import moe.yuru.newhorizons.utils.Listener;
import moe.yuru.newhorizons.utils.Notifier;

/**
 * Game model.
 */
public abstract class GameModel implements Notifier {

    private Town town;
    private Array<Listener> listeners;

    public GameModel(String mapName) {
        this.town = new Town(this, mapName);
        listeners = new Array<>();
    }

    @Override
    public void notifyListeners(Event event) {
        for (Listener listener : listeners) {
            listener.processEvent(event);
        }
    }

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    /**
     * Updates ennemy statistics since last frame.
     * 
     * @param delta last frametime
     */
    public abstract void updateEnemy(float delta);

    /**
     * @return player {@link Town}
     */
    public Town getTown() {
        return town;
    }

}
