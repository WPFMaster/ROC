package kalkulacka;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Char based implementation of the {@link Map} interface. As key can by only any englisch lowecase alpfabet character.
 * 
 * @author dominik.dembinny.s
 * @see Map
 */
public class CharMap implements Map<Character, Double> {
    private double[] charArrValue = new double['z' - 'a'];
    private boolean[] charArrHasBeenAsigned = new boolean['z' - 'a'];
    
    /**
     * Returns the number of key-value mappings in this map.  The max number of elements is 26.
     *
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        int i = 0;
        for (int j = 0; j < 26; j++) {
            if(charArrHasBeenAsigned[j]) i++;
        }
        return i;
    }

    /**
     * Returns true if this map contains no key-value mappings.
     *
     * @return true if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        for (int i = 0; i < 26; i++) {
            if (charArrHasBeenAsigned[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if this map contains a mapping for the specified
     * key.  More formally, returns true if and only if
     * this map contains a mapping for a key k such that
     * (key==null ? k==null : key.equals(k)).  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified
     *         key
     */
    @Override
    public boolean containsKey(Object key) {
        if (!(key instanceof Character)) {
            return false;
        }
        char a = Character.toLowerCase((Character)key);
        if (a > 'z' || a < 'a') {
            return false;
        }
        return charArrHasBeenAsigned[a - 'a'];
    }

    /**
     * Returns true if this map maps one or more keys to the
     * specified value.  More formally, returns true if and only if
     * this map contains at least one mapping to a value v such that
     * (value==null ? v==null : value.equals(v)).  This operation
     * will probably require time linear in the map size for most
     * implementations of the Map interface.
     *
     * @param value value whose presence in this map is to be tested
     * @return true if this map maps one or more keys to the
     *         specified value
     */
    @Override
    public boolean containsValue(Object value) {
        if (!(value instanceof Number)) {
            return false;
        }
        double a = (double)value;
        for (int i = 0; i < 26; i++) {
            if (charArrValue[i] == a) {
                if (charArrHasBeenAsigned[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * <p>If this map permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the map
     * contains no mapping for the key; it's also possible that the map
     * explicitly maps the key to {@code null}.  The {@link #containsKey
     * containsKey} operation may be used to distinguish these two cases.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     */
    @Override
    public Double get(Object key) {
        if (!(key instanceof Character)) {
            return null;
        }
        char a = Character.toLowerCase((Character)key);
        if (a > 'z' || a < 'a') {
            return null;
        }
        if (!charArrHasBeenAsigned[a - 'a']) {
            return null;
        }
        return charArrValue[a - 'a'];
    }

    /**
     * Associates the specified value with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.  (A map
     * m is said to contain a mapping for a key k if and only
     * if {@link #containsKey(Object) m.containsKey(k)} would return
     * true.)
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with key, or
     *         null if there was no mapping for key.
     *         (A null return can also indicate that the map
     *         previously associated null with key,
     *         if the implementation supports null values.)
     */
    @Override
    public Double put(Character key, Double value) {
        char a = Character.toLowerCase(key);
        if (a > 'z' || a < 'a') {
            return null;
        }
        charArrHasBeenAsigned[a - 'a'] = true;
        double v = charArrValue[a - 'a'];
        charArrValue[a - 'a'] = value;
        return v;
    }

    /**
     * Removes the mapping for a key from this map if it is present
     * (optional operation).   More formally, if this map contains a mapping
     * from key k to value v such that
     * <code>(key==null ?  k==null : key.equals(k))</code>, that mapping
     * is removed.  (The map can contain at most one such mapping.)
     *
     * <p>Returns the value to which this map previously associated the key,
     * or null if the map contained no mapping for the key.
     *
     * <p>If this map permits null values, then a return value of
     * null does not <i>necessarily</i> indicate that the map
     * contained no mapping for the key; it's also possible that the map
     * explicitly mapped the key to null.
     *
     * <p>The map will not contain a mapping for the specified key once the
     * call returns.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or
     *         null if there was no mapping for key.
     */
    @Override
    public Double remove(Object key) {
        if (!(key instanceof Character)) {
            return null;
        }
        char a = Character.toLowerCase((Character)key);
        if (a > 'z' || a < 'a') {
            return null;
        }
        charArrHasBeenAsigned[a - 'a'] = false;
        return charArrValue[a - 'a'];
    }

    /**
     * Copies all of the mappings from the specified map to this map
     * (optional operation).  The effect of this call is equivalent to that
     * of calling {@link #put(Object,Object) put(k, v)} on this map once
     * for each mapping from key k to value v in the
     * specified map.  The behavior of this operation is undefined if the
     * specified map is modified while the operation is in progress.
     *
     * @param m mappings to be stored in this map
     */
    @Override
    public void putAll(Map<? extends Character, ? extends Double> m) {
        for (Entry<? extends Character, ? extends Double> a : m.entrySet()) {
            put(a.getKey(), a.getValue());
        }
    }

    /**
     * Removes all of the mappings from this map (optional operation).
     * The map will be empty after this call returns.
     */
    @Override
    public void clear() {
        charArrHasBeenAsigned = new boolean[26];
    }

    /**
     * Returns a {@link Set} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own remove operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * Iterator.remove, Set.remove,
     * removeAll, retainAll, and clear
     * operations.  It does not support the add or addAll
     * operations.
     *
     * @return a set view of the keys contained in this map
     */
    @Override
    public Set<Character> keySet() {
        Set<Character> oup = new HashSet(26);
        for (int i = 0; i < 26; i++) {
            if (charArrHasBeenAsigned[i]) {
                oup.add((char)(i + 'a'));
            }
        }
        return oup;
    }

    /**
     * Returns a {@link Collection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  If the map is
     * modified while an iteration over the collection is in progress
     * (except through the iterator's own remove operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the map, via the Iterator.remove,
     * Collection.remove, removeAll,
     * retainAll and clear operations.  It does not
     * support the add or addAll operations.
     *
     * @return a collection view of the values contained in this map
     */
    @Override
    public Collection<Double> values() {
        Collection<Double> oup = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            if (charArrHasBeenAsigned[i]) {
                oup.add(charArrValue[i]);
            }
        }
        return oup;
    }

    /**
     * Returns a {@link Set} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own remove operation, or through the
     * setValue operation on a map entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the map, via the Iterator.remove,
     * Set.remove, removeAll, retainAll and
     * clear operations.  It does not support the
     * add or addAll operations.
     *
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<Entry<Character, Double>> entrySet() {
       Set<Entry<Character, Double>> oup = new HashSet(26);
        for (int i = 0; i < 26; i++) {
            if (charArrHasBeenAsigned[i]) {
                oup.add(new AbstractMap.SimpleEntry<Character, Double>((char)(i + 'a'), charArrValue[i]));
            }
        }
        return oup;
    }
    
}
