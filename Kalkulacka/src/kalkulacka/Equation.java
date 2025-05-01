/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulacka;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dominik.dembinny.s
 */
public class Equation implements Solver {
    private final String StringRepresentation;
    private final List<String>[] Segments;
    public Map<Character, Double> database = new CharMap();

    public Equation(List<String>[] seg, String StrRepre) {
        StringRepresentation = StrRepre;
        Segments = seg;
    }
    public Equation(List<String> segL, List<String> segR, String StrRepre) {
        StringRepresentation = StrRepre;
        Segments = new List[] {segL, segR};
    }

    @Override
    public double solve() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void simplification() {
        List<Double> unknownPart = new LinkedList<>(); //More effective when olny adding elements
        List<Double> knownPart = new LinkedList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < Segments[i].size(); j++) {
                switch () {
                    case val:
                        
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }
    }

    @Override
    public String getExpression() {
        return StringRepresentation;
    }

    @Override
    public String toString() {
        return StringRepresentation;
    }
    
}
