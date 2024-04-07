package com.cae.http_client.implementations;

import com.cae.http_client.HttpResponse;
import com.cae.http_client.RetrierModel;
import com.cae.http_client.implementations.exceptions.NoResponseHandlersAvailableForExecutionException;
import com.cae.http_client.implementations.exceptions.RetryNeededOnHttpStatusCodeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HttpResponseStatusCodeCheckerTest {

    HttpResponseStatusCodeChecker httpResponseStatusCodeChecker;
    AbstractHttpRequestModel httpRequestModel;
    @Mock
    HttpResponse response;

    @BeforeEach
    void setUp(){
        this.httpRequestModel = HttpRequestModelImplementation.of("", new HttpRequestGetMethod());
        this.httpResponseStatusCodeChecker = HttpResponseStatusCodeChecker.of(this.httpRequestModel);
    }

    @Test
    @DisplayName("Should throw parameterized exception type for the received status code when there is no retrier set")
    void shouldThrowParameterizedExceptionTypeForTheReceivedStatusCodeWhenThereIsNoRetrierSet(){
        Mockito.when(this.response.getStatusCode()).thenReturn(400);
        this.httpRequestModel.responseHandlersByStatusCode.put(400, httpResponse -> {throw new RuntimeException();});
        Assertions.assertThrows(RuntimeException.class, () -> this.httpResponseStatusCodeChecker.checkOutHandlersFor(this.response));
    }

    @Test
    @DisplayName("Should throw RetryNeededOnHttpStatusCodeException for the received status code when there is retrier set")
    void shouldThrowRetryNeededOnHttpStatusCodeExceptionForTheReceivedStatusCodeWhenThereIsRetrierSet(){
        Mockito.when(this.response.getStatusCode()).thenReturn(400);
        this.httpRequestModel.responseHandlersByStatusCode.put(400, httpResponse -> {throw new RuntimeException();});
        this.httpRequestModel.retryCountersByStatusCode.put(400, RetryCounterImplementation.of(RetrierModel.withLimitOf(4)));
        Assertions.assertThrows(RetryNeededOnHttpStatusCodeException.class, () -> this.httpResponseStatusCodeChecker.checkOutHandlersFor(this.response));
    }

    @Test
    @DisplayName("Should throw no response handlers available for execution exception")
    void shouldThrowNoResponseHandlersAvailableForExecutionException(){
        Mockito.when(this.response.getStatusCode()).thenReturn(400);
        Assertions.assertThrows(NoResponseHandlersAvailableForExecutionException.class, () -> this.httpResponseStatusCodeChecker.checkOutHandlersFor(this.response));
    }

    @Test
    @DisplayName("Should just execute the handler for the received status code when there is no retrier set")
    void shouldJustExecuteTheHandlerForTheReceivedStatusCodeWhenThereIsNoRetrierSet(){
        var stringBuilder = new StringBuilder("0");
        Mockito.when(this.response.getStatusCode()).thenReturn(400);
        this.httpRequestModel.responseHandlersByStatusCode.put(400, httpResponse -> stringBuilder.append("0"));
        this.httpResponseStatusCodeChecker.checkOutHandlersFor(this.response);
        var result = stringBuilder.toString();
        Assertions.assertEquals(2, result.length());
    }

}
