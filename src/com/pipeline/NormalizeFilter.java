package com.pipeline;

public class NormalizeFilter implements Filter<String, String> {
    @Override
    public String process(String input) {
        return input.trim().toLowerCase().replaceAll("\\s+", " ");
    }
}
