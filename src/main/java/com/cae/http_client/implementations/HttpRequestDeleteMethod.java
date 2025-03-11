package com.cae.http_client.implementations;


import com.cae.http_client.HttpRequestMethod;
import com.cae.http_client.HttpResponse;

public class HttpRequestDeleteMethod implements HttpRequestMethod {

    @Override
    public HttpResponse execute(AbstractHttpRequestModel httpRequestModel) {
        var finalRequest = FinalHttpRequestFactory.makeFinalRequestForDeleteMethodFrom(httpRequestModel);
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
