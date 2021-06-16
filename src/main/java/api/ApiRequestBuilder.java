/**
 * Copyright (c) 2021 Fundacion Jala.
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package api;

import io.restassured.http.Header;
import io.restassured.http.Headers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiRequestBuilder {

    private ApiRequest apiRequest = new ApiRequest();
    private List<Header> headers;
    private Map<String, String> queryParams;
    private Map<String, String> pathParms;

    public ApiRequestBuilder() {
        headers = new ArrayList<>();
        queryParams = new HashMap<>();
        pathParms = new HashMap<>();
    }

    public ApiRequestBuilder setBaseUri(String baseUri) {
        apiRequest.setBaseUri(baseUri);
        return this;
    }

    public ApiRequestBuilder setEndpoint(String endpoint) {
        apiRequest.setEndpoint(endpoint);
        return this;
    }

    public ApiRequestBuilder setBody(String body) {
        apiRequest.setBody(body);
        return this;
    }

    public ApiRequestBuilder setToken(String token) {
        apiRequest.setToken(token);
        return this;
    }

    public ApiRequestBuilder setMethod(Enum<RequestMethod> method) {
        apiRequest.setMethod(method);
        return this;
    }

    public ApiRequestBuilder addHeader(final String header, final String value) {
        headers.add(new Header(header, value));
        return this;
    }

    public ApiRequestBuilder addQueryParam(final String param, final String value) {
        queryParams.put(param, value);
        return this;
    }

    public ApiRequestBuilder addPathParam(final String param, final String value) {
        pathParms.put(param, value);
        return this;
    }
    public ApiRequest build() {
        apiRequest.setHeaders(new Headers(headers));
        apiRequest.setQueryParams(queryParams);
        apiRequest.setPathParams(pathParms);
        return apiRequest;
    }
}
