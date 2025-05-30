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
public class SegmentSin implements SegmentFunction {

    @Override
    public void run(Deque<Double> list) {
        list.push(Math.sin(list.pop()));
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public String getExactRepresentation() {
        return "sin";
    }

    @Override
    public String toString() {
        return "SegmentSin";
    }
}
