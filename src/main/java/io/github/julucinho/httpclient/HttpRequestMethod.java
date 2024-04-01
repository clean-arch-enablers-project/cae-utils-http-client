package io.github.julucinho.httpclient;


import io.github.julucinho.httpclient.impl.AbstractHttpRequestModel;
import io.github.julucinho.httpclient.impl.exceptions.RetryNeededOnExceptionThrownException;

public interface HttpRequestMethod {

    HttpResponse execute(AbstractHttpRequestModel httpRequestModel) throws RetryNeededOnExceptionThrownException;

}
