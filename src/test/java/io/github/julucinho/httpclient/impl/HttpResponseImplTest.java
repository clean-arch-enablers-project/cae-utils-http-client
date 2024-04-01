package io.github.julucinho.httpclient.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.julucinho.httpclient.HttpRequestModel;
import io.github.julucinho.httpclient.HttpResponse;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class HttpResponseImplTest {

    HttpResponseImpl httpResponse;
    @Mock
    HttpRequestModel httpRequestModel;
    @Mock
    java.net.http.HttpResponse<String> unwrappedHttpResponse;

    @BeforeEach
    void setUp(){
        this.httpResponse = new HttpResponseImpl(this.httpRequestModel, this.unwrappedHttpResponse);
    }

    @Test
    @DisplayName("Should return the status code correctly")
    void shouldReturnTheStatusCodeCorrectly(){
        var expected = 200;
        Mockito.when(this.unwrappedHttpResponse.statusCode()).thenReturn(expected);
        var actual = this.httpResponse.getStatusCode();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return the request model correctly")
    void shouldReturnTheRequestModelCorrectly(){
        Assertions.assertEquals(this.httpRequestModel, this.httpResponse.getHttpRequest());
    }

    @Getter
    @Setter
    private static class TestingDecoding{
        private String test;
    }

    @Test
    @DisplayName("Should return body decoded to the specified type as parameter")
    void shouldReturnBodyDecodedToTheSpecifiedTypeAsParameter(){
        Mockito.when(this.unwrappedHttpResponse.body()).thenReturn("{\"test\":\"value\"}");
        var result = this.httpResponse.getBodyAs(TestingDecoding.class);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("value", result.getTest());
    }

    @Test
    @DisplayName("Should return body decoded to the specified type reference as parameter")
    void shouldReturnBodyDecodedToTheSpecifiedTypeReferenceAsParameter(){
        Mockito.when(this.unwrappedHttpResponse.body()).thenReturn("[{\"test\":\"value\"}]");
        var result = this.httpResponse.getBodyAs(new TypeReference<List<TestingDecoding>>() {});
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("value", result.get(0).getTest());
    }

    @Test
    @DisplayName("Should perform the action")
    void shouldPerformTheAction(){
        var textBuilder = new StringBuilder("0");
        Mockito.when(this.unwrappedHttpResponse.statusCode()).thenReturn(400);
        this.httpResponse.ifNeedsHandling(response -> this.concatSomeChars(response, textBuilder));
        var finalText = textBuilder.toString();
        Assertions.assertEquals("00", finalText);
    }

    @Test
    @DisplayName("Should not perform the action")
    void shouldNotPerformTheAction(){
        var textBuilder = new StringBuilder("0");
        Mockito.when(this.unwrappedHttpResponse.statusCode()).thenReturn(200);
        this.httpResponse.ifNeedsHandling(response -> this.concatSomeChars(response, textBuilder));
        var finalText = textBuilder.toString();
        Assertions.assertEquals("0", finalText);
    }

    private void concatSomeChars(HttpResponse httpResponse, StringBuilder textBuilder) {
        textBuilder.append("0");
    }

}
