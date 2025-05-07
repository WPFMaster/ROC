package kalkulacka.SegmentTypes;

import java.util.Deque;
import kalkulacka.Segment;
import kalkulacka.SegmentOperator;

/**
 * Adds two elements algebraically together.
 *
 * @author dominik.dembinny.s
 */
public class SegmentAdd implements SegmentOperator {

    @Override
    public void run(Deque<Double> list) {
        list.push(list.pop() + list.pop());
    }

    /**
     * 
     * @return 1
     */
    @Override
    public int getPriority() {
        return 1;
    }

    /**
     * 
     * @return true
     */
    @Override
    public boolean isLeftAssociative() {
        return true;
    }
    
    /**
     * 
     * @return '+'
     */
    @Override
    public String getExactRepresentation() {
        return "+";
    }
    
    @Override
    public String toString() {
        return "SegmentAdd";
    }
}
