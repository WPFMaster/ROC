/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulacka;

/**
 * https://kisk.phil.muni.cz/kpi/temata/tvorba-a-sdelovani-novych-informaci/tvorba-posteru
 *
 * @author dominik.dembinny.s
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Expression ex = new Expression("10-5=");
        System.out.println(ex.solve.getExpression());
    }
    
}
