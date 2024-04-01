package io.github.julucinho.httpclient.impl;


import io.github.julucinho.httpclient.HttpRequestMethod;
import io.github.julucinho.httpclient.HttpResponse;
import io.github.julucinho.httpclient.impl.exceptions.RetryNeededOnExceptionThrownException;

public class HttpRequestPostMethod implements HttpRequestMethod {

    @Override
    public HttpResponse execute(AbstractHttpRequestModel httpRequestModel) throws RetryNeededOnExceptionThrownException {
        var finalRequest = FinalHttpRequestFactory.makeFinalRequestForPostMethodFrom(httpRequestModel);
        try {
            var unwrappedResponse = FinalHttpRequestExecutor.of(httpRequestModel).execute(finalRequest);
            return new HttpResponseImpl(httpRequestModel, unwrappedResponse);
        }
        catch (Exception e) {
            ExceptionThrownByHttpRequestChecker.of(httpRequestModel).checkOn(e);
            throw e;
        }
    }
}
