package com.rowanwillis.ddd;

import java.util.Collection;
import java.util.Iterator;

class IterableHelper {
    static boolean sequencesAreEqual(Iterable<?> sequence1, Iterable<?> sequence2)
    {
        if (sequence1 == null)
            throw new IllegalArgumentException("sequence1 must not be null.");

        if (sequence2 == null)
            throw new IllegalArgumentException("sequence2 must not be null.");

        if (sequence1 instanceof Collection<?> collection1
                && sequence2 instanceof Collection<?> collection2
                && collection1.size() != collection2.size())
            return false;

        final Iterator<?> iterator1 = sequence1.iterator();
        final Iterator<?> iterator2 = sequence2.iterator();

        while (iterator1.hasNext() || iterator2.hasNext())
        {
            if (iterator1.hasNext() != iterator2.hasNext())
            {
                return false;
            }

            if (iterator1.next() != iterator2.next())
            {
                return false;
            }
        }

        return true;
    }
}
