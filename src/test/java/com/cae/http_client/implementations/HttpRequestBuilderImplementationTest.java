package com.cae.http_client.implementations;

import com.cae.http_client.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class HttpRequestBuilderImplementationTest {

    @Test
    @DisplayName("Should add header correctly")
    void shouldAddHeaderCorrectly(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        var key = "chuchu";
        builder.headerOf(key, "Duda");
        Assertions.assertNotNull(request.headers.get(key));
    }

    @Test
    @DisplayName("Should return builder instance when finishing method of adding header")
    void shouldReturnBuilderInstanceWhenFinishingMethodOfAddingHeader(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        var returnedBuilder = builder.headerOf("chuchu", "Duda");
        Assertions.assertNotNull(returnedBuilder);
        Assertions.assertEquals(builder, returnedBuilder);
    }

    private static class HeadersFactoryForTestingMatters implements HttpRequestHeaderFactory {

        static final String KEY_1 = "chuchu";
        static final String KEY_2 = "biridinho";
        static final String KEY_3 = "biridao";

        @Override
        public Map<String, String> makeHeaders() {
            var headers = new HashMap<String, String>();
            headers.put(KEY_1, "Duda");
            headers.put(KEY_2, "Zena");
            headers.put(KEY_3, "Mingau");
            return headers;
        }
    }

    @Test
    @DisplayName("Should add headers factory correctly")
    void shouldAddHeadersFactoryCorrectly(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        builder.headersFactory(new HeadersFactoryForTestingMatters());
        Arrays.asList(HeadersFactoryForTestingMatters.KEY_1,
                HeadersFactoryForTestingMatters.KEY_2,
                HeadersFactoryForTestingMatters.KEY_3)
                .forEach(key -> Assertions.assertNotNull(request.headers.get(key)));
    }

    @Test
    @DisplayName("Should return builder instance when finishing method of adding headers factory")
    void shouldReturnBuilderInstanceWhenFinishingMethodOfAddingHeaderFactory(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        var returnedBuilder = builder.headersFactory(new HeadersFactoryForTestingMatters());
        Assertions.assertNotNull(returnedBuilder);
        Assertions.assertEquals(builder, returnedBuilder);
    }

    @Test
    @DisplayName("Should add path variable correctly")
    void shouldAddPathVariableCorrectly(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users/test/{testId}", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        builder.pathVariableOf("testId", "testValue");
        Assertions.assertFalse(request.pathVariables.isEmpty());
        request.pathVariables.get(0).buildPathVariableInto(request);
        Assertions.assertEquals("http://localhost:8080/users/test/testValue", request.uri);
    }

    @Test
    @DisplayName("Should return builder instance when finishing method of adding path variable")
    void shouldReturnBuilderInstanceWhenFinishingMethodOfAddingPathVariable(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        var returnedBuilder = builder.pathVariableOf("test", "testValue");
        Assertions.assertNotNull(returnedBuilder);
        Assertions.assertEquals(builder, returnedBuilder);
    }

    @Test
    @DisplayName("Should add query param correctly")
    void shouldAddQueryParamCorrectly(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        builder.queryParameterOf("test", "testValue");
        Assertions.assertFalse(request.queryParameters.isEmpty());
        Assertions.assertEquals("test=testValue", request.queryParameters.get(0).buildQueryParameter());
    }

    @Test
    @DisplayName("Should return builder instance when finishing method of adding query param")
    void shouldReturnBuilderInstanceWhenFinishingMethodOfAddingQueryParam(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        var returnedBuilder = builder.queryParameterOf("test", "testValue");
        Assertions.assertNotNull(returnedBuilder);
        Assertions.assertEquals(builder, returnedBuilder);
    }

    @Test
    @DisplayName("Should finish building process returning request model instance")
    void shouldFinishBuildingProcessReturningRequestModelInstance(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        var resultFromBuilding = builder.buildRequestModel();
        Assertions.assertNotNull(resultFromBuilding);
        Assertions.assertEquals(request, resultFromBuilding);
    }

    @Test
    @DisplayName("Should add response handler by status code correctly")
    void shouldAddResponseHandlerByStatusCodeCorrectly(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        var statusCode = 400;
        var handler = (HttpResponseHandler) response -> System.out.println("hello, 400");
        builder.handlerByHttpStatusCode(statusCode, handler);
        Assertions.assertFalse(request.responseHandlersByStatusCode.isEmpty());
        Assertions.assertEquals(handler, request.responseHandlersByStatusCode.get(statusCode));
    }

    @Test
    @DisplayName("Should return builder instance when finishing method of adding handler by status code")
    void shouldReturnBuilderInstanceWhenFinishingMethodOfAddingResponseHandlerByStatusCode(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        var statusCode = 400;
        var handler = (HttpResponseHandler) response -> System.out.println("hello, 400");
        var returnedBuilder = builder.handlerByHttpStatusCode(statusCode, handler);
        Assertions.assertNotNull(returnedBuilder);
        Assertions.assertEquals(builder, returnedBuilder);
    }

    private static class HttpResponseHandlersByStatusCodeFactoryForTestingMatters implements HttpResponseHandlersByStatusCodeFactory {

        static final Integer KEY_1 = 400;
        static final Integer KEY_2 = 401;
        static final Integer KEY_3 = 403;

        @Override
        public Map<Integer, HttpResponseHandler> makeHandlers() {
            var handlers = new HashMap<Integer, HttpResponseHandler>();
            handlers.put(KEY_1, response -> System.out.println("Hello, 400"));
            handlers.put(KEY_2, response -> System.out.println("Hello, 401"));
            handlers.put(KEY_3, response -> System.out.println("Hello, 403"));
            return handlers;
        }
    }

    @Test
    @DisplayName("Should add response handlers by status code factory correctly")
    void shouldAddHttpResponseHandlersByStatusCodeFactoryCorrectly(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        builder.handlersByHttpStatusCodeFactory(new HttpResponseHandlersByStatusCodeFactoryForTestingMatters());
        Arrays.asList(HttpResponseHandlersByStatusCodeFactoryForTestingMatters.KEY_1,
                HttpResponseHandlersByStatusCodeFactoryForTestingMatters.KEY_2,
                HttpResponseHandlersByStatusCodeFactoryForTestingMatters.KEY_3)
                .forEach(key -> Assertions.assertNotNull(request.responseHandlersByStatusCode.get(key)));
    }

    @Test
    @DisplayName("Should return builder instance when finishing method of adding response handlers by status code factory")
    void shouldReturnBuilderInstanceWhenFinishingMethodOfAddingHttpResponseHandlersByStatusCodeFactory(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        var returnedBuilder = builder.handlersByHttpStatusCodeFactory(new HttpResponseHandlersByStatusCodeFactoryForTestingMatters());
        Assertions.assertNotNull(returnedBuilder);
        Assertions.assertEquals(builder, returnedBuilder);
    }

    @Test
    @DisplayName("Should add response handler for any not successful response correctly")
    void shouldAddResponseHandlerForAnyNotSuccessfulResponseCorrectly(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        var handler = (HttpResponseHandler) response -> System.out.println("hello, 400");
        builder.handlerForAnyUnsuccessfulResponse(handler);
        Assertions.assertNotNull(request.genericResponseHandler);
        Assertions.assertEquals(handler, request.genericResponseHandler);
    }

    @Test
    @DisplayName("Should return builder instance when finishing method of adding response handler for any not successful response")
    void shouldReturnBuilderInstanceWhenFinishingMethodOfAddingResponseHandlerForAnyNotSuccessfulResponse(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        var handler = (HttpResponseHandler) response -> System.out.println("hello, 400");
        var returnedBuilder = builder.handlerForAnyUnsuccessfulResponse(handler);
        Assertions.assertNotNull(returnedBuilder);
        Assertions.assertEquals(builder, returnedBuilder);
    }

    @Test
    @DisplayName("Should add response handler by exception type correctly")
    void shouldAddResponseHandlerByExceptionTypeCorrectly(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        var handler = (ExceptionHandler) exception -> System.out.println("hello, exception");
        var exceptionToHandle = RuntimeException.class;
        builder.handlerByExceptionType(exceptionToHandle, handler);
        Assertions.assertFalse(request.exceptionHandlersByExceptionType.isEmpty());
        Assertions.assertEquals(handler, request.exceptionHandlersByExceptionType.get(exceptionToHandle));
    }

    @Test
    @DisplayName("Should return builder instance when finishing method of adding response handler by exception type")
    void shouldReturnBuilderInstanceWhenFinishingMethodOfAddingResponseHandlerByExceptionType(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        var handler = (ExceptionHandler) exception -> System.out.println("hello, exception");
        var exceptionToHandle = RuntimeException.class;
        var returnedBuilder = builder.handlerByExceptionType(exceptionToHandle, handler);
        Assertions.assertNotNull(returnedBuilder);
        Assertions.assertEquals(builder, returnedBuilder);
    }

    private static class HttpExceptionHandlersByExceptionTypeFactoryForTestingMatters implements HttpExceptionHandlersByExceptionTypeFactory {

        static final Class<? extends Exception> KEY_1 = RuntimeException.class;
        static final Class<? extends Exception> KEY_2 = InterruptedException.class;
        static final Class<? extends Exception> KEY_3 = Exception.class;


        @Override
        public Map<Class<? extends Exception>, ExceptionHandler> makeHandlers() {
            var handlers = new HashMap<Class<? extends Exception>, ExceptionHandler>();
            handlers.put(KEY_1, exception -> System.out.println("Hello, RuntimeException"));
            handlers.put(KEY_2, exception -> System.out.println("Hello, InterruptedException"));
            handlers.put(KEY_3, exception -> System.out.println("Hello, Exception"));
            return handlers;
        }
    }

    @Test
    @DisplayName("Should add http response handlers by exception type factory correctly")
    void shouldAddHttpResponseHandlersByExceptionTypeFactoryCorrectly(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        builder.handlersByExceptionTypeFactory(new HttpExceptionHandlersByExceptionTypeFactoryForTestingMatters());
        Arrays.asList(HttpExceptionHandlersByExceptionTypeFactoryForTestingMatters.KEY_1,
                HttpExceptionHandlersByExceptionTypeFactoryForTestingMatters.KEY_2,
                HttpExceptionHandlersByExceptionTypeFactoryForTestingMatters.KEY_3)
                .forEach(key -> Assertions.assertNotNull(request.exceptionHandlersByExceptionType.get(key)));
    }

    @Test
    @DisplayName("Should return builder instance when finishing method of adding http response handlers by exception type factory")
    void shouldReturnBuilderInstanceWhenFinishingMethodOfAddingHttpResponseHandlersByExceptionTypeFactory(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        var returnedBuilder = builder.handlersByExceptionTypeFactory(new HttpExceptionHandlersByExceptionTypeFactoryForTestingMatters());
        Assertions.assertNotNull(returnedBuilder);
        Assertions.assertEquals(builder, returnedBuilder);
    }

    @Test
    @DisplayName("Should set proxy address to the request model")
    void shouldSetProxyAddressToTheRequestModel(){
        var host = "tururu.com";
        var port = 8080;
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request);
        builder.proxyAddress(host, port);
        Assertions.assertNotNull(request.proxyAddress);
        Assertions.assertNotNull(request.proxyAddress.getHost());
        Assertions.assertNotNull(request.proxyAddress.getPort());
        Assertions.assertEquals(host, request.proxyAddress.getHost());
        Assertions.assertEquals(port, request.proxyAddress.getPort());
    }

    @Test
    @DisplayName("Should return the builder instance after 'andAddProxyAddress' has been called")
    void shouldReturnTheBuilderInstanceAfterAndAddProxyAddressHasBeenCalled(){
        var request = HttpRequestModelImplementation.of("http://localhost:8080/users", new HttpRequestGetMethod());
        var builder = HttpRequestBuilderImplementation.of(request).proxyAddress("tururu.com", 8080);
        Assertions.assertNotNull(builder);
    }

}
