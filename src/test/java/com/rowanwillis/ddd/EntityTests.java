package com.rowanwillis.ddd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EntityTests {

    static Stream Ids()
    {
        return Stream.of(
            Arguments.of(UUID.randomUUID()),
            Arguments.of(43563458),
            Arguments.of("StringID")
        );
    }

    static Stream DistinctIds()
    {
        return Stream.of(
            Arguments.of(UUID.randomUUID(), UUID.randomUUID()),
            Arguments.of(43563458, 987343),
            Arguments.of("StringID", "DifferentStringID")
        );
    }

    @ParameterizedTest
    @MethodSource("Ids")
    <TIdentity> void givenANonNullId_AnEntityHasTheGivenId(TIdentity id) throws InvalidEntityIdException {
        final var entity = new SimpleEntityStub(id);

        assertEquals(id, entity.getId());
    }

    @Test
    void givenANullId_attemptingToCreateAnEntity_throwsInvalidEntityIdException()
    {
        final var exception = assertThrows(InvalidEntityIdException.class, () -> new SimpleEntityStub(null));
        assertEquals("Entity ID must be a non-default value.", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("Ids")
    <TIdentity> void entitiesWithEqualIds_areEqual(TIdentity id) throws InvalidEntityIdException {
        final var entity1 = new SimpleEntityStub<TIdentity>(id);
        final var entity2 = new SimpleEntityStub<TIdentity>(id);

        assertEquals(entity1, entity2);
    }

    @ParameterizedTest
    @MethodSource("Ids")
    <TIdentity> void entitiesWithEqualIds_haveTheSameHashCode(TIdentity id) throws InvalidEntityIdException {
        final var entity1 = new SimpleEntityStub<TIdentity>(id);
        final var entity2 = new SimpleEntityStub<TIdentity>(id);

        assertEquals(entity1.hashCode(), entity2.hashCode());
    }

    @ParameterizedTest
    @MethodSource("DistinctIds")
    <TIdentity> void entitiesWithDistinctIds_areNotEqual(TIdentity id1, TIdentity id2) throws InvalidEntityIdException {
        final var entity1 = new SimpleEntityStub<TIdentity>(id1);
        final var entity2 = new SimpleEntityStub<TIdentity>(id2);

        assertNotEquals(entity1, entity2);
    }
}
