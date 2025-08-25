package com.pipeline;

import java.util.*;

public class TokenizeFilter implements Filter<String, List<String>> {
    @Override
    public List<String> process(String input) {
        List<String> tokens = new ArrayList<>();
        if (input == null || input.isEmpty()) return tokens;

        int i = 0;
        int n = input.length();

        while (i < n) {
            char c = input.charAt(i);

            if (Character.isWhitespace(c)) { i++; continue; }

            if (Character.isDigit(c) || c == '.') {
                int start = i;
                boolean seenDot = (c == '.');
                i++;
                while (i < n) {
                    char d = input.charAt(i);
                    if (Character.isDigit(d)) { i++; continue; }
                    if (d == '.' && !seenDot) { seenDot = true; i++; continue; }
                    break;
                }
                tokens.add(input.substring(start, i));
                continue;
            }

            if (Character.isLetter(c)) {
                int start = i;
                i++;
                while (i < n && (Character.isLetterOrDigit(input.charAt(i)) || input.charAt(i) == '_')) i++;
                tokens.add(input.substring(start, i));
                continue;
            }

            if (c == 'π') { tokens.add("π"); i++; continue; }

            if ("+-*/^(),".indexOf(c) >= 0) {
                tokens.add(String.valueOf(c));
                i++;
                continue;
            }

            throw new IllegalArgumentException("Carácter no reconocido: " + c);
        }

        return tokens;
    }
}
