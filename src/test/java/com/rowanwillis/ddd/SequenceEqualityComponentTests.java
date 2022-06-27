package com.rowanwillis.ddd;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class SequenceEqualityComponentTests {

    static Stream<Arguments> sequences()
    {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3, 4)),
            Arguments.of(List.of()),
            Arguments.of(List.of("a", "b", "c", "d", "E", "F", "x", "YYY", "z_")),
            Arguments.of(List.of(new Object(), new Object()))
        );
    }

    static Stream<Arguments> equalSequences()
    {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3, 4), List.of(1, 2, 3, 4)),
            Arguments.of(List.of(), List.of()),
            Arguments.of(List.of("a", "b", "c", "d"), List.of("a", "b", "c", "d"))
        );
    }

    static Stream<Arguments> distinctSequences()
    {
        return Stream.of(
            Arguments.of(List.of(), List.of("Something", "Something else")),
            Arguments.of(List.of(1, 2, 3, 4), List.of(2, 3, 4)),
            Arguments.of(List.of("a", "b", "c"), List.of("A", "B", "C")),
            Arguments.of(List.of(new Object(), "String", 0.123456), List.of(true, Optional.empty(), 8000))
        );
    }

    @ParameterizedTest
    @MethodSource("sequences")
    void aNewSequenceEqualityComponent_hasTheGivenSequenceOfValues(Iterable sequence)
    {
        final var component = new SequenceEqualityComponent(sequence);

        assertIterableEquals(sequence, component.getSequence());
    }

    @Test
    void givenNull_attemptingToCreateASequenceEqualityComponent_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> new SequenceEqualityComponent(null));
    }

    @ParameterizedTest
    @MethodSource("equalSequences")
    void twoSequenceEqualityComponents_withIdenticalSequences_areEqual(Iterable sequence1, Iterable sequence2) {
        final var component1 = new SequenceEqualityComponent(sequence1);
        final var component2 = new SequenceEqualityComponent(sequence2);

        assertEquals(component1, component2);
    }

    @ParameterizedTest
    @MethodSource("equalSequences")
    void twoSequenceEqualityComponents_withIdenticalSequences_haveTheSameHashCode(Iterable sequence1, Iterable sequence2) {
        final var component1 = new SequenceEqualityComponent(sequence1);
        final var component2 = new SequenceEqualityComponent(sequence2);

        assertEquals(component1.hashCode(), component2.hashCode());
    }

    @ParameterizedTest
    @MethodSource("distinctSequences")
    void twoSequenceEqualityComponents_withDifferentSequences_areNotEqual(Iterable sequence1, Iterable sequence2) {
        final var component1 = new SequenceEqualityComponent(sequence1);
        final var component2 = new SequenceEqualityComponent(sequence2);

        assertNotEquals(component1, component2);
    }
}