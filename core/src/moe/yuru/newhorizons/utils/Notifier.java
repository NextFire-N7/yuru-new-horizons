package moe.yuru.newhorizons.utils;

/**
 * Can send Events to previously registered {@link Listener}s.
 */
public interface Notifier {

    /**
     * Sends an event to all registered listeners.
     * 
     * @param event to be sent
     */
    public void notifyListeners(Event event);

    /**
     * @param listener to be registered
     */
    public void addListener(Listener listener);

}
