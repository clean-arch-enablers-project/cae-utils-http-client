package com.cae.http_client.implementations;


import com.cae.http_client.HttpRequestMethod;
import com.cae.http_client.HttpResponse;

public class HttpRequestPutMethod implements HttpRequestMethod {

    @Override
    public HttpResponse execute(AbstractHttpRequestModel httpRequestModel) {
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
