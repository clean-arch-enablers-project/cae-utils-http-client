package com.cae.http_client.implementations;


import com.cae.http_client.HttpRequestBuilder;
import com.cae.http_client.HttpRequestStarter;

import java.net.http.HttpRequest;

public class HttpRequestStarterImplementation implements HttpRequestStarter {

    @Override
    public HttpRequestBuilder startGetRequestFor(String url) {
        return HttpRequestBuilderImplementation.of(HttpRequestModelImplementation.of(url, new HttpRequestGetMethod()));
    }

    @Override
    public HttpRequestBuilder startPostRequestFor(String url, HttpRequest.BodyPublisher body) {
        return HttpRequestBuilderImplementation.of(HttpRequestModelImplementation.of(url, new HttpRequestPostMethod(), body));
    }

    @Override
    public HttpRequestBuilder startPutRequestFor(String url, HttpRequest.BodyPublisher body) {
        return HttpRequestBuilderImplementation.of(HttpRequestModelImplementation.of(url, new HttpRequestPutMethod(), body));
    }

    @Override
    public HttpRequestBuilder startPatchRequestFor(String url, HttpRequest.BodyPublisher body) {
        return HttpRequestBuilderImplementation.of(HttpRequestModelImplementation.of(url, new HttpRequestPatchMethod(), body));
    }

    @Override
    public HttpRequestBuilder startDeleteRequestFor(String url) {
        return HttpRequestBuilderImplementation.of(HttpRequestModelImplementation.of(url, new HttpRequestDeleteMethod()));
    }
}
