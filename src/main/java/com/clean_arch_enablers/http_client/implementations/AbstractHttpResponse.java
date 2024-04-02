package com.clean_arch_enablers.http_client.implementations;

import com.clean_arch_enablers.http_client.HttpRequestModel;
import com.clean_arch_enablers.http_client.HttpResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractHttpResponse implements HttpResponse {

    protected final HttpRequestModel httpRequestModel;
    protected final java.net.http.HttpResponse<String> unwrappedHttpResponse;
}
