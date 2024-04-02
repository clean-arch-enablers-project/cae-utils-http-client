package com.clean_arch_enablers.http_client;

import com.clean_arch_enablers.http_client.commons.RetriersFactory;

import java.util.Map;

public interface RetriersByStatusCodeFactory extends RetriersFactory<Map<Integer, RetrierModel>> {
}
