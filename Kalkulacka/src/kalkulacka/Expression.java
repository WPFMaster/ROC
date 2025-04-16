/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulacka;

import java.util.function.*;

/**
 *
 * @author dominik.dembinny.s
 */
public class Expression {
    public final Solver solve;

    public Expression(String input) {
        input = input.replaceAll(" +", "");     // " +" is regex expresion standing for: take as mutch spaces as can
        String[] sides = input.split("=", 3);   // Limited with 3 because IDK
        if (sides.length > 2) {
            System.out.println("Zadaný výraz má příliš mnoho znaků '='!!!");
            solve = null;
            return;
        }
        if (sides.length == 1 || sides[1].trim().length() == 0) {
            //Problem to solve
            solve = new Problem(Parser(sides[0]), sides[0] + "=");
        }
        else {
            //Equation to solve
            solve = new Equation(new String[][] {Parser(sides[0]), Parser(sides[1])}, input);
        }
    }
    
    public static String[] Parser(String input) {
        String suportedOperations = "\\+\\-*/^()";
        return input.split("(?=[" + suportedOperations + "])|(?<=[" + suportedOperations + "])");  //Uses positive lookahead to keep spliting characters
    }
}
