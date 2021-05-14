package moe.yuru.newhorizons.utils;

/**
 * Possible event types.
 * 
 * @author NextFire
 */
public interface EventType {

    /**
     * Building constructions related.
     * 
     * @author NextFire
     */
    public enum Construction implements EventType {

        VALIDATED, TO_PLACE

    }

}
