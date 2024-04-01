package io.github.julucinho.httpclient;

import java.net.http.HttpRequest;

public interface HttpRequestStarter {

    HttpRequestBuilder startGetRequestModelFor(String url);
    HttpRequestBuilder startPostRequestModelFor(String url, HttpRequest.BodyPublisher body);
    HttpRequestBuilder startPutRequestModelFor(String url, HttpRequest.BodyPublisher body);
    HttpRequestBuilder startDeleteRequestModelFor(String url);

}
