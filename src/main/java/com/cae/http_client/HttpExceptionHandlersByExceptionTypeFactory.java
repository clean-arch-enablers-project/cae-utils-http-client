package com.cae.http_client;

import com.cae.http_client.commons.HandlersFactory;

import java.util.Map;

public interface HttpExceptionHandlersByExceptionTypeFactory extends HandlersFactory<Map<Class<? extends Exception>, ExceptionHandler>> { }



