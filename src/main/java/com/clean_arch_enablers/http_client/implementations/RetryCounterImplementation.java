package com.clean_arch_enablers.http_client.implementations;

import com.clean_arch_enablers.http_client.RetrierModel;
import com.clean_arch_enablers.http_client.RetryCounter;
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
