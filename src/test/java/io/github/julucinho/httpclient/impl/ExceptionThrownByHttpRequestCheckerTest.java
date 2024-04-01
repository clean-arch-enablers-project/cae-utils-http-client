package io.github.julucinho.httpclient.impl;

import io.github.julucinho.httpclient.RetrierModel;
import io.github.julucinho.httpclient.impl.exceptions.JsonProcessingRuntimeException;
import io.github.julucinho.httpclient.impl.exceptions.RetryNeededOnExceptionThrownException;
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
        this.abstractHttpRequestModel = HttpRequestModelImpl.of("http://localhost:8080/tutstuts", new HttpRequestGetMethod());
        this.abstractHttpRequestModel.retryCountersByExceptionType.put(RuntimeException.class, RetryCounterImpl.of(RetrierModel.withLimitOf(4)));
        this.abstractHttpRequestModel.exceptionHandlersByExceptionType.put(Exception.class, this::doNothing);
        this.abstractHttpRequestModel.retryCountersByExceptionType.put(Exception.class, RetryCounterImpl.of(RetrierModel.withLimitOf(4)));
        this.abstractHttpRequestModel.retryCountersByExceptionType.put(ClassNotFoundException.class, RetryCounterImpl.of(RetrierModel.withLimitOf(4)));
    }

    private void doNothing(Exception exception) { }

    @Test
    @DisplayName("Should not return null reference when static constructor invoked")
    void shouldNotReturnNullReferenceWhenStaticConstructorInvoked(){
        Assertions.assertNotNull(ExceptionThrownByHttpRequestChecker.of(this.abstractHttpRequestModel));
    }

    @Test
    @DisplayName("Should throw RetryNeededOnExceptionThrownException")
    void shouldThrowRetryNeededOnExceptionThrownException(){
        Arrays.asList(new ClassNotFoundException(), new Exception(), new RuntimeException()).forEach(exceptionThrown -> {
            var checker =  ExceptionThrownByHttpRequestChecker.of(this.abstractHttpRequestModel);
            Assertions.assertThrows(RetryNeededOnExceptionThrownException.class, () -> checker.checkOn(exceptionThrown));
        });

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
