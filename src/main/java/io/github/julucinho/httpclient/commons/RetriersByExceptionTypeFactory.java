package io.github.julucinho.httpclient.commons;


import io.github.julucinho.httpclient.RetrierModel;

import java.util.Map;

public interface RetriersByExceptionTypeFactory extends RetriersFactory<Map<Class<? extends Exception>, RetrierModel>> {
}
