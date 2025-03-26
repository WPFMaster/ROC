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
public class Expression {
    public static String[] Parser(String input) {
        input = input.replaceAll(" +", "");
        String[] sides = input.split("=", 3);
        if (sides.length > 2) {
            
        }
        if (sides.length == 1 || sides[1].trim().length() == 0) {
            
        }
        return sides;
    }
}
