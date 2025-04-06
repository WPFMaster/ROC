/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package kalkulacka;

/**
 *
 * @author dominik.dembinny.s
 */
public interface Solver {
    //Solve Expression
    public double solve();
    //Returns String representation of Expression
    //Same as toString() method
    public String getExpression();

    @Override
    public String toString();
    
}
