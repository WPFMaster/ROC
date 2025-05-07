package kalkulacka;

/**
 * A part of problem for one element.
 *
 * @author dominik.dembinny.s
 */
public interface Segment {

    /**
     * Give the same string representation in infix notation.
     * 
     * @return the exact string representation
     */
    public String getExactRepresentation();

    @Override
    public String toString();
}
