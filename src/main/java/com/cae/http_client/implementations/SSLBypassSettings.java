package com.cae.http_client.implementations;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SSLBypassSettings {

    public static SSLContext getContext(){
        try{
            var allTrustingManager = new TrustManager[] {new CustomTrustManager()};
            var sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, allTrustingManager, new SecureRandom());
            return sslContext;
        } catch (NoSuchAlgorithmException noSuchAlgorithmException){
            throw new SSLBypassSettingsException(
                    "Couldn't set the bypass for SSL. Problem while trying to get the SSL Context instance for TLS. More details:"
                    + noSuchAlgorithmException
            );
        } catch (KeyManagementException keyManagementException){
            throw new SSLBypassSettingsException(
                    "Couldn't set the bypass for SSL. Problem while trying init the SSL Context. More details:"
                    + keyManagementException
            );
        }
    }

    public static class CustomTrustManager extends X509ExtendedTrustManager {

        public static final String BYPASS_MESSAGE = "Bypassing SSL verifications";

        @Override
        public void checkClientTrusted(X509Certificate[] certs, String authType) {
            System.out.println(BYPASS_MESSAGE);
        }

        @Override
        public void checkServerTrusted(X509Certificate[] certs, String authType) {
            System.out.println(BYPASS_MESSAGE);
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) {
            System.out.println(BYPASS_MESSAGE);
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) {
            System.out.println(BYPASS_MESSAGE);
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) {
            System.out.println(BYPASS_MESSAGE);
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) {
            System.out.println(BYPASS_MESSAGE);
        }
    }

    public static class SSLBypassSettingsException extends RuntimeException{

        public SSLBypassSettingsException(String message){
            super(message);
        }

    }

}
