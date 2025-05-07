package kalkulacka.SegmentTypes;

import kalkulacka.SegmentItem;

/**
 * Used only to get trace of where in the stack the bracket starts.
 *
 * @author dominik.dembinny.s
 */
public class SegmentBracket implements SegmentItem {

    /**
     * Never actually used. More often used instance of {@link SegmentBracket}
     * 
     * @return 5
     */
    public int getPriority() {
        return 5;
    }

    @Override
    public String getExactRepresentation() {
        return "(";
    }
}
