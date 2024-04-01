package io.github.julucinho.httpclient.impl;


import io.github.julucinho.httpclient.HttpRequestBuilder;
import io.github.julucinho.httpclient.HttpRequestStarter;

import java.net.http.HttpRequest;

public class HttpRequestStarterImpl implements HttpRequestStarter {

    @Override
    public HttpRequestBuilder startGetRequestModelFor(String url) {
        return HttpRequestBuilderImpl.of(HttpRequestModelImpl.of(url, new HttpRequestGetMethod()));
    }

    @Override
    public HttpRequestBuilder startPostRequestModelFor(String url, HttpRequest.BodyPublisher body) {
        return HttpRequestBuilderImpl.of(HttpRequestModelImpl.of(url, new HttpRequestPostMethod(), body));
    }

    @Override
    public HttpRequestBuilder startPutRequestModelFor(String url, HttpRequest.BodyPublisher body) {
        return HttpRequestBuilderImpl.of(HttpRequestModelImpl.of(url, new HttpRequestPutMethod(), body));
    }

    @Override
    public HttpRequestBuilder startDeleteRequestModelFor(String url) {
        return HttpRequestBuilderImpl.of(HttpRequestModelImpl.of(url, new HttpRequestDeleteMethod()));
    }
}
