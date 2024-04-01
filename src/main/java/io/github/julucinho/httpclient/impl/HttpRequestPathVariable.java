package io.github.julucinho.httpclient.impl;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class HttpRequestPathVariable {

    private final String pathVariableName;
    private final String pathVariableValue;

    String buildPathVariable(){
        return "/".concat(this.pathVariableName).concat("/").concat(this.pathVariableValue);
    }

}
