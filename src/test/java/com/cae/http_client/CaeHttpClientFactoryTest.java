package com.cae.http_client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CaeHttpClientFactoryTest {

    @Test
    void shouldReturnFilledInstance(){
        Assertions.assertNotNull(CaeHttpClientFactory.REQUEST_STARTER);
    }

    @Test
    void shouldReturnSingletonInstanceWhenDirectlyCallingAttribute(){
        var resultFromCallOne = CaeHttpClientFactory.REQUEST_STARTER;
        var resultFromCallTwo = CaeHttpClientFactory.REQUEST_STARTER;
        var resultFromCallThree = CaeHttpClientFactory.REQUEST_STARTER;
        Assertions.assertEquals(resultFromCallOne, resultFromCallTwo);
        Assertions.assertEquals(resultFromCallTwo, resultFromCallThree);
    }

    @Test
    void shouldReturnSingletonInstanceWhenCallingGetter(){
        var resultFromCallOne = CaeHttpClientFactory.getSingletonClient();
        var resultFromCallTwo = CaeHttpClientFactory.getSingletonClient();
        var resultFromCallThree = CaeHttpClientFactory.getSingletonClient();
        Assertions.assertEquals(resultFromCallOne, resultFromCallTwo);
        Assertions.assertEquals(resultFromCallTwo, resultFromCallThree);
    }

}
