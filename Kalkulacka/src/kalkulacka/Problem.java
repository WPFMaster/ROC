/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kalkulacka;

import kalkulacka.SegmentTypes.SegmentNumber;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import kalkulacka.SegmentTypes.*;

/**
 *
 * @author dominik.dembinny.s
 */
public class Problem implements Solver {
    private final String StringRepresentation;
    private final List<String> Segments;
    private int numberOfElements;
    
    public Problem(List<String> seg, String StrRepre) {
        StringRepresentation = StrRepre;
        Segments = seg;
    }

    @Override
    public double solve() {
        List<Segment> arr = Simplify();
        Deque<Double> oup = new LinkedList<>();
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
            System.out.println("Příklad je příliš vnořen!!! 01");
            return false;
        }
        //if (Segments[i.get()].charAt(0) == '-') oup.add(new SegmentNumber(0));
        
        List<Segment> function = new ArrayList();
        
        List<Segment> lowPriority = new ArrayList();    //+ -
        List<Segment> midPriority = new ArrayList();    //* /
        List<Segment> highPriority = new ArrayList();   //^
        
        //Every operation adds to it's own priority level and pushes other higher priority operations
        
        int distanceFromNumber = 0xFF;
        
        for (; i.get() < Segments.size(); i.getAndIncrement()) {
            String item = Segments.get(i.get());
            if (item.length() == 0) {
                continue;
            }
            distanceFromNumber++;
            switch (item) {
                case "-":
                    oup.addAll(highPriority);
                    highPriority.clear();
                    oup.addAll(midPriority);
                    midPriority.clear();
                    oup.addAll(lowPriority);
                    lowPriority.clear();
                    
                    oup.addAll(function);
                    function.clear();
                    
                    //Need to solve minus after pranthesies and in the beginning of expression
                    if (i.get() == 0 || "(".equals(Segments.get(i.get() - 1)) || "*".equals(Segments.get(i.get() - 1)) || "/".equals(Segments.get(i.get() - 1))) {
                        function.add(new SegmentNegation());
                    } else lowPriority.add(new SegmentSubtract());
                    
                    
//                    if (Segments[i.get()].length() > 1) {
//                        lowPriority.add((list) -> list.push(list.pop() + list.pop()));
//                        //After operator is number located
//                        oup.add(new SegmentNumber(parseRest(Segments[i.get()])));
//                        numberOfElements++;
//                    } else lowPriority.add((list) -> list.push(- list.pop() + list.pop()));
                    break;
                case "+":
                    oup.addAll(highPriority);
                    highPriority.clear();
                    oup.addAll(midPriority);
                    midPriority.clear();
                    oup.addAll(lowPriority);
                    lowPriority.clear();
                    
                    oup.addAll(function);
                    function.clear();
                    
                    lowPriority.add(new SegmentAdd());
                    break;
                case "*":
                    oup.addAll(highPriority);
                    highPriority.clear();
                    oup.addAll(midPriority);
                    midPriority.clear();
                    
                    oup.addAll(function);
                    function.clear();
                    
                    midPriority.add(new SegmentMultiply());
                    break;
                case "/":
                    oup.addAll(highPriority);
                    highPriority.clear();
                    oup.addAll(midPriority);
                    midPriority.clear();
                    
                    oup.addAll(function);
                    function.clear();
                    
                    midPriority.add(new SegmentDivide());
                    break;
                case "^":
                    oup.addAll(highPriority);
                    highPriority.clear();
                    
                    highPriority.add(new SegmentPower());
                    break;
                case "(": //Need to solve when there is number before braclet
                case "{":
                case "[":
                case "<":
                    if (distanceFromNumber == 1) {
                        midPriority.add(new SegmentMultiply());
                    }
                    i.incrementAndGet();
                    recursiveSimplification(oup, i, n + 1);
                    
                    //When recursiveSimplification() returns the braclet is already closed, so operators can be flushed
                    oup.addAll(highPriority);
                    highPriority.clear();
                    oup.addAll(midPriority);
                    midPriority.clear();
                    oup.addAll(lowPriority);
                    lowPriority.clear();
                    
                    oup.addAll(function);
                    function.clear();
                    
                    break;
                case ")":  
                case "}":
                case "]":
                case ">":                  
                    oup.addAll(highPriority);
                    oup.addAll(midPriority);
                    oup.addAll(lowPriority);
                    
                    return true;
                case "sin":
                case "s":
                    oup.addAll(highPriority);
                    highPriority.clear();
                    oup.addAll(midPriority);
                    midPriority.clear();
                    oup.addAll(lowPriority);
                    lowPriority.clear();
                    
                    oup.addAll(function);
                    function.clear();
                    
                    function.add(new SegmentSin());
                    break;
                    
                case "cos":
                case "c":
                    oup.addAll(highPriority);
                    highPriority.clear();
                    oup.addAll(midPriority);
                    midPriority.clear();
                    oup.addAll(lowPriority);
                    lowPriority.clear();
                    
                    oup.addAll(function);
                    function.clear();
                    
                    function.add(new SegmentCos());
                    break;
                    
                default:
                    if (Character.isDigit(item.charAt(0)) || item.charAt(0) == '.' || item.charAt(0) == ',') {
                        oup.add(new SegmentNumber(parseRest(item)));
                        numberOfElements++;
                        distanceFromNumber = 0;
                        
                        oup.addAll(function);
                        function.clear();
                    } else {
                        System.out.println("Chyba při zadávání příkladu!!! 03");
                        return false;
                    }
            }
        }
        
        oup.addAll(highPriority);
        highPriority.clear();
        oup.addAll(midPriority);
        midPriority.clear();
        oup.addAll(lowPriority);
        lowPriority.clear();
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
    
    public String getSiplifyNotation() {
        StringBuilder sb = new StringBuilder();
        for (Segment item : Simplify()) {
            sb.append(item.getExactRepresentation());
            sb.append(' ');
        }
        return sb.toString();
    }
}
