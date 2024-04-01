package io.github.julucinho.httpclient;

import io.github.julucinho.httpclient.commons.HandlersFactory;

import java.util.Map;

public interface HttpResponseHandlersByStatusCodeFactory extends HandlersFactory<Map<Integer, HttpResponseHandler>> { }
