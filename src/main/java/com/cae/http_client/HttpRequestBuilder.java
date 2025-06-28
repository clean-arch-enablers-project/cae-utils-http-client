package com.cae.http_client;

public interface HttpRequestBuilder extends HttpRequestBuilderForHandlers{

    HttpRequestBuilder headerOf(String key, String value);
    HttpRequestBuilder headersFactory(HttpRequestHeaderFactory httpRequestHeaderFactory);
    HttpRequestBuilder pathVariableOf(String pathVariablePlaceholder, String pathVariableValue);
    HttpRequestBuilder queryParameterOf(String queryParameterName, String queryParameterValue);
    HttpRequestBuilder proxyAddress(String host, Integer port);
    HttpRequestBuilder bypassSsl();
    HttpRequestBuilder bypassDomainCheck();
    HttpRequestModel buildRequestModel();

}
