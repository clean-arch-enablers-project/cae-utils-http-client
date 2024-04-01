package io.github.julucinho.httpclient;

import io.github.julucinho.httpclient.commons.RetriersByExceptionTypeFactory;

public interface HttpRequestBuilderForRetrying {

    HttpRequestBuilder andAddRetrierByHttpStatusCode(Integer statusCode, RetrierModel retrierModel);
    HttpRequestBuilder andAddRetriersByHttpStatusCodeFactory(RetriersByStatusCodeFactory retriersByStatusCodeFactory);
    HttpRequestBuilder andAddRetriersByExceptionTypeFactory(RetriersByExceptionTypeFactory retriersByExceptionTypeFactory);
    HttpRequestBuilder andAddRetrierByExceptionType(Class<? extends Exception> exceptionType, RetrierModel retrierModel);

}




