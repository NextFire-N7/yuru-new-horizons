package moe.yuru.newhorizons.utils;

/**
 * Can send GameEvents to registered {@link GameListener}s.
 */
public interface Notifier {

    /**
     * Sends an event to all registered listeners.
     * 
     * @param name       of the event
     * @param attachment any object to be attached
     */
    public void notifyListeners(Event event);

    /**
     * @param listener to be registered
     */
    public void addListener(Listener listener);

}
