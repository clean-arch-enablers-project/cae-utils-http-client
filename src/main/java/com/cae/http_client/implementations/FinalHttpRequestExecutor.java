package com.cae.http_client.implementations;

import com.cae.http_client.implementations.exceptions.IORuntimeException;
import com.cae.http_client.implementations.exceptions.InterruptedRuntimeException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FinalHttpRequestExecutor {

    private AbstractHttpRequestModel httpRequestModel;

    public static FinalHttpRequestExecutor of(AbstractHttpRequestModel httpRequestModel){
        var executor = new FinalHttpRequestExecutor();
        executor.httpRequestModel = httpRequestModel;
        return executor;
    }

    public HttpResponse<String> execute(HttpRequest finalRequest){
        try {
            return this.createClient().send(finalRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new InterruptedRuntimeException(e);
        }
    }

    private HttpClient createClient() {
        var client = HttpClient.newBuilder();
        this.handleProxySettings(client);
        this.handleSslBypass(client);
        this.handleDomainCheckBypass(client);
        return client.build();
    }

    private void handleProxySettings(HttpClient.Builder client) {
        Optional.ofNullable(this.httpRequestModel.proxyAddress).ifPresent(
                proxyAddress -> client.proxy(
                        ProxySelector.of(
                                new InetSocketAddress(
                                        proxyAddress.getHost(),
                                        proxyAddress.getPort()
                                )
                        )
                )
        );
    }

    private void handleSslBypass(HttpClient.Builder client) {
        if (this.httpRequestModel.bypassSsl)
            client.sslContext(SSLBypassSettings.getContext());
    }

    private void handleDomainCheckBypass(HttpClient.Builder client) {
        if (this.httpRequestModel.bypassDomainCheck){
            var sslParameters = new SSLParameters();
            DomainCheckBypass.run(sslParameters);
            client.sslParameters(sslParameters);
        }
    }


}
