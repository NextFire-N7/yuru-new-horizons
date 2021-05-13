package moe.yuru.newhorizons.utils;

/**
 * Simple event in the game.
 * 
 * @author NextFire
 */
public class Event {

    private Object source;
    private EventType type;
    private Object value;

    /**
     * Creates a new event to be send to listeners.
     * 
     * @param source of the event (probably {@code this})
     * @param type   {@link EventType} of the event
     * @param value  any attachement, can be {@code null}
     */
    public Event(Object source, EventType type, Object value) {
        this.source = source;
        this.type = type;
        this.value = value;
    }

    /**
     * @return the source of the event
     */
    public Object getSource() {
        return source;
    }

    /**
     * @return the {@link EventType} of the event
     */
    public EventType getType() {
        return type;
    }

    /**
     * @return the attached value
     */
    public Object getValue() {
        return value;
    }

}
