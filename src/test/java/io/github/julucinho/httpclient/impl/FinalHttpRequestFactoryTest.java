package io.github.julucinho.httpclient.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.HttpRequest;

@ExtendWith(MockitoExtension.class)
class FinalHttpRequestFactoryTest {

    @Test
    @DisplayName("Should set query params right")
    void shouldSetQueryParamsRight(){
        var request = HttpRequestModelImpl.of("http://localhost:8080/uhg", new HttpRequestGetMethod());
        var queryParam1 = new HttpRequestQueryParameter("testKey", "testValue");
        var queryParam2 = new HttpRequestQueryParameter("testKey2", "testValue2");
        request.queryParameters.add(queryParam1);
        request.queryParameters.add(queryParam2);
        var finalHttpRequest = FinalHttpRequestFactory.makeFinalRequestForGetMethodFrom(request);
        var queryParams = finalHttpRequest.uri().getQuery();
        Assertions.assertTrue(queryParams.contains(queryParam1.buildQueryParameter()));
        Assertions.assertTrue(queryParams.contains(queryParam2.buildQueryParameter()));
    }

    @Test
    @DisplayName("Should set path variables right")
    void shouldSetPathVariablesRight(){
        var request = HttpRequestModelImpl.of("http://localhost:8080/uhg", new HttpRequestGetMethod());
        var pathVariable1 = new HttpRequestPathVariable("companies", "62333");
        var pathVariable2 = new HttpRequestPathVariable("users", "34234");
        request.pathVariables.add(pathVariable1);
        request.pathVariables.add(pathVariable2);
        var finalHttpRequest = FinalHttpRequestFactory.makeFinalRequestForGetMethodFrom(request);
        var uri = finalHttpRequest.uri().toString();
        Assertions.assertTrue(uri.contains(pathVariable1.buildPathVariable()));
        Assertions.assertTrue(uri.contains(pathVariable2.buildPathVariable()));
    }

    @Test
    @DisplayName("Should set body right")
    void shouldSetBodyRight(){
        var request = HttpRequestModelImpl.of("http://localhost:8080/uhg", new HttpRequestPostMethod());
        request.body = HttpRequest.BodyPublishers.ofString("{testField:\"testValue\"}");
        var finalHttpRequest = FinalHttpRequestFactory.makeFinalRequestForPostMethodFrom(request);
        var bodyFromFinalRequest = finalHttpRequest.bodyPublisher().orElseThrow();
        Assertions.assertEquals(request.body, bodyFromFinalRequest);
    }

    @Test
    @DisplayName("Should make instance for get method")
    void shouldMakeInstanceForGetMethod(){
        var request = HttpRequestModelImpl.of("http://localhost:8080/uhg", new HttpRequestGetMethod());
        Assertions.assertNotNull(FinalHttpRequestFactory.makeFinalRequestForGetMethodFrom(request));
    }

    @Test
    @DisplayName("Should make instance for delete method")
    void shouldMakeInstanceForDeleteMethod(){
        var request = HttpRequestModelImpl.of("http://localhost:8080/uhg", new HttpRequestDeleteMethod());
        Assertions.assertNotNull(FinalHttpRequestFactory.makeFinalRequestForDeleteMethodFrom(request));
    }

    @Test
    @DisplayName("Should make instance for post method")
    void shouldMakeInstanceForPostMethod(){
        var request = HttpRequestModelImpl.of("http://localhost:8080/uhg", new HttpRequestPostMethod());
        request.body = HttpRequest.BodyPublishers.ofString("{testField:\"testValue\"}");
        Assertions.assertNotNull(FinalHttpRequestFactory.makeFinalRequestForPostMethodFrom(request));
    }

    @Test
    @DisplayName("Should make instance for put method")
    void shouldMakeInstanceForPutMethod(){
        var request = HttpRequestModelImpl.of("http://localhost:8080/uhg", new HttpRequestPutMethod());
        request.body = HttpRequest.BodyPublishers.ofString("{testField:\"testValue\"}");
        Assertions.assertNotNull(FinalHttpRequestFactory.makeFinalRequestForPutMethodFrom(request));
    }

}
