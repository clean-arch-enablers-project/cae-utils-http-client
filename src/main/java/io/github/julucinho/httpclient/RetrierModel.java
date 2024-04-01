package io.github.julucinho.httpclient;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RetrierModel {

    private final Integer originalLimitForRetrying;
    private Integer retriesRemaining;

    public static RetrierModel withLimitOf(Integer limitForRetrying){
        var retrier = new RetrierModel(limitForRetrying);
        retrier.setRetriesRemaining(retrier.getOriginalLimitForRetrying());
        return retrier;
    }

}
