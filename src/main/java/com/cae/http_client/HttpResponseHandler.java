package com.cae.http_client;

@FunctionalInterface
public interface HttpResponseHandler {

    /**
     * Method called when handling an HTTP Response
     * @param httpResponse the HTTP response to be handled
     */
    void handle(HttpResponse httpResponse);

}
