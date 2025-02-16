package com.cae.http_client.implementations;

import com.cae.http_client.implementations.exceptions.JsonProcessingRuntimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class ExceptionThrownByHttpRequestCheckerTest {

    AbstractHttpRequestModel abstractHttpRequestModel;

    @BeforeEach
    void setUp(){
        this.abstractHttpRequestModel = HttpRequestModelImplementation.of("http://localhost:8080/tutstuts", new HttpRequestGetMethod());
        this.abstractHttpRequestModel.exceptionHandlersByExceptionType.put(Exception.class, this::doNothing);
    }

    private void doNothing(Exception exception) { }

    @Test
    @DisplayName("Should not return null reference when static constructor invoked")
    void shouldNotReturnNullReferenceWhenStaticConstructorInvoked(){
        Assertions.assertNotNull(ExceptionThrownByHttpRequestChecker.of(this.abstractHttpRequestModel));
    }

    @Test
    @DisplayName("Should not throw RetryNeededOnExceptionThrownException")
    void shouldNotThrowRetryNeededOnExceptionThrownException(){
        Arrays.asList(new JsonProcessingRuntimeException(""), new InterruptedException()).forEach(exceptionThrown -> {
            var checker =  ExceptionThrownByHttpRequestChecker.of(this.abstractHttpRequestModel);
            Assertions.assertDoesNotThrow(() -> checker.checkOn(exceptionThrown));
        });

    }



}
