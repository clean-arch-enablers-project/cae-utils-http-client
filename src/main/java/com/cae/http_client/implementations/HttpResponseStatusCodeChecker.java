package com.cae.http_client.implementations;

import com.cae.http_client.HttpResponse;
import com.cae.http_client.HttpResponseHandler;
import com.cae.http_client.implementations.exceptions.NoResponseHandlersAvailableForExecutionException;
import com.cae.http_client.implementations.exceptions.RetryNeededOnHttpStatusCodeException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpResponseStatusCodeChecker {

    private final AbstractHttpRequestModel httpRequest;
    private HttpResponse httpResponse;

    public static HttpResponseStatusCodeChecker of(AbstractHttpRequestModel httpRequest){
        return new HttpResponseStatusCodeChecker(httpRequest);
    }

    public void checkOutHandlersFor(HttpResponse response) throws RetryNeededOnHttpStatusCodeException {
        this.httpResponse = response;
        var statusCodeReceived = response.getStatusCode();
        var retryCounterForStatusCodeReceived = ofNullable(this.httpRequest.retryCountersByStatusCode.get(statusCodeReceived));
        if (retryCounterForStatusCodeReceived.isPresent() && retryCounterForStatusCodeReceived.get().thereIsRetryAvailable()){
            retryCounterForStatusCodeReceived.get().decreaseRetriesAvailable();
            throw new RetryNeededOnHttpStatusCodeException(response);
        }
        this.executeHandlerIfAnyIsPresentForTheResponseReceived();
    }

    private void executeHandlerIfAnyIsPresentForTheResponseReceived() {
        var statusCodeReceived = this.httpResponse.getStatusCode();
        var handler = ofNullable(this.httpRequest.responseHandlersByStatusCode.get(statusCodeReceived)).orElseGet(this::getGenericHandler);
        handler.handle(this.httpResponse);
    }

    private HttpResponseHandler getGenericHandler() {
        return ofNullable(this.httpRequest.genericResponseHandler).orElseThrow(() -> new NoResponseHandlersAvailableForExecutionException(this.httpResponse));
    }
}
