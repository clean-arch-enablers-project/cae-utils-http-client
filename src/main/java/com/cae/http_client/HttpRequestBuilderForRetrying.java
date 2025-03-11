package com.cae.http_client;

import com.cae.http_client.commons.RetriersByExceptionTypeFactory;

interface HttpRequestBuilderForRetrying {

    HttpRequestBuilder retrierByHttpStatusCode(Integer statusCode, RetrierModel retrierModel);
    HttpRequestBuilder retriersByHttpStatusCodeFactory(RetriersByStatusCodeFactory retriersByStatusCodeFactory);
    HttpRequestBuilder retriersByExceptionTypeFactory(RetriersByExceptionTypeFactory retriersByExceptionTypeFactory);
    HttpRequestBuilder retrierByExceptionType(Class<? extends Exception> exceptionType, RetrierModel retrierModel);

}




