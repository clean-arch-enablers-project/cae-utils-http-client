package com.clean_arch_enablers.http_client.implementations;


import com.clean_arch_enablers.http_client.HttpRequestMethod;
import com.clean_arch_enablers.http_client.HttpResponse;
import com.clean_arch_enablers.http_client.implementations.exceptions.RetryNeededOnExceptionThrownException;

public class HttpRequestDeleteMethod implements HttpRequestMethod {

    @Override
    public HttpResponse execute(AbstractHttpRequestModel httpRequestModel) throws RetryNeededOnExceptionThrownException {
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
