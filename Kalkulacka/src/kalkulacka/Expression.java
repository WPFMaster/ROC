/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulacka;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dominik.dembinny.s
 */
public class Expression {
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
            solve = new Problem(Parser(sides[0], "\\+\\-*/^()"), sides[0] + "=");
        }
        else {
            //Equation to solve
            isEquation = true;
            isProblem = false;
            solve = new Equation(Parser(sides[0], "\\+\\-a-zA-Z"), Parser(sides[1], "\\+\\-a-zA-Z"), input);
            //Uncomment****solve = new Equation(new List<String>[] {Parser(sides[0]), Parser(sides[1])}, input);
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
    
    public double solve() {
        return solve.solve();
    }
    
    public static List<String> Parser(String input, String suportedOperations) {
        //String suportedOperations = "\\+\\-*/^()";
        String[] segments = input.split("(?=[" + suportedOperations + "])|(?<=[" + suportedOperations + "])");  //Uses positive lookahead to keep spliting characters
        List<String> segmentsNoEmpty = new ArrayList(segments.length);
        for (String segment : segments) {
            if (segment != "") {
                segmentsNoEmpty.add(segment);
            }
        }
        System.out.println(segmentsNoEmpty);
        return segmentsNoEmpty;
    }
    public static List<String> Parser(String input) {
        return Parser(input, "\\+\\-*/^()");
    }
    
    public Expression craeteExpression(String input) {
        return createExpression(this, input);
    }
    
    public static Expression createExpression(Expression from, String input) {
        return new Expression(from, input);
    }
    public static Expression createExpression(String input) {
        return new Expression(input);
    }
    public static Expression createExpression() {
        return new Expression();
    }
}
