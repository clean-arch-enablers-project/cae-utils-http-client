package com.cae.http_client.implementations.exceptions;

import com.cae.http_client.HttpResponse;
import lombok.Getter;

@Getter
class RetryNeededOnHttpStatusCodeException extends RuntimeException{

    private final HttpResponse httpResponse;

    public RetryNeededOnHttpStatusCodeException(HttpResponse httpResponse){
        super("Retry needed on ".concat(httpResponse.getStatusCode().toString()).concat(" received"));
        this.httpResponse = httpResponse;
    }

}
