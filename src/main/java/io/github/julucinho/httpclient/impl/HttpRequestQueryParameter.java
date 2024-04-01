package io.github.julucinho.httpclient.impl;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class HttpRequestQueryParameter {

    private final String queryParameterName;
    private final String queryParameterValue;

    String buildQueryParameter(){
        return this.queryParameterName.concat("=").concat(this.queryParameterValue);
    }

}
