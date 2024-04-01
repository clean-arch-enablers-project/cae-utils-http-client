package io.github.julucinho.httpclient.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ThreadIdRetrievementUtilTest {

    @Test
    @DisplayName("Should return filled string")
    void shouldReturnFilledString(){
        Assertions.assertFalse(ThreadIdRetrievementUtil.retrieveThreadId().isEmpty());
    }

    @Test
    @DisplayName("Should not return a blank string")
    void shouldNotReturnBlankString(){
        Assertions.assertFalse(ThreadIdRetrievementUtil.retrieveThreadId().isBlank());
    }

    @Test
    @DisplayName("Should not return null reference")
    void shouldNotReturnNullReference(){
        Assertions.assertNotNull(ThreadIdRetrievementUtil.retrieveThreadId());
    }

}
