package com.cae.http_client;


import com.cae.http_client.implementations.AbstractHttpRequestModel;
import com.cae.http_client.implementations.exceptions.RetryNeededOnExceptionThrownException;

public interface HttpRequestMethod {

    HttpResponse execute(AbstractHttpRequestModel httpRequestModel) throws RetryNeededOnExceptionThrownException;

}
