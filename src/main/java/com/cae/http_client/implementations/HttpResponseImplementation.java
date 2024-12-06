package com.cae.http_client.implementations;

import com.cae.http_client.HttpRequestModel;
import com.cae.http_client.HttpResponse;
import com.cae.http_client.implementations.exceptions.JsonProcessingRuntimeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class HttpResponseImplementation extends AbstractHttpResponse{

    public HttpResponseImplementation(HttpRequestModel httpRequestModel, java.net.http.HttpResponse<String> unwrappedHttpResponse) {
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

    @Override
    public Map<String, List<String>> getHeaders() {
        return Optional.ofNullable(this.unwrappedHttpResponse.headers())
                .map(HttpHeaders::map)
                .orElse(new HashMap<>());
    }

    private boolean isNot2xx() {
        var statusString = this.getStatusCode().toString();
        return (statusString.charAt(0) != '2');
    }
}
