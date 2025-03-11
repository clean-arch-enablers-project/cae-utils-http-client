package com.cae.http_client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CaeHttpClientTest {

    @Test
    void shouldReturnFilledInstance(){
        Assertions.assertNotNull(CaeHttpClient.REQUEST_STARTER);
    }

    @Test
    void shouldReturnSingletonInstanceWhenDirectlyCallingAttribute(){
        var resultFromCallOne = CaeHttpClient.REQUEST_STARTER;
        var resultFromCallTwo = CaeHttpClient.REQUEST_STARTER;
        var resultFromCallThree = CaeHttpClient.REQUEST_STARTER;
        Assertions.assertEquals(resultFromCallOne, resultFromCallTwo);
        Assertions.assertEquals(resultFromCallTwo, resultFromCallThree);
    }

    @Test
    void shouldReturnSingletonInstanceWhenCallingGetter(){
        var resultFromCallOne = CaeHttpClient.getStarter();
        var resultFromCallTwo = CaeHttpClient.getStarter();
        var resultFromCallThree = CaeHttpClient.getStarter();
        Assertions.assertEquals(resultFromCallOne, resultFromCallTwo);
        Assertions.assertEquals(resultFromCallTwo, resultFromCallThree);
    }

}
