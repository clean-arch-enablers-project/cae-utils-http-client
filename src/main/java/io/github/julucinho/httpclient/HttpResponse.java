package io.github.julucinho.httpclient;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.function.Consumer;

public interface HttpResponse{

    Integer getStatusCode();
    HttpRequestModel getHttpRequest();
    <T> T getBodyAs(Class<T> bodyType);
    <T> T getBodyAs(TypeReference<T> typeOfExpectedResponseBody);
    boolean needsHandling();
    void ifNeedsHandling(Consumer<HttpResponse> checkOnResponse);
}
