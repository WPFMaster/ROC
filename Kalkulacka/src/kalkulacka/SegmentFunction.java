package kalkulacka;

import java.util.Deque;

/**
 * Specifies the {@link SegmentItem} to only items that can by processed, when evaluating problem.
 *
 * @author dominik.dembinny.s
 */
public interface SegmentFunction extends SegmentItem {
    
    /**
     * Modification of the evaluating stack based on function implementation.
     * 
     * @param list the Deque evaluating stack
     */
    public void run(Deque<Double> list);
    
    public int getPriority();
}
