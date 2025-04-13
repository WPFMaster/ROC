/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kalkulacka;

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
    public void run(Stack<Double> list) {
        list.add(a);
    }
}
