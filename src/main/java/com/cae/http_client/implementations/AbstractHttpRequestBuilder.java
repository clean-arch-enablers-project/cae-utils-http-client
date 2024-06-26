package com.cae.http_client.implementations;

import com.cae.http_client.HttpRequestBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractHttpRequestBuilder implements HttpRequestBuilder {

    protected final AbstractHttpRequestModel httpRequest;

}
