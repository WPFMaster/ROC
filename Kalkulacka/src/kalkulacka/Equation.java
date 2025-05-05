/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulacka;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dominik.dembinny.s
 */
public class Equation implements Solver {
    private final String StringRepresentation;
    private final List<String>[] Segments;
    public Map<Character, Double> database = new CharMap();

    /**
     * 
     * @param seg
     * @param strRepre 
     * @hidden 
     * @deprecated 
     */
    public Equation(List<String>[] seg, String strRepre) {
        StringRepresentation = strRepre;
        Segments = seg;
    }
    
    /**
     * 
     * @param segL
     * @param segR
     * @param strRepre 
     * @hidden
     * @deprecated 
     */
    public Equation(List<String> segL, List<String> segR, String strRepre) {
        StringRepresentation = strRepre;
        Segments = new List[] {segL, segR};
    }
    
    public Equation(String strL, String strR, String strRepre) {
        StringRepresentation = strRepre;
        Segments = new List[] { Parser(strL), Parser(strR) };
    }

    @Override
    public double solve() {
        List<Double> unknownPart = new LinkedList<>(); //LinkedList is more effective when olny adding elements
        List<Double> knownPart = new LinkedList<>();
        char unknownChar;
        if (Segments[0].size() == 1) {
            if ('A' <= Segments[0].get(0).charAt(0) && Segments[0].get(0).charAt(0) <= 'z') {
                unknownChar = simplification(unknownPart, knownPart, Segments[0].get(0).charAt(0));
            } else 
                unknownChar = simplification(unknownPart, knownPart);
        } else 
            unknownChar = simplification(unknownPart, knownPart);
        double knownSum = 0;
        for (Double aDouble : knownPart) {
            knownSum += aDouble;
        }
        double unknownSum = 0;
        for (Double aDouble : unknownPart) {
            unknownSum += aDouble;
        }
        if (unknownChar == 0 || unknownSum == 0) {
            if (knownSum == 0) {
                return Double.POSITIVE_INFINITY;
            } else return Double.NEGATIVE_INFINITY;
        }
        double unknown = - knownSum / unknownSum;
        database.put(unknownChar, unknown);
        return unknown;
    }
    
    private char simplification(List<Double> unknownPart, List<Double> knownPart, char unknownChar) {
        for (int i = 0; i < 2; i++) {
            int sideSign = i == 0 ? 1 : -1;
            int operSign = 1;
            double value = 1;
            boolean containsUknownPart = false;
            for (int j = 0; j < Segments[i].size(); j++) {
                switch (Segments[i].get(j)) {
                    case "-":
                        if (j != 0) {
                            if (containsUknownPart) {
                                unknownPart.add(value * sideSign * operSign);
                            } else {
                                knownPart.add(value * sideSign * operSign);
                            }
                        }
                        operSign = -1;
                        value = 1;
                        containsUknownPart = false;
                        break;
                    case "+":
                        if (j != 0) {
                            if (containsUknownPart) {
                                unknownPart.add(value * sideSign * operSign);
                            } else {
                                knownPart.add(value * sideSign * operSign);
                            }
                        }
                        operSign = 1;
                        value = 1;
                        containsUknownPart = false;
                        break;
                    default:
                        try { 
                            value *= Double.parseDouble(Segments[i].get(j));
                            System.out.println(Double.parseDouble(Segments[i].get(j)));
                        } catch (NumberFormatException e) {
                            //Is letter
                            if (database.containsKey(Segments[i].get(j).charAt(0))) {
                                value *= database.get(Segments[i].get(j).charAt(0));
                            } else {
                                if (Segments[i].get(j).charAt(0) == unknownChar) {
                                    containsUknownPart = true;
                                } else if (unknownChar != 0) {
                                    System.out.println("Více neznámích!!!");
                                    return 0;
                                } else {
                                    unknownChar = Segments[i].get(j).charAt(0);
                                    containsUknownPart = true;
                                }
                            }
                        }
                }
            }
            if (containsUknownPart) {
                unknownPart.add(value * sideSign * operSign);
            } else {
                knownPart.add(value * sideSign * operSign);
            }
        }
        return unknownChar;
    }
    private char simplification(List<Double> unknownPart, List<Double> knownPart) {
        return simplification(unknownPart, knownPart, (char) 0);
    }
    
    public static List<String> Parser(String input) {
        String suportedOperations = "\\+\\-a-zA-Z";
        String[] segments = input.split("(?=[" + suportedOperations + "])|(?<=[" + suportedOperations + "])");  //Uses positive lookahead to keep spliting characters
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
    public String getExpression() {
        return StringRepresentation;
    }

    @Override
    public String toString() {
        return StringRepresentation;
    }
    
}
