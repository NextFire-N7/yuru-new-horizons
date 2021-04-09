package moe.yuru.newhorizons.utils;

/**
 * Can process {@link GameEvent}s send by GameNotifiers to which it has been
 * registered.
 */
public interface GameListener {

    /**
     * @param event to process
     */
    public void processEvent(GameEvent event);

}
