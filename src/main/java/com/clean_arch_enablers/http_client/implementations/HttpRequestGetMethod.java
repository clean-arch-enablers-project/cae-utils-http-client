package com.clean_arch_enablers.http_client.implementations;


import com.clean_arch_enablers.http_client.HttpRequestMethod;
import com.clean_arch_enablers.http_client.HttpResponse;
import com.clean_arch_enablers.http_client.implementations.exceptions.RetryNeededOnExceptionThrownException;

public class HttpRequestGetMethod implements HttpRequestMethod {

    @Override
    public HttpResponse execute(AbstractHttpRequestModel httpRequestModel) throws RetryNeededOnExceptionThrownException {
        var finalRequest = FinalHttpRequestFactory.makeFinalRequestForGetMethodFrom(httpRequestModel);
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
