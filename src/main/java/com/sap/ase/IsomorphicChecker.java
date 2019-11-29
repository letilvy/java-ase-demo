package com.sap.ase;

public class IsomorphicChecker {
    public boolean isIsomorphic(String s, String s1) {
        if (s.length() == s1.length()) {
            for (int i = 0; i < s.length()-1; i++) {
                if (nextSameCharIndex(s, i) != nextSameCharIndex(s1, i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private int nextSameCharIndex(String s, int start) {
        for (int i = start+1; i < s.length(); i++) {
            if (s.charAt(start) == s.charAt(i)) {
                return i;
            }
        }
        return start;
    }
}
