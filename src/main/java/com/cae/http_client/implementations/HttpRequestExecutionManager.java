package com.cae.http_client.implementations;

import com.cae.http_client.HttpResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class HttpRequestExecutionManager {

    protected AbstractHttpRequestModel httpRequest;

    static HttpRequestExecutionManager of(AbstractHttpRequestModel httpRequest){
        var manager = new HttpRequestExecutionManager();
        manager.httpRequest = httpRequest;
        return manager;
    }

    protected HttpResponse run() {
        var response = this.httpRequest.method.execute(this.httpRequest);
        response.ifNeedsHandling(HttpResponseStatusCodeChecker.of(this.httpRequest)::checkOutHandlersFor);
        return response;
    }

}
