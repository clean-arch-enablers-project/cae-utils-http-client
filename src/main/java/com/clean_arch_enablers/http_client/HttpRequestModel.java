package com.clean_arch_enablers.http_client;

import com.fasterxml.jackson.core.type.TypeReference;

public interface HttpRequestModel {

    <T> T sendRequestReturning(Class<T> typeOfExpectedResponseBody);

    <T> T sendRequestReturning(TypeReference<T> typeReference);
}
