package com.cae.http_client.implementations;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProxyAddressModel {

    private final String host;
    private final Integer port;

}
