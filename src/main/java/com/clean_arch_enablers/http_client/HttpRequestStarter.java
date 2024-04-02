package com.clean_arch_enablers.http_client;

import java.net.http.HttpRequest;

public interface HttpRequestStarter {

    HttpRequestBuilder startGetRequestFor(String url);
    HttpRequestBuilder startPostRequestFor(String url, HttpRequest.BodyPublisher body);
    HttpRequestBuilder startPutRequestFor(String url, HttpRequest.BodyPublisher body);
    HttpRequestBuilder startDeleteRequestFor(String url);

}
