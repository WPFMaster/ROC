package kalkulacka;

/**
 * Box class for creating and differencing Solver instances.
 * 
 * @version %I%, %G%
 * @author dominik.dembinny.s
 * @see Solver
 */
public class Expression {
    /**
     * Access point for solver class.
     */
    public final Solver solve;
    private final boolean isProblem;
    private final boolean isEquation;

    private Expression(String input) {
        input = input.replaceAll(" +", "");     // " +" is regex expresion standing for: take as mutch spaces as can
        String[] sides = input.split("=", 3);   // Limited with 3 because IDK
        if (sides.length > 2) {
            System.out.println("Zadaný výraz má příliš mnoho znaků '='!!!");
            solve = null;
            isProblem = false;
            isEquation = false;
            return;
        }
        if (sides.length == 1 || sides[1].trim().length() == 0) {
            //Problem to solve
            isProblem = true;
            isEquation = false;
            solve = new Problem(sides[0]);
        }
        else {
            //Equation to solve
            isEquation = true;
            isProblem = false;
            solve = new Equation(sides[0], sides[1], input);
        }
    }
    private Expression(Expression from, String input) {
        this(input);
        if (isEquation && from.isEquation) {
            ((Equation)solve).database = ((Equation)from.solve).database;
        }
    }
    private Expression() {
        solve = null;
        isProblem = false;
        isEquation = false;
    }
    
    /**
     * Easier access to solve method. This is the same as calling {@link Solver#solve()}
     * 
     * @see     Solver#solve() 
     * @return  double value, of calculated expression.
     */
    public double solve() {
        return solve.solve();
    }
    
    /**
     * Used instead of constructor to create expression. In it is called {@link #createExpression(kalkulacka.Expression, java.lang.String)} creating from ones instance.
     * The only method used within the class instance. Not a static function.
     * This method is recommended to use, when creating new classes.
     * 
     * @param   input the user input, to by processed.
     * @return  a Expression class instance, for calculating the input.
     */
    public Expression craeteExpression(String input) {
        return createExpression(this, input);
    }
    
    /**
     * Used instead of constructor to create expression. Creates new expression based on data from last.
     * 
     * @param from  the instance to be taken data from.
     * @param input the String representation of user input.
     * @return      a Expression class instance, for calculating the input.
     */
    public static Expression createExpression(Expression from, String input) {
        return new Expression(from, input);
    }
        
    /**
     * Used instead of constructor to create expression. Creates new expression based on data from last.
     * This method is recommended to use, when new chain of expressions begins.
     * 
     * @param input the String representation of user input.
     * @return      a Expression class instance, for calculating the input.
     */
    public static Expression createExpression(String input) {
        return new Expression(input);
    }
        
    /**
     * Initialize new not null class, that can't be used for computing.
     * 
     * @return      a empty Expression.
     */
    public static Expression createExpression() {
        return new Expression();
    }
}
