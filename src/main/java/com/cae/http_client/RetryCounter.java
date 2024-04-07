package com.cae.http_client;

public interface RetryCounter {
    boolean thereIsRetryAvailable();
    void decreaseRetriesAvailable();
}
