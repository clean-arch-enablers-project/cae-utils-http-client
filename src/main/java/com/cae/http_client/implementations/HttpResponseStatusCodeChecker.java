package com.cae.http_client.implementations;

import com.cae.http_client.HttpResponse;
import com.cae.http_client.HttpResponseHandler;
import com.cae.http_client.implementations.exceptions.NoResponseHandlersAvailableForExecutionException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpResponseStatusCodeChecker {

    private final AbstractHttpRequestModel httpRequest;

    public static HttpResponseStatusCodeChecker of(AbstractHttpRequestModel httpRequest){
        return new HttpResponseStatusCodeChecker(httpRequest);
    }

    public void checkOutHandlersFor(HttpResponse response) {
        var statusCodeReceived = response.getStatusCode();
        var handler = ofNullable(this.httpRequest.responseHandlersByStatusCode.get(statusCodeReceived))
                .orElseGet(() -> this.getGenericHandler(response));
        handler.handle(response);
    }

    private HttpResponseHandler getGenericHandler(HttpResponse response) {
        return ofNullable(this.httpRequest.genericResponseHandler).orElseThrow(() -> new NoResponseHandlersAvailableForExecutionException(response));
    }
}
