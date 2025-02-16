package com.cae.http_client;

interface RetryCounter {
    boolean thereIsRetryAvailable();
    void decreaseRetriesAvailable();
}
