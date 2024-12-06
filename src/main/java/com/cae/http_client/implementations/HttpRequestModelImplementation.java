package com.cae.http_client.implementations;

import com.cae.http_client.HttpRequestMethod;
import com.cae.http_client.HttpResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.http.HttpRequest.BodyPublisher;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpRequestModelImplementation extends AbstractHttpRequestModel {

    public static AbstractHttpRequestModel of(String uri, HttpRequestMethod method){
        var httpRequest = new HttpRequestModelImplementation();
        httpRequest.uri = uri;
        httpRequest.method = method;
        return httpRequest;
    }

    public static AbstractHttpRequestModel of(String uri, HttpRequestMethod method, BodyPublisher body){
        var httpRequest = new HttpRequestModelImplementation();
        httpRequest.uri = uri;
        httpRequest.method = method;
        httpRequest.body = body;
        return httpRequest;
    }

    @Override
    public HttpResponse sendRequest() {
        return HttpRequestExecutionManager.of(this).run();
    }

    @Override
    public <T> T sendRequestReturning(Class<T> typeOfExpectedResponseBody) {
        return HttpRequestExecutionManager.of(this).run().getBodyAs(typeOfExpectedResponseBody);
    }

    @Override
    public <T> T sendRequestReturning(TypeReference<T> typeOfExpectedResponseBody) {
        return HttpRequestExecutionManager.of(this).run().getBodyAs(typeOfExpectedResponseBody);
    }
}
