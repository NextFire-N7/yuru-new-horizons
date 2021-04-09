package moe.yuru.newhorizons.utils;

/**
 * Simple event in the game.
 */
public class GameEvent {

    private Object source;
    private String name;
    private Object object;

    /**
     * Creates a new event to be send to listeners.
     * 
     * @param source of the event (probably {@code this})
     * @param name   of the event
     * @param object any attachement, can be {@code null}
     */
    public GameEvent(Object source, String name, Object object) {
        this.source = source;
        this.name = name;
        this.object = object;
    }

    /**
     * @return the source of the event
     */
    public Object getSource() {
        return source;
    }

    /**
     * @return the name of the event
     */
    public String getName() {
        return name;
    }

    /**
     * @return the attached object
     */
    public Object getObject() {
        return object;
    }

}
