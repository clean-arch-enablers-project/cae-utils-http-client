package com.clean_arch_enablers.http_client;

import com.clean_arch_enablers.http_client.commons.HandlersFactory;

import java.util.Map;

public interface HttpResponseHandlersByStatusCodeFactory extends HandlersFactory<Map<Integer, HttpResponseHandler>> { }
