package com.clean_arch_enablers.http_client.implementations;

import com.clean_arch_enablers.http_client.HttpRequestBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractHttpRequestBuilder implements HttpRequestBuilder {

    protected final AbstractHttpRequestModel httpRequest;

}
