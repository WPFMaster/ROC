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
        
        Deque<Segment> stack = new LinkedList<>();
        
        int distanceFromNumber = 0xFF;
        
        for (int i = 0; i < Segments.size(); i++) {
            String item = Segments.get(i);
            
            //Don't realy happends
            if (item.length() == 0) {
                continue;
            }
            
            distanceFromNumber++;
            
            Segment addItem;
            switch (item) {
                case "-":
                    //Need to solve minus after pranthesies and in the beginning of expression
                    if (i == 0 || "(".equals(Segments.get(i - 1)) ||
                            "*".equals(Segments.get(i - 1)) ||
                            "/".equals(Segments.get(i - 1)) ||
                            "^".equals(Segments.get(i - 1))) {
                        addItem = new SegmentNegation();
                    } else {
                        addItem = new SegmentSubtract();
                        
                        flush(oup, addItem, stack);
                    }
                    stack.push(addItem);
                    break;
                case "+":
                    addItem = new SegmentAdd();
                    
                    flush(oup, addItem, stack);
                    stack.push(addItem);
                    break;
                case "*":
                    addItem = new SegmentMultiply();
                    
                    flush(oup, addItem, stack);
                    stack.push(addItem);
                    break;
                case "/":
                    addItem = new SegmentDivide();
                    
                    flush(oup, addItem, stack);
                    stack.push(addItem);
                    break;
                case "^":
                    addItem = new SegmentPower();
                    
                    flush(oup, addItem, stack);
                    stack.push(addItem);
                    break;
                case "(": //Need to solve when there is number before braclet
                case "{":
                case "[":
                case "<":
                    if (distanceFromNumber == 1) {
                        addItem = new SegmentMultiply();

                        flush(oup, addItem, stack);
                        stack.push(addItem);
                    }
                    
                    addItem = new SegmentBracket();
                    
                    stack.push(addItem);
                    
//                    oup.addAll(highPriority);
//                    highPriority.clear();
//                    oup.addAll(midPriority);
//                    midPriority.clear();
//                    oup.addAll(lowPriority);
//                    lowPriority.clear();
//                    
//                    oup.addAll(function);
//                    function.clear();
                    
                    break;
                case ")":  
                case "}":
                case "]":
                case ">":
                    addItem = new SegmentBracket();
                    
                    flush(oup, addItem, stack);
                    break;
                case "sin":
                case "s":
                    addItem = new SegmentSin();
                    
                    flush(oup, addItem, stack);
                    stack.push(addItem);
                    break;
                    
                case "cos":
                case "c":
                    addItem = new SegmentSin();
                    
                    flush(oup, addItem, stack);
                    stack.push(addItem);
                    break;
                    
                default:
                    if (Character.isDigit(item.charAt(0)) || item.charAt(0) == '.' || item.charAt(0) == ',') {
                        oup.add(new SegmentNumber(parseRest(item)));
                        numberOfElements++;
                        distanceFromNumber = 0;
                    } else {
                        System.out.println("Chyba při zadávání příkladu!!! 03");
                        return null;
                    }
            }
        }
                    
        flush(oup, new SegmentEnd(), stack);
        return oup;
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
    
    private void flush(List<Segment> oup, Segment e, Deque<Segment> stack) {
        int priority = e.getPriority();
        
        if (e instanceof SegmentBracket) {
            while (!stack.isEmpty() && !(stack.peek() instanceof SegmentBracket)) {
                oup.add(stack.pop());
            }
        }
        
        //+ - 1; * / 2; (-) sin cos 3; ^ 4; ( ) 5
        while (!stack.isEmpty()) {
            if (stack.peek().getPriority() >= priority) {
                oup.add(stack.pop());
            } else break;
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
