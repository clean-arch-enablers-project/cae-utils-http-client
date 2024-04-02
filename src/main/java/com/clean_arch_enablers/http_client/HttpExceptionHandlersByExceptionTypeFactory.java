package com.clean_arch_enablers.http_client;

import com.clean_arch_enablers.http_client.commons.HandlersFactory;

import java.util.Map;

public interface HttpExceptionHandlersByExceptionTypeFactory extends HandlersFactory<Map<Class<? extends Exception>, ExceptionHandler>> { }



