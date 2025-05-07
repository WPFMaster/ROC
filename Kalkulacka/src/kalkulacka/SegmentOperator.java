package kalkulacka;

/**
 * The type of Segment that operates on two numbers. This is important for the associative decision.
 *
 * @author dominik.dembinny.s
 */
public interface SegmentOperator extends SegmentFunction, SegmentItem {
    
    /**
     * When computing with side is associative.
     * The left associative operators are all except Power.
     * 
     * @return True, when is Left associative.
     */
    public boolean isLeftAssociative();
}
