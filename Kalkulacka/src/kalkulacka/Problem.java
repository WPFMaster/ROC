package kalkulacka;

import kalkulacka.SegmentTypes.SegmentNumber;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import kalkulacka.SegmentTypes.*;

/**
 * Solver for mathematics problems.
 *
 * @author dominik.dembinny.s
 * @see Solver
 */
public class Problem implements Solver {
    private final String StringRepresentation;
    private final List<String> Segments;
    private int numberOfElements;
    
    /**
     * 
     * @param seg
     * @param StrRepre
     * @hidden the splitting is handled by this class instead.
     * @deprecated 
     */
    public Problem(List<String> seg, String StrRepre) {
        StringRepresentation = StrRepre;
        Segments = seg;
    }

    /**
     * Constructor for creating problem.
     * 
     * @param StrRepre a trying representation of problem in infix notation.
     */
    public Problem(String StrRepre) {
        StringRepresentation = StrRepre;
        Segments = Spliter(StrRepre);
    }
    
    /**
     * The part of program that splits input into tokens.
     * 
     * @param input input to by split
     * @see String#split(java.lang.String) 
     */
    private static List<String> Spliter(String input) {
        String suportedOperations = "\\+\\-*/^()\\[\\]{}<>]|si?n?|co?s?";
        String[] segments = input.split("(?=[" + suportedOperations + ")|(?<=[" + suportedOperations + ")");  //Uses positive lookahead to keep spliting characters
        List<String> segmentsNoEmpty = new ArrayList(segments.length);
        for (String segment : segments) {
            if (segment != "") {
                segmentsNoEmpty.add(segment);
            }
        }
        return segmentsNoEmpty;
    }
    
    @Override
    public double solve() {
        //Parse the whole problem
        List<SegmentFunction> arr = Simplify();
        
        //Calculate the RPN (Revezed polish notation)
        Deque<Double> oup = new LinkedList<>();
        for (SegmentFunction a : arr) {
            a.run(oup);
        }
        return oup.pop();
    }
    
    /**
     * The parser.
     * <p> Pseudo code for explanation:
     * while there are tokens to be read:
    read a token
    if the token is:
    - a number:
        put it into the output queue
    - a function:
        push it onto the operator stack 
    - an operator o1:
        while (
            there is an operator o2 at the top of the operator stack which is not a left parenthesis, 
            and (o2 has greater precedence than o1 or (o1 and o2 have the same precedence and o1 is left-associative))
        ):
            pop o2 from the operator stack into the output queue
        push o1 onto the operator stack
    - a ",":
        while the operator at the top of the operator stack is not a left parenthesis:
             pop the operator from the operator stack into the output queue
    - a left parenthesis (i.e. "("):
        push it onto the operator stack
    - a right parenthesis (i.e. ")"):
        while the operator at the top of the operator stack is not a left parenthesis:
            {assert the operator stack is not empty}
             If the stack runs out without finding a left parenthesis, then there are mismatched parentheses. 
            pop the operator from the operator stack into the output queue
        {assert there is a left parenthesis at the top of the operator stack}
        pop the left parenthesis from the operator stack and discard it
        if there is a function token at the top of the operator stack, then:
            pop the function from the operator stack into the output queue
     * 
     * @return list of functions to evaluate the problem
     */
    private List<SegmentFunction> Simplify() {
        //Output queue
        List<SegmentFunction> oup = new ArrayList();
        
        //Stack for operators
        Deque<SegmentItem> stack = new LinkedList<>();
        
        int distanceFromNumber = 0xFF;
        
        //Go througth all tokens
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
                    //Need to solve minus after pranthesies and in the beginning of expression.
                    //As its evaluate as negation
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
                    //Case when number is immediatly after bracket
                    if (distanceFromNumber == 1) {
                        addItem = new SegmentMultiply();

                        flush(oup, addItem, stack);
                        stack.push(addItem);
                    }
                    
                    stack.push(new SegmentBracket());
                    break;
                case ")":  
                case "}":
                case "]":
                case ">":
                    flush(oup, new SegmentBracket(), stack);
                    
                    //When braket ends it's same as just number
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
    
    /**
     * Adds functions from list of items to output function list.
     * 
     * @param oup   output list 
     * @param e     the element whitch is adding
     * @param stack the stack of operators
     */
    private void flush(List<SegmentFunction> oup, Segment e, Deque<? extends SegmentItem> stack) {
        
        if (e instanceof SegmentBracket) {
            //We flush untill end braker or end of stack (that shouldn't realy happend)
            while (!stack.isEmpty() && !(stack.peek() instanceof SegmentBracket)) {
                oup.add(((SegmentFunction) stack.pop()));
            }
        } else
        if (e instanceof SegmentEnd) {
            //We flush the whole stack but we need to filter out only functions
            for (SegmentItem segmentItem : stack) {
                if (segmentItem instanceof SegmentFunction) {
                    oup.add((SegmentFunction) segmentItem);
                }
            }
        } else
        if (e instanceof SegmentOperator) {
            //The precedence is:
            //+ - 1; * / 2; (-) sin cos 3; ^ 4; ( ) 5
            while (!stack.isEmpty()) {
                if (stack.peek() instanceof SegmentBracket) {
                    break;
                } else if (stack.peek() instanceof SegmentOperator) {
                    //If the operator is LeftAssociative the element is flush even if the precedents equals
                    //If not the adding element must have bigger precedent
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
                    //For function we don't care about the precedent
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
    
    /**
     * Returns a postfix notation of the problem.
     * 
     * @return string representation of problem in postfix notation (RPN)
     */
    public String getSiplifyNotation() {
        StringBuilder sb = new StringBuilder();
        for (Segment item : Simplify()) {
            sb.append(item.getExactRepresentation());
            sb.append(' ');
        }
        return sb.toString();
    }
}
