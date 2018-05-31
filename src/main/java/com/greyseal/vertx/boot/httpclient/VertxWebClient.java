package com.greyseal.vertx.boot.httpclient;

import io.reactivex.Single;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.reactivex.core.MultiMap;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.buffer.Buffer;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;
import java.util.Map;

public class VertxWebClient extends AbstractHttpClient {
    private static final VertxWebClient INSTANCE = new VertxWebClient();

    private static WebClient webClient;

    private VertxWebClient() {
    }

    public static IHttpClient create() {
        return INSTANCE;
    }

    public static void create(final Vertx vertx) {
        webClient = WebClient.create(vertx, getWebClientOptions());
    }

    private static WebClientOptions getWebClientOptions() {
        return new WebClientOptions(CONFIGURATION.getJsonObject("http_client_options"));
    }

    @Override
    public Single<HttpClientResponse> doExecute(final HttpRequest httpRequest) {
        io.vertx.reactivex.ext.web.client.HttpRequest<Buffer> request = webClient.requestAbs(httpRequest.getHttpMethod(), httpRequest.getResourceURL());
        final JsonObject _payload = httpRequest.getPayload();
        Buffer buffer = Buffer.buffer();
        if (null != _payload) {
            buffer = Buffer.buffer(_payload.toString());
            request.headers().add(HttpHeaders.CONTENT_LENGTH.toString(), buffer.length() + "");
        }
        final Map<String, String> _headers = httpRequest.getHeaders();
        if (null != _headers) {
            request.headers().addAll(getHeaders(_headers));
        }
        return request.rxSendBuffer(buffer).flatMap(this::wrapResponse);
    }

    private MultiMap getHeaders(final Map<String, String> headers) {
        final MultiMap _headers = MultiMap.caseInsensitiveMultiMap();
        headers.forEach((k, v) -> {
            _headers.add(k, v);
        });
        return _headers;
    }

    private Single<HttpClientResponse> wrapResponse(HttpResponse<Buffer> response) {
        return toBody(response)
                .doOnSuccess(this::trace)
                .map(buffer -> new HttpClientResponse(buffer, response.headers(), response.statusCode())
                );
    }

    private Single<Buffer> toBody(HttpResponse<Buffer> response) {
        if (response.body() != null) {
            return Single.just(response.body());
        } else {
            System.out.println("Empty body");
            return Single.just(Buffer.buffer());
        }
    }

    private void trace(Buffer results) {
        System.out.println(results.toString());
        //TODO: log trace here
    }
}
