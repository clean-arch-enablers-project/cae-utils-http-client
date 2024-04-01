package io.github.julucinho.httpclient;

@FunctionalInterface
public interface ExceptionHandler {

    void handle(Exception exception);

}
