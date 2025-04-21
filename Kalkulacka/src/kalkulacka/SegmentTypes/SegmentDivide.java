/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kalkulacka.SegmentTypes;

import java.util.Deque;
import kalkulacka.Segment;

/**
 *
 * @author Admin
 */
public class SegmentDivide implements Segment {

    @Override
    public void run(Deque<Double> list) {
        double e = list.pop();
        list.push(list.pop() / e);
    }

    @Override
    public String getExactRepresentation() {
        return "/";
    }

    @Override
    public String toString() {
        return "SegmentDivide";
    }
}
