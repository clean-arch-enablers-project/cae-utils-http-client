package io.github.julucinho.httpclient.impl.exceptions;

import java.io.IOException;

public class IORuntimeException extends RuntimeException{

    public IORuntimeException(IOException exception){
        super(exception.getMessage());
    }

}
