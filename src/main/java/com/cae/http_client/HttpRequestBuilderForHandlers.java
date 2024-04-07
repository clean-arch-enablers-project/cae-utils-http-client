package com.cae.http_client;

/**
 *  Builder component for attaching handlers to the request model.
 */
public interface HttpRequestBuilderForHandlers {


    /**
     * Attaches a handler by the number which represents the HTTP status code
     * @param statusCode the number of the HTTP status code: 404, 400, 503...
     * @param httpResponseHandler an arrow function which implements the Functional Interface of HttpResponseHandler
     * @return the builder instance for further attachments
     */
    HttpRequestBuilder handlerByHttpStatusCode(Integer statusCode, HttpResponseHandler httpResponseHandler);

    /**
     * Attaches a group of handlers by the numbers which represent HTTP statuses code from within the factory
     * @param httpResponseHandlersByStatusCodeFactory the factory from which the handlers will be made
     * @return the builder instance for further attachments
     */
    HttpRequestBuilder handlersByHttpStatusCodeFactory(HttpResponseHandlersByStatusCodeFactory httpResponseHandlersByStatusCodeFactory);

    /**
     *  Attaches a handler for any unsuccessful HTTP response
     * @param httpResponseHandler an arrow function which implements the Functional Interface of HttpResponseHandler
     * @return the builder instance for further attachments
     */
    HttpRequestBuilder handlerForAnyUnsuccessfulResponse(HttpResponseHandler httpResponseHandler);

    /**
     * Attaches a group of handlers by types of exceptions from within the factory
     * @param httpExceptionHandlersByExceptionTypeFactory the factory from which the handlers will be made
     * @return the builder instance for further attachments
     */
    HttpRequestBuilder handlersByExceptionTypeFactory(HttpExceptionHandlersByExceptionTypeFactory httpExceptionHandlersByExceptionTypeFactory);

    /**
     * Attaches a handler by type of exception
     * @param exceptionType the type of the exception which will trigger the execution of the following handler
     * @param exceptionHandler an arrow function which implements the Functional Interface of ExceptionHandler
     * @return the builder instance for further attachments
     */
    HttpRequestBuilder handlerByExceptionType(Class<? extends Exception> exceptionType, ExceptionHandler exceptionHandler);

}
