/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package kalkulacka;

import java.util.Deque;
import java.util.Stack;

/**
 *
 * @author dominik.dembinny.s
 */
public interface Segment {
    public void run(Deque<Double> list);

    public String getExactRepresentation();

    @Override
    public String toString();
}
