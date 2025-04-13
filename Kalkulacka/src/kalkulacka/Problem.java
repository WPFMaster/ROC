/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kalkulacka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author dominik.dembinny.s
 */
public class Problem implements Solver {
    private final String StringRepresentation;
    private final String[] Segments;
    private int numberOfElements;
    
    public Problem(String[] seg, String StrRepre) {
        StringRepresentation = StrRepre;
        Segments = seg;
    }

    @Override
    public double solve() {
        List<Segment> arr = Simplify();
        Stack<Double> oup = new Stack();
        for (Segment a : arr) {
            a.run(oup);
        }
        return oup.pop();
    }
    
    private List<Segment> Simplify() {
        List<Segment> oup = new ArrayList();
        recursiveSimplification(oup, new AtomicInteger(), 0);
        
        return oup;
    }
    
    private boolean recursiveSimplification(List<Segment> oup, AtomicInteger i, int n) {
        //AtomicInteger used to referece int value  and make it faster and thread-safe :)
        if (n >= 10) {
            System.out.println("Rovnice je příliš vnořená!!! 01");
            return false;
        }
        if (Segments[i.get()].charAt(0) == '-') oup.add(new SegmentNumber(0));
        
        List<Segment> lowPriority = new ArrayList();    //+ -
        List<Segment> midPriority = new ArrayList();    //* /
        List<Segment> highPriority = new ArrayList();   //^
        
        //Every operation adds to it's own priority level and pushes other higher priority operations
        
        for (; i.get() < Segments.length; i.getAndIncrement()) {
            switch (Segments[i.get()].charAt(0)) {
                case '-':
                    oup.addAll(highPriority);
                    oup.addAll(midPriority);
                    oup.addAll(lowPriority);
                    
                    if (Segments[i.get()].length() > 1) {
                        lowPriority.add(new SegmentAdd());
                    } else lowPriority.add((list) -> list.push(- list.pop() + list.pop()));
                    break;
                case '+':
                    oup.addAll(highPriority);
                    oup.addAll(midPriority);
                    oup.addAll(lowPriority);
                    
                    lowPriority.add(new SegmentAdd());
                    break;
                case '*':
                    oup.addAll(highPriority);
                    oup.addAll(midPriority);
                    
                    midPriority.add(new SegmentMulti());
                    break;
                case '/':
                    oup.addAll(highPriority);
                    oup.addAll(midPriority);
                    
                    midPriority.add((list) -> list.push(1 / list.pop() * list.pop()));
                    break;
                case '^':
                    oup.addAll(highPriority);
                    
                    highPriority.add((list) -> {
                        double e = list.pop();
                        list.push(Math.pow(list.pop(), e));
                            });
                    break;
                case '(':
                    i.incrementAndGet();
                    recursiveSimplification(oup, i, n + 1);
                    
                    break;
                case ')':
                    if (Segments[i.get()].length() > 1) { //Immediately after end-braclet is number or other inparseble character
                        System.out.println("Chyba při zadávání příkladu!!! 02");
                        return false;
                    }
                    
                    oup.addAll(highPriority);
                    oup.addAll(midPriority);
                    oup.addAll(lowPriority);
                    
                    return true;
                case '.'://   - length is grader than 1 and can by handled by parseRest
                case ','://   - The same applies
                    break;
                    
                default:
                    if (Character.isDigit(Segments[i.get()].charAt(0)) && Segments[i.get()].length() == 1) {
                        oup.add(new SegmentNumber(parseRest(Segments[i.get()])));
                        numberOfElements++;
                    } else if (Character.isDigit(Segments[i.get()].charAt(0))) {
                        System.out.println("Chyba při zadávání příkladu!!! 03");
                        return false;
                    }
            }
            
            
            if (Segments[i.get()].length() > 1) {
                //After operator is number located
                oup.add(new SegmentNumber(parseRest(Segments[i.get()])));
                numberOfElements++;
            }
        }
        return true;
    }
    
    private double parseRest(String inp) {
        if (inp.length()  == 0) {
            System.out.println("Zadané číslo nesplňuje standartní formát!!! 04");
            return 0;
        }
        inp = inp.replace(',', '.'); //To not throw error when comma used
        try {
            return Double.parseDouble(inp);
        } catch (Exception e) {
            System.out.println("Zadané číslo nesplňuje standartní formát!!! 05");
            return 0;
        }
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
