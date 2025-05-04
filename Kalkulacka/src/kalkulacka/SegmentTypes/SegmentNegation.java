/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kalkulacka.SegmentTypes;

import java.util.Deque;
import kalkulacka.Segment;
import kalkulacka.SegmentFunction;

/**
 *
 * @author dominik.dembinny.s
 */
public class SegmentNegation implements SegmentFunction {

    @Override
    public void run(Deque<Double> list) {
        list.add(- list.pop());
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public String getExactRepresentation() {
        return "-";
    }

    @Override
    public String toString() {
        return "SegmentNegation";
    }
}
