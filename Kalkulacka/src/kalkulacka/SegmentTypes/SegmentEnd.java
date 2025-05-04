/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kalkulacka.SegmentTypes;

import java.util.Deque;
import kalkulacka.Segment;

/**
 *
 * @author dominik.dembinny.s
 */
public class SegmentEnd implements Segment {

    @Override
    public void run(Deque<Double> list) {
        throw new UnsupportedOperationException("Can't be ran.");
    }

    @Override
    public int getPriority() {
        return 6;
    }

    @Override
    public String getExactRepresentation() {
        return "<EoF>";
    }
}
