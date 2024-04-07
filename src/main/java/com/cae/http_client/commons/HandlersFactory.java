package com.cae.http_client.commons;

public interface HandlersFactory<T> {

    /**
     * Factory method for creating handlers
     * @return the handlers
     */
    T makeHandlers();

}
