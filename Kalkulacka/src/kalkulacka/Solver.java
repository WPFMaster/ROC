package kalkulacka;

/**
 * The main interface for solving expressions.
 *
 * @author dominik.dembinny.s
 * @see Expression
 * @see Problem
 * @see Equation
 */
public interface Solver {
    
    /**
     * solve the Expression.
     * 
     * @return double value fo evaluated expression
     */
    public double solve();
    
    /**
     * Returns full string representatin of expresion. It's the same as {@link #toString()}.
     * 
     * @return the string representation of user input
     */
    public String getExpression();
        
    @Override
    public String toString();
    
}
