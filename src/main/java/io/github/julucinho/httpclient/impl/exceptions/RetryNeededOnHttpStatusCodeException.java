package io.github.julucinho.httpclient.impl.exceptions;

import io.github.julucinho.httpclient.HttpResponse;
import lombok.Getter;

@Getter
public class RetryNeededOnHttpStatusCodeException extends RuntimeException{

    private final HttpResponse httpResponse;

    public RetryNeededOnHttpStatusCodeException(HttpResponse httpResponse){
        super("Retry needed on ".concat(httpResponse.getStatusCode().toString()).concat(" received"));
        this.httpResponse = httpResponse;
    }

}
