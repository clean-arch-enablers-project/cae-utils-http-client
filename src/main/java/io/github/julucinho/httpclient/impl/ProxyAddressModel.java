package io.github.julucinho.httpclient.impl;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProxyAddressModel {

    private final String host;
    private final Integer port;

}
