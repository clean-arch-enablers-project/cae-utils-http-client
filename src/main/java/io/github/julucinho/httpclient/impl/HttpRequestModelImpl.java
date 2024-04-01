package io.github.julucinho.httpclient.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.julucinho.httpclient.HttpRequestMethod;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.http.HttpRequest.BodyPublisher;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpRequestModelImpl extends AbstractHttpRequestModel {

    public static AbstractHttpRequestModel of(String uri, HttpRequestMethod method){
        var httpRequest = new HttpRequestModelImpl();
        httpRequest.uri = uri;
        httpRequest.method = method;
        return httpRequest;
    }

    public static AbstractHttpRequestModel of(String uri, HttpRequestMethod method, BodyPublisher body){
        var httpRequest = new HttpRequestModelImpl();
        httpRequest.uri = uri;
        httpRequest.method = method;
        httpRequest.body = body;
        return httpRequest;
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
