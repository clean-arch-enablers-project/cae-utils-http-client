package com.clean_arch_enablers.http_client.implementations.exceptions;


import com.clean_arch_enablers.http_client.HttpResponse;

public class NoResponseHandlersAvailableForExecutionException extends RuntimeException {

    public NoResponseHandlersAvailableForExecutionException(HttpResponse httpResponse) {
        super("No response handlers were available, not even the most generic one. "
            .concat("For handling any not successful response set a generic handler at the request building process; ")
            .concat("if you find interesting to have a specific response handler for the status code received (")
            .concat(httpResponse.getStatusCode().toString())
            .concat("), set a specific one, also at the request building process. ")
        );
    }

}
