/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kalkulacka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author dominik.dembinny.s
 */
public class Problem implements Solver {
    private final String StringRepresentation;
    private final String[] Segments;
    
    public Problem(String[] seg, String StrRepre) {
        StringRepresentation = StrRepre;
        Segments = seg;
    }

    @Override
    public double solve() {
        System.out.println(Arrays.toString(Segments));
        for (int i = 0; i < Segments.length; i++) {
            
        }
        return .1;
    }
    
    private String[] Simplify() {
        List<String> oup = new ArrayList();
        for (int i = 0; i < Segments.length; i++) {
            switch (Segments[i].charAt(0)) {
                case '-':
                case '+':
                    break;
                case '*':
                case '/':
                case '^':
                    break;
                case '(':
                case ')':
                    break;
                case '.':
                    break;
                default:
                    if (Character.isDigit(Segments[i].charAt(0))) {
                        
                    } else {
                        System.out.println("Chyba při zadávání příkladu!!!");
                        return null;
                    }
                    break;
            }
        }
        return oup.toArray(new String[0]);
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
