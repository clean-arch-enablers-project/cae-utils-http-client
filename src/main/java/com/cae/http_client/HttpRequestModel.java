package com.cae.http_client;

import com.fasterxml.jackson.core.type.TypeReference;

public interface HttpRequestModel {

    HttpResponse sendRequest();

    <T> T sendRequestReturning(Class<T> typeOfExpectedResponseBody);

    <T> T sendRequestReturning(TypeReference<T> typeReference);
}
