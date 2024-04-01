package io.github.julucinho.httpclient.impl;

import io.github.julucinho.httpclient.HttpRequestStarter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.HttpRequest;

@ExtendWith(MockitoExtension.class)
class HttpRequestStarterImplTest {

    HttpRequestStarter httpRequestStarter = new HttpRequestStarterImpl();

    @Test
    @DisplayName("Should return non null instance of builder when starting for delete method")
    void shouldReturnNonNullInstanceOfBuilderWhenStartingForDeleteMethod(){
        Assertions.assertNotNull(this.httpRequestStarter.startDeleteRequestModelFor("http://localhost:2222"));
    }

    @Test
    @DisplayName("Should return non null instance of builder when starting for get method")
    void shouldReturnNonNullInstanceOfBuilderWhenStartingForGetMethod(){
        Assertions.assertNotNull(this.httpRequestStarter.startGetRequestModelFor("http://localhost:2222"));
    }

    @Test
    @DisplayName("Should return non null instance of builder when starting for post method")
    void shouldReturnNonNullInstanceOfBuilderWhenStartingForPostMethod(){
        Assertions.assertNotNull(this.httpRequestStarter.startPostRequestModelFor("http://localhost:2222", HttpRequest.BodyPublishers.ofString("{someRandomJsonField: \"someRandomValue\"}")));
    }

    @Test
    @DisplayName("Should return non null instance of builder when starting for put method")
    void shouldReturnNonNullInstanceOfBuilderWhenStartingForPutMethod(){
        Assertions.assertNotNull(this.httpRequestStarter.startPutRequestModelFor("http://localhost:2222", HttpRequest.BodyPublishers.ofString("{someRandomJsonField: \"someRandomValue\"}")));
    }

}
