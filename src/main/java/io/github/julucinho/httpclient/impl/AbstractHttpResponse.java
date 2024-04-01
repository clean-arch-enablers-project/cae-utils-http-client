package io.github.julucinho.httpclient.impl;

import io.github.julucinho.httpclient.HttpRequestModel;
import io.github.julucinho.httpclient.HttpResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractHttpResponse implements HttpResponse {

    protected final HttpRequestModel httpRequestModel;
    protected final java.net.http.HttpResponse<String> unwrappedHttpResponse;
}
