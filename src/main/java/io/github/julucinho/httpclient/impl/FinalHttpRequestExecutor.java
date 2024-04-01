package io.github.julucinho.httpclient.impl;

import io.github.julucinho.httpclient.impl.exceptions.IORuntimeException;
import io.github.julucinho.httpclient.impl.exceptions.InterruptedRuntimeException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
        Optional.ofNullable(this.httpRequestModel.proxyAddress).ifPresent(proxyAddress -> client.proxy(ProxySelector.of(new InetSocketAddress(proxyAddress.getHost(), proxyAddress.getPort()))));
        return client.build();
    }
}
