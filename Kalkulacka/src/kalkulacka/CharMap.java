/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kalkulacka;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author dominik.dembinny.s
 */
public class CharMap implements Map<Character, Double> {
    private double[] charArrValue = new double['z' - 'a'];
    private boolean[] charArrHasBeenAsigned = new boolean['z' - 'a'];
    
    @Override
    public int size() {
        return 26;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < size(); i++) {
            if (charArrHasBeenAsigned[i]) {
                return false;
            }
        }
        return true;
    }

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

    @Override
    public boolean containsValue(Object value) {
        if (!(value instanceof Number)) {
            return false;
        }
        double a = (double)value;
        for (int i = 0; i < size(); i++) {
            if (charArrValue[i] == a) {
                if (charArrHasBeenAsigned[i]) {
                    return true;
                }
            }
        }
        return false;
    }

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

    @Override
    public Double put(Character key, Double value) {
        char a = Character.toLowerCase(key);
        if (a > 'z' || a < 'a') {
            return null;
        }
        charArrHasBeenAsigned[a - 'a'] = true;
        return charArrValue[a - 'a'] = value;
    }

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

    @Override
    public void putAll(Map<? extends Character, ? extends Double> m) {
        for (Entry<? extends Character, ? extends Double> a : m.entrySet()) {
            put(a.getKey(), a.getValue());
        }
    }

    @Override
    public void clear() {
        charArrHasBeenAsigned = new boolean['z' - 'a'];
    }

    @Override
    public Set<Character> keySet() {
        Set<Character> oup = new HashSet(size());
        for (int i = 0; i < size(); i++) {
            if (charArrHasBeenAsigned[i]) {
                oup.add((char)(i + 'a'));
            }
        }
        return oup;
    }

    @Override
    public Collection<Double> values() {
        Collection<Double> oup = new ArrayList<>(size());
        for (int i = 0; i < size(); i++) {
            if (charArrHasBeenAsigned[i]) {
                oup.add(charArrValue[i]);
            }
        }
        return oup;
    }

    @Override
    public Set<Entry<Character, Double>> entrySet() {
       Set<Entry<Character, Double>> oup = new HashSet(size());
        for (int i = 0; i < size(); i++) {
            if (charArrHasBeenAsigned[i]) {
                oup.add(new AbstractMap.SimpleEntry<Character, Double>((char)(i + 'a'), charArrValue[i]));
            }
        }
        return oup;
    }
    
}
