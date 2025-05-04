/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kalkulacka.SegmentTypes;

import java.util.Deque;
import kalkulacka.Segment;
import kalkulacka.SegmentOperator;

/**
 *
 * @author dominik.dembinny.s
 */
public class SegmentAdd implements SegmentOperator {

    @Override
    public void run(Deque<Double> list) {
        list.push(list.pop() + list.pop());
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public boolean isLeftAssociative() {
        return true;
    }
    
    @Override
    public String getExactRepresentation() {
        return "+";
    }
    
    @Override
    public String toString() {
        return "SegmentAdd";
    }
}
