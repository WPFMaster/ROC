/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kalkulacka.SegmentTypes;

import java.util.Deque;
import java.util.Stack;
import kalkulacka.Segment;
import kalkulacka.SegmentFunction;

/**
 *
 * @author dominik.dembinny.s
 */
public class SegmentNumber implements SegmentFunction {
    private final double a;

    public SegmentNumber(double a) {
        this.a = a;
    }
    
    @Override
    public void run(Deque<Double> list) {
        list.push(a);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getExactRepresentation() {
        return String.valueOf(a);
    }
    
    @Override
    public String toString() {
        return "SegmentNumber{" + String.valueOf(a) + '}';
    }
}
