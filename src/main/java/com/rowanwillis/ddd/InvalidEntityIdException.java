package com.rowanwillis.ddd;

public class InvalidEntityIdException extends Exception {
    @Override
    public String getMessage()
    {
        return "Entity ID must be a non-default value.";
    }
}
