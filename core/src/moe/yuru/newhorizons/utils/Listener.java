package moe.yuru.newhorizons.utils;

/**
 * Can process {@link Event}s send by Notifiers to which it has been registered.
 * 
 * @author NextFire
 */
public interface Listener {

    /**
     * @param event to process
     */
    public void processEvent(Event event);

}
