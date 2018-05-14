package com.greyseal.vertx.boot.handler;

import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.RoutingContext;

public class AuthHandler extends BaseHandler {
    public AuthHandler(Vertx vertx) {
        super(vertx);
    }

    public static AuthHandler create(final Vertx vertx) {
        return new AuthHandler(vertx);
    }

    @Override
    public void handle(RoutingContext event) {
        System.out.println("AuthHandler called");
        event.next();
    }
}