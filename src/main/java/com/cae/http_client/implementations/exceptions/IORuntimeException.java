package com.cae.http_client.implementations.exceptions;

import java.io.IOException;

public class IORuntimeException extends RuntimeException{

    public IORuntimeException(IOException exception){
        super(exception.getMessage());
    }

}
