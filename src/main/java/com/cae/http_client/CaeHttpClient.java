package com.cae.http_client;

import com.cae.http_client.implementations.HttpRequestStarterImplementation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CaeHttpClient {

    public static final HttpRequestStarter REQUEST_STARTER = new HttpRequestStarterImplementation();

    public static HttpRequestStarter getStarter(){
        return CaeHttpClient.REQUEST_STARTER;
    }

}
