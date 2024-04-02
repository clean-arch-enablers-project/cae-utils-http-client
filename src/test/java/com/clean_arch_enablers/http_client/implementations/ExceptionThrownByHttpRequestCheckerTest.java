package com.clean_arch_enablers.http_client.implementations;

import com.clean_arch_enablers.http_client.RetrierModel;
import com.clean_arch_enablers.http_client.implementations.exceptions.JsonProcessingRuntimeException;
import com.clean_arch_enablers.http_client.implementations.exceptions.RetryNeededOnExceptionThrownException;
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
        this.abstractHttpRequestModel.retryCountersByExceptionType.put(RuntimeException.class, RetryCounterImplementation.of(RetrierModel.withLimitOf(4)));
        this.abstractHttpRequestModel.exceptionHandlersByExceptionType.put(Exception.class, this::doNothing);
        this.abstractHttpRequestModel.retryCountersByExceptionType.put(Exception.class, RetryCounterImplementation.of(RetrierModel.withLimitOf(4)));
        this.abstractHttpRequestModel.retryCountersByExceptionType.put(ClassNotFoundException.class, RetryCounterImplementation.of(RetrierModel.withLimitOf(4)));
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
