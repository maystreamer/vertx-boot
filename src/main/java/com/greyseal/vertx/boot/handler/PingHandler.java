package com.greyseal.vertx.boot.handler;

import com.greyseal.vertx.boot.Constant.MediaType;
import com.greyseal.vertx.boot.annotation.Protected;
import com.greyseal.vertx.boot.annotation.RequestMapping;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.buffer.Buffer;
import io.vertx.reactivex.ext.web.RoutingContext;

@RequestMapping(path = "/ping")
public class PingHandler extends BaseHandler {

    public PingHandler(Vertx vertx) {
        super(vertx);
    }

    @Override
    @Protected
    @RequestMapping(method = HttpMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void handle(RoutingContext ctx) {
        try {
            JsonObject result;
            result = new JsonObject().put("status", "OK");
            ctx.setBody(Buffer.buffer(result.toString()));
            ctx.response().putHeader("Custom", "header");
            ctx.next();
        } catch (Exception ex) {
            ctx.fail(ex);
        }
    }
}