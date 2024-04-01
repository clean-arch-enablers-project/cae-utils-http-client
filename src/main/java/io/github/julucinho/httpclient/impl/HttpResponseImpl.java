package io.github.julucinho.httpclient.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.julucinho.httpclient.HttpRequestModel;
import io.github.julucinho.httpclient.HttpResponse;
import io.github.julucinho.httpclient.impl.exceptions.JsonProcessingRuntimeException;

import java.util.function.Consumer;

public class HttpResponseImpl extends AbstractHttpResponse{

    public HttpResponseImpl(HttpRequestModel httpRequestModel, java.net.http.HttpResponse<String> unwrappedHttpResponse) {
        super(httpRequestModel, unwrappedHttpResponse);
    }

    @Override
    public Integer getStatusCode() {
        return this.unwrappedHttpResponse.statusCode();
    }

    @Override
    public HttpRequestModel getHttpRequest() {
        return this.httpRequestModel;
    }

    @Override
    public <T> T getBodyAs(Class<T> bodyType) {
        try{
            return ObjectMapperFactory.createNewObjectMapper().readValue(this.unwrappedHttpResponse.body(), bodyType);
        } catch (Exception exception){
            throw new JsonProcessingRuntimeException(exception.getMessage());
        }
    }

    @Override
    public <T> T getBodyAs(TypeReference<T> bodyType) {
        try{
            return ObjectMapperFactory.createNewObjectMapper().readValue(this.unwrappedHttpResponse.body(), bodyType);
        } catch (JsonProcessingException exception){
            throw new JsonProcessingRuntimeException(exception.getMessage());
        }
    }

    @Override
    public boolean needsHandling() {
        return this.isNot2xx();
    }

    @Override
    public void ifNeedsHandling(Consumer<HttpResponse> checkOnResponse) {
        if (this.needsHandling())
            checkOnResponse.accept(this);
    }

    private boolean isNot2xx() {
        var statusString = this.getStatusCode().toString();
        return (statusString.charAt(0) != '2');
    }
}
