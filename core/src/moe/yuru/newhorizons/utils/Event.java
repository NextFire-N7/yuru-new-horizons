package moe.yuru.newhorizons.utils;

/**
 * Simple event in the game.
 */
public class Event {

    private Object source;
    private String name;
    private Object value;

    /**
     * Creates a new event to be send to listeners.
     * 
     * @param source of the event (probably {@code this})
     * @param name   of the event
     * @param value  any attachement, can be {@code null}
     */
    public Event(Object source, String name, Object value) {
        this.source = source;
        this.name = name;
        this.value = value;
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
     * @return the attached value
     */
    public Object getValue() {
        return value;
    }

}
