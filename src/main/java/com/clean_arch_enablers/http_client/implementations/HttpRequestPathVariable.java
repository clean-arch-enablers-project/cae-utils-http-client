package com.clean_arch_enablers.http_client.implementations;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HttpRequestPathVariable {

    private final String pathVariablePlaceholder;
    private final String pathVariableValue;

    void buildPathVariableInto(AbstractHttpRequestModel requestModel){
        var placeholder = this.pathVariablePlaceholder.replace("{", "").replace("}", "");
        if (requestModel.uri.contains(placeholder))
            requestModel.uri = requestModel.uri.replace("{" + placeholder + "}", this.pathVariableValue);
        else
            throw new NoPathVariablePlaceholderFound(placeholder, requestModel.uri);
    }

    public static class NoPathVariablePlaceholderFound extends RuntimeException {
        public NoPathVariablePlaceholderFound(String placeholder, String uri) {
            super("No placeholder '" + placeholder + "' was found in the URI '" + uri + "'. Make sure such placeholder is present.");
        }
    }
}
