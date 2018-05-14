package com.greyseal.vertx.boot.handler;

import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.RoutingContext;

public class ErrorHandler extends BaseHandler {
    private static Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    public ErrorHandler(Vertx vertx) {
        super(vertx);
    }

    public static ErrorHandler create(final Vertx vertx) {
        return new ErrorHandler(vertx);
    }

    @Override
    public void handle(RoutingContext event) {
        System.out.println("ErrorHandler called");
        event.response().end(new JsonObject().put("error", event.failure().getMessage()).toString());
    }
}