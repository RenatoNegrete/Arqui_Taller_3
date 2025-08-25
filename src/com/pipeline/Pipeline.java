package com.pipeline;

import java.util.*;

public class Pipeline<I, O> {
    private final List<Filter<?, ?>> filters = new ArrayList<>();

    public <X, Y> Pipeline<I, Y> add(Filter<X, Y> filter) {
        filters.add(filter);
        @SuppressWarnings("unchecked")
        Pipeline<I, Y> casted = (Pipeline<I, Y>) this;
        return casted;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public O run(I input) {
        Object current = input;
        for (Filter f : filters) current = f.process(current);
        return (O) current;
    }
}
