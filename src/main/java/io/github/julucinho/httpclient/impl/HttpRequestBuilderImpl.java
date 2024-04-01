package io.github.julucinho.httpclient.impl;


import io.github.julucinho.httpclient.*;
import io.github.julucinho.httpclient.commons.RetriersByExceptionTypeFactory;

public class HttpRequestBuilderImpl extends AbstractHttpRequestBuilder {

    public static HttpRequestBuilder of(AbstractHttpRequestModel httpRequest){
        return new HttpRequestBuilderImpl(httpRequest);
    }

    private HttpRequestBuilderImpl(AbstractHttpRequestModel httpRequest) {
        super(httpRequest);
    }

    @Override
    public HttpRequestBuilder andAddHeaderOf(String key, String value) {
        this.httpRequest.headers.put(key, value);
        return this;
    }

    @Override
    public HttpRequestBuilder andAddHeadersFactory(HttpRequestHeaderFactory httpRequestHeaderFactory) {
        httpRequestHeaderFactory.makeHeaders().forEach(this.httpRequest.headers::put);
        return this;
    }

    @Override
    public HttpRequestBuilder andAddPathVariableOf(String pathVariableName, String pathVariableValue) {
        this.httpRequest.pathVariables.add(new HttpRequestPathVariable(pathVariableName, pathVariableValue));
        return this;
    }

    @Override
    public HttpRequestBuilder andAddQueryParameterOf(String queryParameterName, String queryParameterValue) {
        this.httpRequest.queryParameters.add(new HttpRequestQueryParameter(queryParameterName, queryParameterValue));
        return this;
    }

    @Override
    public HttpRequestBuilder andAddProxyAddress(String host, Integer port) {
        this.httpRequest.proxyAddress = ProxyAddressModel.builder().host(host).port(port).build();
        return this;
    }

    @Override
    public HttpRequestModel andFinishBuildingModel() {
        return this.httpRequest;
    }

    @Override
    public HttpRequestBuilder andAddResponseHandlerByHttpStatusCode(Integer statusCode, HttpResponseHandler httpResponseHandler) {
        this.httpRequest.responseHandlersByStatusCode.put(statusCode, httpResponseHandler);
        return this;
    }

    @Override
    public HttpRequestBuilder andAddResponseHandlersByHttpStatusCodeFactory(HttpResponseHandlersByStatusCodeFactory httpResponseHandlersByStatusCodeFactory) {
        httpResponseHandlersByStatusCodeFactory.makeHandlers().forEach(this.httpRequest.responseHandlersByStatusCode::put);
        return this;
    }

    @Override
    public HttpRequestBuilder andAddResponseHandlerForAnyNotSuccessfulResponse(HttpResponseHandler httpResponseHandler) {
        this.httpRequest.genericResponseHandler = httpResponseHandler;
        return this;
    }

    @Override
    public HttpRequestBuilder andAddExceptionHandlerByExceptionType(Class<? extends Exception> exceptionType, ExceptionHandler exceptionHandler) {
        this.httpRequest.exceptionHandlersByExceptionType.put(exceptionType, exceptionHandler);
        return this;
    }

    @Override
    public HttpRequestBuilder andAddExceptionHandlersByExceptionTypeFactory(HttpExceptionHandlersByExceptionTypeFactory httpExceptionHandlersByExceptionTypeFactory) {
        httpExceptionHandlersByExceptionTypeFactory.makeHandlers().forEach(this.httpRequest.exceptionHandlersByExceptionType::put);
        return this;
    }

    @Override
    public HttpRequestBuilder andAddRetrierByHttpStatusCode(Integer statusCode, RetrierModel retrierModel) {
        this.httpRequest.retryCountersByStatusCode.put(statusCode, RetryCounterImpl.of(retrierModel));
        return this;
    }

    @Override
    public HttpRequestBuilder andAddRetriersByHttpStatusCodeFactory(RetriersByStatusCodeFactory retriersByStatusCodeFactory) {
        retriersByStatusCodeFactory.makeRetriers().forEach((statusCode, retrierModel) -> this.httpRequest.retryCountersByStatusCode.put(statusCode, RetryCounterImpl.of(retrierModel)));
        return this;
    }

    @Override
    public HttpRequestBuilder andAddRetrierByExceptionType(Class<? extends Exception> exceptionType, RetrierModel retrierModel) {
        this.httpRequest.retryCountersByExceptionType.put(exceptionType, RetryCounterImpl.of(retrierModel));
        return this;
    }

    @Override
    public HttpRequestBuilder andAddRetriersByExceptionTypeFactory(RetriersByExceptionTypeFactory retriersByExceptionTypeFactory) {
        retriersByExceptionTypeFactory.makeRetriers().forEach((exceptionType, retrierModel) -> this.httpRequest.retryCountersByExceptionType.put(exceptionType, RetryCounterImpl.of(retrierModel)));
        return this;
    }
}
