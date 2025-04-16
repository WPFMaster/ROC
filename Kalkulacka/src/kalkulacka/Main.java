/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulacka;

import java.util.ArrayList;
import java.util.List;

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
        Expression ex = new Expression("(-1/2 + 3/8 + 1/4-3/2) / 2");
        System.out.println(ex.solve.getExpression());
        System.out.println(ex.solve.solve());
        List<Integer> list = new ArrayList();
        list.add(null);
        list.add(null);
        list.add(null);
        System.out.println(list);
        
    }
    public static void ahoj(Integer a) {
        a++;
    }
    
}
