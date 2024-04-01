package io.github.julucinho.httpclient.impl;

import io.github.julucinho.httpclient.HttpRequestBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractHttpRequestBuilder implements HttpRequestBuilder {

    protected final AbstractHttpRequestModel httpRequest;

}
