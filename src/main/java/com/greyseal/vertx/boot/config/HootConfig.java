package com.greyseal.vertx.hoot.config;

import io.vertx.core.json.JsonObject;

public enum HootConfig {
    INSTANCE;
    private JsonObject config;

    public JsonObject getConfig() {
        return config;
    }

    public void setConfig(final JsonObject config) {
        this.config = config;
    }
}