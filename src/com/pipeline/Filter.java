package com.pipeline;

public interface Filter<I, O> {
    O process(I input);
}