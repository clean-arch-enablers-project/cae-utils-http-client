package com.cae.http_client.commons;


import com.cae.http_client.RetrierModel;

import java.util.Map;

public interface RetriersByExceptionTypeFactory extends RetriersFactory<Map<Class<? extends Exception>, RetrierModel>> {
}
