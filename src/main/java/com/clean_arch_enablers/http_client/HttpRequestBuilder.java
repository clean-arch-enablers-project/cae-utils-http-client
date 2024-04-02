package com.clean_arch_enablers.http_client;

public interface HttpRequestBuilder extends HttpRequestBuilderForHandlers, HttpRequestBuilderForRetrying{

    HttpRequestBuilder headerOf(String key, String value);
    HttpRequestBuilder headersFactory(HttpRequestHeaderFactory httpRequestHeaderFactory);
    HttpRequestBuilder pathVariableOf(String pathVariablePlaceholder, String pathVariableValue);
    HttpRequestBuilder queryParameterOf(String queryParameterName, String queryParameterValue);
    HttpRequestBuilder proxyAddress(String host, Integer port);
    HttpRequestModel finishBuildingModel();

}
