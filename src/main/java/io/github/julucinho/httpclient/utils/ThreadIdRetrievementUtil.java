package io.github.julucinho.httpclient.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThreadIdRetrievementUtil {

    public static String retrieveThreadId(){
        return "Current thread: "
                .concat(String.valueOf(Thread.currentThread().getId())
                .concat(":")
                .concat(Thread.currentThread().getName()));
    }

}
