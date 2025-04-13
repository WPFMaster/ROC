/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kalkulacka;

import java.util.Stack;

/**
 *
 * @author dembi
 */
public class SegmentMulti implements Segment {

    @Override
    public void run(Stack<Double> list) {
        list.push(list.pop() * list.pop());
    }
    
}
