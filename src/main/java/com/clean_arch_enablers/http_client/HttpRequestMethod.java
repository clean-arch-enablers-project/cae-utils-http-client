package com.clean_arch_enablers.http_client;


import com.clean_arch_enablers.http_client.implementations.AbstractHttpRequestModel;
import com.clean_arch_enablers.http_client.implementations.exceptions.RetryNeededOnExceptionThrownException;

public interface HttpRequestMethod {

    HttpResponse execute(AbstractHttpRequestModel httpRequestModel) throws RetryNeededOnExceptionThrownException;

}
