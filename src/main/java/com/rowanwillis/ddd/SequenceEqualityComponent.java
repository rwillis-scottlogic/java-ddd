package com.rowanwillis.ddd;

public class SequenceEqualityComponent<TElement> {

    private Iterable<TElement> sequence;

    public SequenceEqualityComponent(Iterable<TElement> sequence) {
        if (sequence == null)
            throw new IllegalArgumentException("Sequence must not be null.");

        this.sequence = sequence;
    }

    public Iterable<TElement> getSequence() {
        return sequence;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;

        if (other == this)
            return true;

        if (!(other instanceof SequenceEqualityComponent<?> otherComponent))
            return false;

        return IterableHelper.sequencesAreEqual(sequence, otherComponent.getSequence());
    }
    
    @Override
    public int hashCode()
    {
        var hashcode = 17;

        for (Object element : sequence) {
            hashcode = 31 * hashcode + (element == null ? 0 : element.hashCode());
        }

        return hashcode;
    }
}
