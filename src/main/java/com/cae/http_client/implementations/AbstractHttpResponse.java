package com.cae.http_client.implementations;

import com.cae.http_client.HttpRequestModel;
import com.cae.http_client.HttpResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractHttpResponse implements HttpResponse {

    protected final HttpRequestModel httpRequestModel;
    protected final java.net.http.HttpResponse<String> unwrappedHttpResponse;
}
