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
public interface SegmentFunction extends Segment, SegmentItem {
    
    public void run(Deque<Double> list);
    
    public int getPriority();
}
