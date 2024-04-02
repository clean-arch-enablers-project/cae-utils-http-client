package com.clean_arch_enablers.http_client.implementations.exceptions;

import com.clean_arch_enablers.http_client.HttpResponse;
import lombok.Getter;

@Getter
public class RetryNeededOnHttpStatusCodeException extends RuntimeException{

    private final HttpResponse httpResponse;

    public RetryNeededOnHttpStatusCodeException(HttpResponse httpResponse){
        super("Retry needed on ".concat(httpResponse.getStatusCode().toString()).concat(" received"));
        this.httpResponse = httpResponse;
    }

}
