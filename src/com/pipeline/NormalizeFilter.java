package com.pipeline;

public class NormalizeFilter implements Filter<String, String> {
    @Override
    public String process(String input) {
        if (input == null) return "";
        return input.trim().toLowerCase().replaceAll("\\s+", " ");
    }
}
