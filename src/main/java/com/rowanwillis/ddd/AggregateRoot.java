package com.rowanwillis.ddd;

import java.util.Queue;

public abstract class AggregateRoot<TIdentity> extends Entity<TIdentity> {

    private Queue<DomainEvent> events;

    public AggregateRoot(TIdentity id) throws InvalidEntityIdException
    {
        super(id);
    }

    public Iterable<DomainEvent> getEvents()
    {
        return events;
    }

    public void publishEvent(DomainEvent event)
    {
        events.add(event);
    }
}
