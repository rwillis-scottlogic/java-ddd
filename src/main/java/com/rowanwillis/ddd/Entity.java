package com.rowanwillis.ddd;

public abstract class Entity<TIdentity> {
    private final TIdentity id;

    public Entity(TIdentity id) throws InvalidEntityIdException {
        if (id == null)
            throw new InvalidEntityIdException();

        this.id = id;
    }

    public TIdentity getId() {
        return id;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null)
            return false;

        if (other == this)
            return true;

        if (!(other instanceof Entity entity))
            return false;

        return this.getId() == entity.getId();
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }
}
