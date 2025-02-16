package com.cae.http_client.implementations;

import com.cae.http_client.HttpRequestMethod;
import com.cae.http_client.HttpResponse;

public class HttpRequestPatchMethod implements HttpRequestMethod {
    @Override
    public HttpResponse execute(AbstractHttpRequestModel httpRequestModel) {
        var finalRequest = FinalHttpRequestFactory.makeFinalRequestForPatchMethodFrom(httpRequestModel);
        try {
            var unwrappedResponse = FinalHttpRequestExecutor.of(httpRequestModel).execute(finalRequest);
            return new HttpResponseImplementation(httpRequestModel, unwrappedResponse);
        }
        catch (Exception exception) {
            ExceptionThrownByHttpRequestChecker.of(httpRequestModel).checkOn(exception);
            throw exception;
        }
    }
}
