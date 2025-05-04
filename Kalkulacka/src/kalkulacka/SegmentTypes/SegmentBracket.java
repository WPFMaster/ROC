/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kalkulacka.SegmentTypes;

import java.util.Deque;
import kalkulacka.Segment;
import kalkulacka.SegmentItem;

/**
 *
 * @author dominik.dembinny.s
 */
public class SegmentBracket implements SegmentItem {

    public int getPriority() {
        return 5;
    }

    @Override
    public String getExactRepresentation() {
        return "(";
    }
}
