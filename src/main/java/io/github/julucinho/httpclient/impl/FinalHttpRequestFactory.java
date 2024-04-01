package io.github.julucinho.httpclient.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FinalHttpRequestFactory {

    public static HttpRequest makeFinalRequestForGetMethodFrom(AbstractHttpRequestModel httpRequestModel) {
        var finalHttpRequestBuilder = HttpRequest.newBuilder().GET();
        return finallyBuildIt(finalHttpRequestBuilder, httpRequestModel);
    }

    public static HttpRequest makeFinalRequestForPostMethodFrom(AbstractHttpRequestModel httpRequestModel) {
        var finalHttpRequestBuilder = HttpRequest.newBuilder().POST(httpRequestModel.body);
        return finallyBuildIt(finalHttpRequestBuilder, httpRequestModel);
    }

    public static HttpRequest makeFinalRequestForPutMethodFrom(AbstractHttpRequestModel httpRequestModel) {
        var finalHttpRequestBuilder = HttpRequest.newBuilder().PUT(httpRequestModel.body);
        return finallyBuildIt(finalHttpRequestBuilder, httpRequestModel);
    }

    public static HttpRequest makeFinalRequestForDeleteMethodFrom(AbstractHttpRequestModel httpRequestModel) {
        var finalHttpRequestBuilder = HttpRequest.newBuilder().DELETE();
        return finallyBuildIt(finalHttpRequestBuilder, httpRequestModel);
    }

    private static HttpRequest finallyBuildIt(HttpRequest.Builder finalHttpRequestBuilder, AbstractHttpRequestModel httpRequestModel) {
        setUriInto(finalHttpRequestBuilder, httpRequestModel);
        setHeadersInto(finalHttpRequestBuilder, httpRequestModel);
        return finalHttpRequestBuilder.build();
    }

    private static void setUriInto(HttpRequest.Builder finalHttpRequestBuilder, AbstractHttpRequestModel httpRequestModel) {
        var uriBuilder = new StringBuilder(httpRequestModel.uri);
        setPathVariablesInto(uriBuilder, httpRequestModel.pathVariables);
        setQueryParametersInto(uriBuilder, httpRequestModel.queryParameters);
        var finalUriString = uriBuilder.toString();
        var finalUri = URI.create(finalUriString);
        finalHttpRequestBuilder.uri(finalUri);
    }

    private static void setPathVariablesInto(StringBuilder uriBuilder, List<HttpRequestPathVariable> pathVariables) {
        pathVariables.stream().map(HttpRequestPathVariable::buildPathVariable).forEach(uriBuilder::append);
    }

    private static void setQueryParametersInto(StringBuilder uriBuilder, List<HttpRequestQueryParameter> queryParameters) {
        if (!queryParameters.isEmpty()){
            var firstQueryParam = queryParameters.get(0).buildQueryParameter();
            var otherQueryParams = queryParameters.subList(1, queryParameters.size()).stream().map(HttpRequestQueryParameter::buildQueryParameter).collect(Collectors.toList());
            uriBuilder.append("?".concat(firstQueryParam));
            otherQueryParams.stream().map("&"::concat).forEach(uriBuilder::append);
        }
    }

    private static void setHeadersInto(HttpRequest.Builder finalHttpRequestBuilder, AbstractHttpRequestModel httpRequestModel) {
        httpRequestModel.headers.forEach(finalHttpRequestBuilder::header);
    }
}
