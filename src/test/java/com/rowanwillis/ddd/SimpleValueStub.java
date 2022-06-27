package com.rowanwillis.ddd;

import java.util.List;

class SimpleValueStub<TValue> extends Value
{
    private TValue value;

    public SimpleValueStub(TValue value)
    {
        this.value = value;
    }

    @Override
    protected Iterable<?> getEqualityComponents()
    {
        return List.of(value);
    }
}