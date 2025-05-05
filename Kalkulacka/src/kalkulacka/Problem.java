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
    
    /**
     * 
     * @param seg
     * @param StrRepre
     * @hidden 
     * @deprecated 
     */
    public Problem(List<String> seg, String StrRepre) {
        StringRepresentation = StrRepre;
        Segments = seg;
    }

    public Problem(String StrRepre) {
        StringRepresentation = StrRepre;
        Segments = Parser(StrRepre);
    }
    
    private static List<String> Parser(String input) {
        String suportedOperations = "\\+\\-*/^()[\\]{}<>]|si?n?|co?s?";
        String[] segments = input.split("(?=[" + suportedOperations + ")|(?<=[" + suportedOperations + ")");  //Uses positive lookahead to keep spliting characters
        List<String> segmentsNoEmpty = new ArrayList(segments.length);
        for (String segment : segments) {
            if (segment != "") {
                segmentsNoEmpty.add(segment);
            }
        }
        System.out.println(segmentsNoEmpty);
        return segmentsNoEmpty;
    }
    
    @Override
    public double solve() {
        List<SegmentFunction> arr = Simplify();
        Deque<Double> oup = new LinkedList<>();
        for (SegmentFunction a : arr) {
            a.run(oup);
        }
        return oup.pop();
    }
    
    private List<SegmentFunction> Simplify() {
        List<SegmentFunction> oup = new ArrayList();
        
        Deque<SegmentItem> stack = new LinkedList<>();
        
        int distanceFromNumber = 0xFF;
        
        for (int i = 0; i < Segments.size(); i++) {
            String item = Segments.get(i);
            
            //Don't realy happends
            if (item.length() == 0) {
                continue;
            }
            
            distanceFromNumber++;
            
            SegmentFunction addItem;
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
                    
                    stack.push(new SegmentBracket());
                    
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
                    flush(oup, new SegmentBracket(), stack);
                    distanceFromNumber = 0;
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
    
    private void flush(List<SegmentFunction> oup, Segment e, Deque<? extends SegmentItem> stack) {
        
        if (e instanceof SegmentBracket) {
            while (!stack.isEmpty() && !(stack.peek() instanceof SegmentBracket)) {
                oup.add(((SegmentFunction) stack.pop()));
            }
        } else
        if (e instanceof SegmentEnd) {
            for (SegmentItem segmentItem : stack) {
                if (segmentItem instanceof SegmentFunction) {
                    oup.add((SegmentFunction) segmentItem);
                }
            }
        } else
        if (e instanceof SegmentOperator) {
            //+ - 1; * / 2; (-) sin cos 3; ^ 4; ( ) 5
            while (!stack.isEmpty()) {
                if (stack.peek() instanceof SegmentBracket) {
                    break;
                } else if (stack.peek() instanceof SegmentOperator && e instanceof SegmentOperator) {
                    if (!((SegmentOperator) e).isLeftAssociative()) {
                        if (((SegmentFunction) stack.peek()).getPriority() > ((SegmentOperator) e).getPriority()) {
                            oup.add((SegmentFunction) stack.pop());
                        } else break;
                        continue;
                    }
                    if (((SegmentFunction) stack.peek()).getPriority() >= ((SegmentOperator) e).getPriority()) {
                        oup.add((SegmentFunction) stack.pop());
                    } else break;
                } else
                if (stack.peek() instanceof SegmentFunction) {
                    if (((SegmentFunction) stack.peek()).getPriority() >= ((SegmentOperator) e).getPriority()) {
                        oup.add((SegmentFunction) stack.pop());
                    } else break;
                }
            }
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
