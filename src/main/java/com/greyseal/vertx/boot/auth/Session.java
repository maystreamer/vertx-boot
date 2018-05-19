package com.greyseal.vertx.boot.auth;

import io.vertx.core.json.JsonObject;

public abstract class Session {

    public abstract Session getSession();

    public abstract String getSessionToken();

    public abstract JsonObject toJson();
}
