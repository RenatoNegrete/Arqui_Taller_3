package com.pipeline;

import java.util.*;

public class TokenizeFilter implements Filter<String, List<String>> {
    @Override
    public List<String> process(String input) {
        List<String> tokens = new ArrayList<>();
        StringBuilder number = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                number.append(c);
            } else {
                if (number.length() > 0) {
                    tokens.add(number.toString());
                    number.setLength(0);
                }
                if (!Character.isWhitespace(c)) {
                    tokens.add(String.valueOf(c));
                }
            }
        }
        if (number.length() > 0) tokens.add(number.toString());
        return tokens;
    }
}
