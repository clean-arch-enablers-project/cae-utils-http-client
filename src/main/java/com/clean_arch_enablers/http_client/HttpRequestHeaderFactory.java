package com.clean_arch_enablers.http_client;

import java.util.Map;

/**
 * Factory for HTTP Request Headers
 */
public interface HttpRequestHeaderFactory {

    /**
     * This method will be called under the hood when an instance of this type is passed during the building process of a HttpRequestModel instance.
     * @return a map in which the keys are the HTTP Headers and the values are their respective HTTP Header Values
     */
    Map<String, String> makeHeaders();

}
