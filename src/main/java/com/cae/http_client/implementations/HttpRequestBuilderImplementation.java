package com.cae.http_client.implementations;


import com.cae.http_client.*;

public class HttpRequestBuilderImplementation extends AbstractHttpRequestBuilder {

    public static HttpRequestBuilder of(AbstractHttpRequestModel httpRequest){
        return new HttpRequestBuilderImplementation(httpRequest);
    }

    private HttpRequestBuilderImplementation(AbstractHttpRequestModel httpRequest) {
        super(httpRequest);
    }

    @Override
    public HttpRequestBuilder headerOf(String key, String value) {
        this.httpRequest.headers.put(key, value);
        return this;
    }

    @Override
    public HttpRequestBuilder headersFactory(HttpRequestHeaderFactory httpRequestHeaderFactory) {
        httpRequestHeaderFactory.makeHeaders().forEach(this.httpRequest.headers::put);
        return this;
    }

    @Override
    public HttpRequestBuilder pathVariableOf(String pathVariablePlaceholder, String pathVariableValue) {
        this.httpRequest.pathVariables.add(new HttpRequestPathVariable(pathVariablePlaceholder, pathVariableValue));
        return this;
    }

    @Override
    public HttpRequestBuilder queryParameterOf(String queryParameterName, String queryParameterValue) {
        this.httpRequest.queryParameters.add(new HttpRequestQueryParameter(queryParameterName, queryParameterValue));
        return this;
    }

    @Override
    public HttpRequestBuilder proxyAddress(String host, Integer port) {
        this.httpRequest.proxyAddress = ProxyAddressModel.builder().host(host).port(port).build();
        return this;
    }

    @Override
    public HttpRequestModel buildRequestModel() {
        return this.httpRequest;
    }

    @Override
    public HttpRequestBuilder handlerByHttpStatusCode(Integer statusCode, HttpResponseHandler httpResponseHandler) {
        this.httpRequest.responseHandlersByStatusCode.put(statusCode, httpResponseHandler);
        return this;
    }

    @Override
    public HttpRequestBuilder handlersByHttpStatusCodeFactory(HttpResponseHandlersByStatusCodeFactory httpResponseHandlersByStatusCodeFactory) {
        httpResponseHandlersByStatusCodeFactory.makeHandlers().forEach(this.httpRequest.responseHandlersByStatusCode::put);
        return this;
    }

    @Override
    public HttpRequestBuilder handlerForAnyUnsuccessfulResponse(HttpResponseHandler httpResponseHandler) {
        this.httpRequest.genericResponseHandler = httpResponseHandler;
        return this;
    }

    @Override
    public HttpRequestBuilder handlerByExceptionType(Class<? extends Exception> exceptionType, ExceptionHandler exceptionHandler) {
        this.httpRequest.exceptionHandlersByExceptionType.put(exceptionType, exceptionHandler);
        return this;
    }

    @Override
    public HttpRequestBuilder handlersByExceptionTypeFactory(HttpExceptionHandlersByExceptionTypeFactory httpExceptionHandlersByExceptionTypeFactory) {
        httpExceptionHandlersByExceptionTypeFactory.makeHandlers().forEach(this.httpRequest.exceptionHandlersByExceptionType::put);
        return this;
    }

}
