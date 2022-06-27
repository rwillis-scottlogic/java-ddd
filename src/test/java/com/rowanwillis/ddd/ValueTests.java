package com.rowanwillis.ddd;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

class ValueTests {

    static Stream<Arguments> simpleValues()
    {
        return Stream.of(
            Arguments.of(1),
            Arguments.of("Test"),
            Arguments.of(new Object()),
            Arguments.of(true)
        );
    }

    static Stream<Arguments> compoundValues()
    {
        return Stream.of(
            Arguments.of(489753985, "Hello"),
            Arguments.of(true, false),
            Arguments.of(new Object(), List.of(0.01, 0.05, 0.83))
        );
    }

    public static Stream<Arguments> differentSimpleValues()
    {
        return Stream.of(
            Arguments.of(1, 420),
            Arguments.of("Test 1", "Something different"),
            Arguments.of(new Object(), new Object())
        );
    }

    static Stream<Arguments> differentCompoundValues()
    {
        return Stream.of(
            Arguments.of(489753985, "Hello", 983574, "Goodbye"),
            Arguments.of(true, false, false, false),
            Arguments.of(new Object(), List.of(0.01, 0.05, 0.83), new Object(), List.of(10, 5, 0, -1))
        );
    }

    @ParameterizedTest
    @MethodSource("simpleValues")
    <T> void twoSimpleValues_withIdenticalEqualityComponents_areEqual(T value)
    {
        final var simpleValue1 = new SimpleValueStub<T>(value);
        final var simpleValue2 = new SimpleValueStub<T>(value);

        assertEquals(simpleValue1, simpleValue2);
    }

    @ParameterizedTest
    @MethodSource("simpleValues")
    <T> void twoSimpleValues_withIdenticalEqualityComponents_haveTheSameHashCode(T value)
    {
        final var simpleValue1 = new SimpleValueStub<T>(value);
        final var simpleValue2 = new SimpleValueStub<T>(value);

        assertEquals(simpleValue1.hashCode(), simpleValue2.hashCode());
    }

    @ParameterizedTest
    @MethodSource("differentSimpleValues")
    <T> void twoSimpleValues_withDifferentEqualityComponents_areNotEqual(T value1, T value2)
    {
        final var simpleValue1 = new SimpleValueStub<T>(value1);
        final var simpleValue2 = new SimpleValueStub<T>(value2);

        assertNotEquals(simpleValue1, simpleValue2);
    }

    @ParameterizedTest
    @MethodSource("compoundValues")
    <T1, T2> void twoCompoundValues_withIdenticalEqualityComponents_areEqual(T1 value1, T2 value2)
    {
        final var compoundValue1 = new CompoundValueStub<T1, T2>(value1, value2);
        final var compoundValue2 = new CompoundValueStub<T1, T2>(value1, value2);

        assertEquals(compoundValue1, compoundValue2);
    }

    @ParameterizedTest
    @MethodSource("compoundValues")
    <T1, T2> void twoCompoundValues_withIdenticalEqualityComponents_haveTheSameHashCode(T1 value1, T2 value2)
    {
        final var compoundValue1 = new CompoundValueStub<T1, T2>(value1, value2);
        final var compoundValue2 = new CompoundValueStub<T1, T2>(value1, value2);

        assertEquals(compoundValue1.hashCode(), compoundValue2.hashCode());
    }

    @ParameterizedTest
    @MethodSource("differentCompoundValues")
    <T1, T2> void twoCompoundValues_withDifferentEqualityComponents_areNotEqual(
        T1 value1_1, T2 value1_2, T1 value2_1, T2 value2_2)
    {
        final var compoundValue1 = new CompoundValueStub<T1, T2>(value1_1, value1_2);
        final var compoundValue2 = new CompoundValueStub<T1, T2>(value2_1, value2_2);

        assertNotEquals(compoundValue1, compoundValue2);
    }
}