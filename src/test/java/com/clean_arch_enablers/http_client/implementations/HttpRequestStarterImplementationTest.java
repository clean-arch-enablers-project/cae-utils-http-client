package com.clean_arch_enablers.http_client.implementations;

import com.clean_arch_enablers.http_client.HttpRequestStarter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.HttpRequest;

@ExtendWith(MockitoExtension.class)
class HttpRequestStarterImplementationTest {

    HttpRequestStarter httpRequestStarter = new HttpRequestStarterImplementation();

    @Test
    @DisplayName("Should return non null instance of builder when starting for delete method")
    void shouldReturnNonNullInstanceOfBuilderWhenStartingForDeleteMethod(){
        Assertions.assertNotNull(this.httpRequestStarter.startDeleteRequestFor("http://localhost:2222"));
    }

    @Test
    @DisplayName("Should return non null instance of builder when starting for get method")
    void shouldReturnNonNullInstanceOfBuilderWhenStartingForGetMethod(){
        Assertions.assertNotNull(this.httpRequestStarter.startGetRequestFor("http://localhost:2222"));
    }

    @Test
    @DisplayName("Should return non null instance of builder when starting for post method")
    void shouldReturnNonNullInstanceOfBuilderWhenStartingForPostMethod(){
        Assertions.assertNotNull(this.httpRequestStarter.startPostRequestFor("http://localhost:2222", HttpRequest.BodyPublishers.ofString("{someRandomJsonField: \"someRandomValue\"}")));
    }

    @Test
    @DisplayName("Should return non null instance of builder when starting for put method")
    void shouldReturnNonNullInstanceOfBuilderWhenStartingForPutMethod(){
        Assertions.assertNotNull(this.httpRequestStarter.startPutRequestFor("http://localhost:2222", HttpRequest.BodyPublishers.ofString("{someRandomJsonField: \"someRandomValue\"}")));
    }

}
