package com.clean_arch_enablers.http_client.implementations;

import com.clean_arch_enablers.http_client.implementations.exceptions.RetryNeededOnExceptionThrownException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import static java.util.Optional.ofNullable;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionThrownByHttpRequestChecker {

    private final AbstractHttpRequestModel httpRequestModel;

    public static ExceptionThrownByHttpRequestChecker of(AbstractHttpRequestModel httpRequestModel) {
        return new ExceptionThrownByHttpRequestChecker(httpRequestModel);
    }

    public void checkOn(Exception e) {
        var retryCounterByExceptionType = ofNullable(this.httpRequestModel.retryCountersByExceptionType.get(e.getClass()));
        if (retryCounterByExceptionType.isPresent() && retryCounterByExceptionType.get().thereIsRetryAvailable()) {
            retryCounterByExceptionType.get().decreaseRetriesAvailable();
            throw new RetryNeededOnExceptionThrownException(e.getClass());
        }
        var handlerByThisException = ofNullable(this.httpRequestModel.exceptionHandlersByExceptionType.get(e.getClass()));
        handlerByThisException.ifPresent(handler -> handler.handle(e));
    }
}
