package com.cae.http_client.implementations;

import com.cae.http_client.RetrierModel;
import com.cae.http_client.RetryCounter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RetryCounterImplementation implements RetryCounter {

    private final RetrierModel retrierModel;

    public static RetryCounter of(RetrierModel retrierModel){
        return new RetryCounterImplementation(retrierModel);
    }

    @Override
    public boolean thereIsRetryAvailable() {
        return this.retrierModel.getRetriesRemaining() > 0;
    }

    @Override
    public void decreaseRetriesAvailable() {
        this.retrierModel.setRetriesRemaining(this.retrierModel.getRetriesRemaining()-1);
    }
}
