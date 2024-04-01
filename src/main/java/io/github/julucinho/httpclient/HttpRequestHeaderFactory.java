package io.github.julucinho.httpclient;

import java.util.Map;

public interface HttpRequestHeaderFactory {

    Map<String, String> makeHeaders();

}
