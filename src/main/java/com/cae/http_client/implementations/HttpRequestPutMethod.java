package com.cae.http_client.implementations;


import com.cae.http_client.HttpRequestMethod;
import com.cae.http_client.HttpResponse;
import com.cae.http_client.implementations.exceptions.RetryNeededOnExceptionThrownException;

public class HttpRequestPutMethod implements HttpRequestMethod {

    @Override
    public HttpResponse execute(AbstractHttpRequestModel httpRequestModel) throws RetryNeededOnExceptionThrownException {
        var finalRequest = FinalHttpRequestFactory.makeFinalRequestForPutMethodFrom(httpRequestModel);
        try {
            var unwrappedResponse = FinalHttpRequestExecutor.of(httpRequestModel).execute(finalRequest);
            return new HttpResponseImplementation(httpRequestModel, unwrappedResponse);
        }
        catch (Exception e) {
            ExceptionThrownByHttpRequestChecker.of(httpRequestModel).checkOn(e);
            throw e;
        }
    }
}
