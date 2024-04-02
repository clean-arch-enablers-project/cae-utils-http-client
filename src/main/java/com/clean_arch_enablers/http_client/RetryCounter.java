package com.clean_arch_enablers.http_client;

public interface RetryCounter {
    boolean thereIsRetryAvailable();
    void decreaseRetriesAvailable();
}
