package io.github.julucinho.httpclient;

import io.github.julucinho.httpclient.commons.HandlersFactory;

import java.util.Map;

public interface HttpExceptionHandlersByExceptionTypeFactory extends HandlersFactory<Map<Class<? extends Exception>, ExceptionHandler>> { }



