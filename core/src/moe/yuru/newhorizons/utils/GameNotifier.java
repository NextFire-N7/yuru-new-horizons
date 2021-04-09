package moe.yuru.newhorizons.utils;

/**
 * Can send GameEvents to registered {@link GameListener}s.
 */
public interface GameNotifier {

    /**
     * @param listener to be registered
     */
    public void addListener(GameListener listener);

}
