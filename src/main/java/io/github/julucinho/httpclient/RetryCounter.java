package io.github.julucinho.httpclient;

public interface RetryCounter {
    boolean thereIsRetryAvailable();
    void decreaseRetriesAvailable();
}
