package io.github.julucinho.httpclient;

public interface HttpRequestBuilder extends HttpRequestBuilderForHandlers, HttpRequestBuilderForRetrying{

    HttpRequestBuilder andAddHeaderOf(String key, String value);
    HttpRequestBuilder andAddHeadersFactory(HttpRequestHeaderFactory httpRequestHeaderFactory);
    HttpRequestBuilder andAddPathVariableOf(String pathVariableName, String pathVariableValue);
    HttpRequestBuilder andAddQueryParameterOf(String queryParameterName, String queryParameterValue);
    HttpRequestBuilder andAddProxyAddress(String host, Integer port);
    HttpRequestModel andFinishBuildingModel();

}
