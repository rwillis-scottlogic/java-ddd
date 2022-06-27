package com.rowanwillis.ddd;

import java.util.Collection;
import java.util.Iterator;

public abstract class Value {

    protected abstract Iterable<?> getEqualityComponents();

    @Override
    public boolean equals(Object other)
    {
        if (other == null)
            return false;

        if (getClass() != other.getClass())
            return false;

        return IterableHelper.sequencesAreEqual(getEqualityComponents(), ((Value) other).getEqualityComponents());
    }

    @Override
    public int hashCode()
    {
        var hashcode = 17;

        for (Object element : getEqualityComponents())
            hashcode = 31 * hashcode + (element == null ? 0 : element.hashCode());

        return hashcode;
    }
}
