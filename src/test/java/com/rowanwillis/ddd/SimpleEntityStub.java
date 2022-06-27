package com.rowanwillis.ddd;

class SimpleEntityStub<TIdentity> extends Entity<TIdentity> {

    public SimpleEntityStub(TIdentity id) throws InvalidEntityIdException {
        super(id);
    }
}
