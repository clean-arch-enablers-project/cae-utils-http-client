package com.clean_arch_enablers.http_client.implementations;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HttpRequestQueryParameter {

    private final String queryParameterName;
    private final String queryParameterValue;

    String buildQueryParameter(){
        return this.queryParameterName.concat("=").concat(this.queryParameterValue);
    }

}
