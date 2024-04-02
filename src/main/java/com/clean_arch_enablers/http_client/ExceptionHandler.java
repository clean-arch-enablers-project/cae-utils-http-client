package com.clean_arch_enablers.http_client;

@FunctionalInterface
public interface ExceptionHandler {

    /**
     * Method which will be called when handling an exception
     * @param exception the exception to handle
     */
    void handle(Exception exception);

}
