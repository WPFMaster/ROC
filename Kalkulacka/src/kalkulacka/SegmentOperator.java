/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kalkulacka;

import java.util.Deque;

/**
 *
 * @author dominik.dembinny.s
 */
public interface SegmentOperator extends Segment, SegmentFunction, SegmentItem {
    
    /**
     * When computing with side is associative.
     * The left associative operators are all except Power.
     * 
     * @return True, when is Left associative.
     */
    public boolean isLeftAssociative();
}
