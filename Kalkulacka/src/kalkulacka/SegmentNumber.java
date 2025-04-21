/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kalkulacka;

import java.util.Deque;
import java.util.Stack;

/**
 *
 * @author dominik.dembinny.s
 */
public class SegmentNumber implements Segment {
    private final double a;

    public SegmentNumber(double a) {
        this.a = a;
    }
    
    @Override
    public void run(Deque<Double> list) {
        list.push(a);
    }

    @Override
    public String toString() {
        return a + "";
    }
}
