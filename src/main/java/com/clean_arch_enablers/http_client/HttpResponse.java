package com.clean_arch_enablers.http_client;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.function.Consumer;

public interface HttpResponse{

    /**
     * Retrieve the status code of the response
     * @return a number of some HTTP Status Code: 200, 204, 400, 403...
     */
    Integer getStatusCode();

    /**
     * Gets the request responsible for generating this response
     * @return the HTTP request model object
     */
    HttpRequestModel getHttpRequest();

    /**
     * Gets the response body as the parameterized type
     * @param bodyType the desired type for the body
     * @return the instance of the desired type
     * @param <T> the known type to map the body to
     */
    <T> T getBodyAs(Class<T> bodyType);

    /**
     * Gets the response body as the parameterized type
     * @param typeOfExpectedResponseBody the desired type for the body
     * @return the instance of the desired type
     * @param <T> the known type to map the body to
     */
    <T> T getBodyAs(TypeReference<T> typeOfExpectedResponseBody);

    /**
     *
     * @return whether the response needs handling
     */
    boolean needsHandling();

    /**
     * If the instance needs handling it will execute the Consumer received as parameter
     * @param checkOnResponse the action to be executed in case of needing handling
     */
    void ifNeedsHandling(Consumer<HttpResponse> checkOnResponse);
}
