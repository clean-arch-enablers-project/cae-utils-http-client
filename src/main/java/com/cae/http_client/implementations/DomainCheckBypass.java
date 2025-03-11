package com.cae.http_client.implementations;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.net.ssl.SSLParameters;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DomainCheckBypass {

    public static void run(SSLParameters sslParameters){
        sslParameters.setEndpointIdentificationAlgorithm("");
    }

}
