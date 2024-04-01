package io.github.julucinho.httpclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RetrierModelTest {

    @Test
    @DisplayName("Should not return null reference when static constructor invoked")
    void shouldNotReturnNullReferenceWhenStaticConstructorInvoked(){
        Assertions.assertNotNull(RetrierModel.withLimitOf(6));
    }

    @Test
    @DisplayName("Should fill both fields when instantiated via static constructor")
    void shouldFillBothFieldsWhenInstantiatedViaStaticConstructor(){
        var retrierModel = RetrierModel.withLimitOf(4);
        Assertions.assertNotNull(retrierModel.getOriginalLimitForRetrying());
        Assertions.assertNotNull(retrierModel.getRetriesRemaining());
    }

}
