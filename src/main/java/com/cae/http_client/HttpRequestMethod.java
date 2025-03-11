package com.cae.http_client;


import com.cae.http_client.implementations.AbstractHttpRequestModel;

public interface HttpRequestMethod {

    HttpResponse execute(AbstractHttpRequestModel httpRequestModel);

}
