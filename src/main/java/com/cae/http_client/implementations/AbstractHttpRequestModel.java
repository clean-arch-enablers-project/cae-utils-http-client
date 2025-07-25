package com.cae.http_client.implementations;

import com.cae.http_client.ExceptionHandler;
import com.cae.http_client.HttpRequestMethod;
import com.cae.http_client.HttpRequestModel;
import com.cae.http_client.HttpResponseHandler;

import java.net.http.HttpRequest.BodyPublisher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractHttpRequestModel implements HttpRequestModel {

    protected String uri;
    protected final List<HttpRequestPathVariable> pathVariables = new ArrayList<>();
    protected final List<HttpRequestQueryParameter> queryParameters = new ArrayList<>();
    protected final Map<String, String> headers = new HashMap<>();
    protected BodyPublisher body;
    protected HttpRequestMethod method;
    protected ProxyAddressModel proxyAddress;
    protected Boolean bypassSsl = false;
    protected Boolean bypassDomainCheck = false;
    protected HttpResponseHandler genericResponseHandler;
    protected final Map<Integer, HttpResponseHandler> responseHandlersByStatusCode = new HashMap<>();
    protected final Map<Class<? extends Exception>, ExceptionHandler> exceptionHandlersByExceptionType = new HashMap<>();

}
