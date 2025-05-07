/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulacka;

import java.util.Scanner;

/**
 * Just testing class.
 *
 * @hidden 
 * @author dominik.dembinny.s
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Expression a = Expression.createExpression();
        Scanner sc = new Scanner(System.in);
        while (true) {
            a = a.craeteExpression(sc.nextLine());
            System.out.println(a.solve.solve());
            if (a.solve instanceof Problem)
                System.out.println(((Problem)a.solve).getSiplifyNotation());
        }
    }
}
