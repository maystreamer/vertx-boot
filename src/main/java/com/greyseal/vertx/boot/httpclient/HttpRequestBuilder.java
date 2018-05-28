package com.greyseal.vertx.boot.httpclient;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import java.util.Map;
import java.util.function.Consumer;

public class HttpRequestBuilder {
    private HttpMethod httpMethod;
    private String resourceURL;
    private JsonObject payload;
    private Map<String, String> headers;

    public HttpRequestBuilder with(
            Consumer<HttpRequestBuilder> builderFunction) {
        builderFunction.accept(this);
        return this;
    }

    public HttpRequest createRequest() {
        return new HttpRequest(httpMethod, resourceURL, payload,
                headers);
    }
}
