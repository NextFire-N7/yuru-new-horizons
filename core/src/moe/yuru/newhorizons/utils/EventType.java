package moe.yuru.newhorizons.utils;

/**
 * Possible event types.
 * 
 * @author NextFire
 */
public interface EventType {

    /**
     * Building constructions related.
     */
    public enum Construction implements EventType {

        /** A validated construction */
        VALIDATED,
        /** A new building or nothing has to be placed */
        TO_PLACE

    }

}
