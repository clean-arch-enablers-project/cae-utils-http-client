package io.github.julucinho.httpclient;

import io.github.julucinho.httpclient.commons.RetriersFactory;

import java.util.Map;

public interface RetriersByStatusCodeFactory extends RetriersFactory<Map<Integer, RetrierModel>> {
}
