package com.rowanwillis.ddd;

import java.util.List;

class CompoundValueStub<TValue1, TValue2> extends Value {

    private TValue1 value1;
    private TValue2 value2;

    public CompoundValueStub(TValue1 value1, TValue2 value2)
    {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    protected Iterable<?> getEqualityComponents()
    {
        return List.of(value1, value2);
    }
}