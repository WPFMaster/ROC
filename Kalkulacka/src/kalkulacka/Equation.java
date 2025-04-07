/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulacka;

/**
 *
 * @author dominik.dembinny.s
 */
public class Equation implements Solver {
    private final String StringRepresentation;
    //Seghmets[0] = Right Side of Equation
    //Segments[1] = Left side of Equation
    private final String[][] Segments;

    public Equation(String[][] seg, String StrRepre) {
        StringRepresentation = StrRepre;
        Segments = seg;
    }

    @Override
    public double solve() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
