package com.cae.http_client;

import com.cae.http_client.commons.RetriersFactory;

import java.util.Map;

interface RetriersByStatusCodeFactory extends RetriersFactory<Map<Integer, RetrierModel>> {
}
