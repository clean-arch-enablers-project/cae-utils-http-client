package com.clean_arch_enablers.http_client;

import com.clean_arch_enablers.http_client.commons.RetriersByExceptionTypeFactory;

public interface HttpRequestBuilderForRetrying {

    HttpRequestBuilder retrierByHttpStatusCode(Integer statusCode, RetrierModel retrierModel);
    HttpRequestBuilder retriersByHttpStatusCodeFactory(RetriersByStatusCodeFactory retriersByStatusCodeFactory);
    HttpRequestBuilder retriersByExceptionTypeFactory(RetriersByExceptionTypeFactory retriersByExceptionTypeFactory);
    HttpRequestBuilder retrierByExceptionType(Class<? extends Exception> exceptionType, RetrierModel retrierModel);

}




