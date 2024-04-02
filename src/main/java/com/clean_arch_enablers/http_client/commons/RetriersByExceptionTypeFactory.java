package com.clean_arch_enablers.http_client.commons;


import com.clean_arch_enablers.http_client.RetrierModel;

import java.util.Map;

public interface RetriersByExceptionTypeFactory extends RetriersFactory<Map<Class<? extends Exception>, RetrierModel>> {
}
