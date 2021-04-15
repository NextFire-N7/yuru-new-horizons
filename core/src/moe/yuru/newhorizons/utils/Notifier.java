package moe.yuru.newhorizons.utils;

import com.badlogic.gdx.utils.ObjectSet;

/**
 * Can send Events to previously registered {@link Listener}s.
 */
public abstract class Notifier {

    private ObjectSet<Listener> listeners;

    /**
     * Initializes the listeners set.
     */
    public Notifier() {
        listeners = new ObjectSet<>();
    }

    /**
     * Sends an event to all registered listeners.
     * 
     * @param event to be sent
     */
    public void notifyListeners(Event event) {
        for (Listener listener : listeners) {
            listener.processEvent(event);
        }
    }

    /**
     * @param listener to be registered
     */
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    /**
     * @param listener to be unregistered
     */
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

}