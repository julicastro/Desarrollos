package com.danielblanco.algoritmosestructuras.arraysstringshashtables._01_is_unique;

import java.util.HashMap;
import java.util.Map;

/*
 * Dado un método que recibe una String, comprobar si todos los caracteres son únicos o no.
 *
 * isUnique("abcde") => true;
 * isUnique("abcded") => false;
 */
public class IsUnique {

    public static void main(String[] args) {
        System.out.println(isUnique("asdfafg"));
    }

    public static boolean isUnique(String s) {
        Map<Integer, Character> map = new HashMap<>();
        boolean isUnique = true;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsValue(s.charAt(i)))
                return false;
            map.put(i, s.charAt(i));
        }
        return isUnique;
    }


}
