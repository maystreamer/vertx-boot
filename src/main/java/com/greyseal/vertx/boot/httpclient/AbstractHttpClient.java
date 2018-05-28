package com.greyseal.vertx.boot.httpclient;

import com.greyseal.vertx.boot.helper.ConfigHelper;
import io.vertx.core.json.JsonObject;

public abstract class AbstractHttpClient implements IHttpClient {
    protected static final JsonObject CONFIGURATION = ConfigHelper.loadConfigurationByEnvironment();
}
