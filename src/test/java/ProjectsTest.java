import api.*;
import constants.Endpoints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.PropertiesReader;
import java.io.IOException;

/**
 * Copyright (c) 2021 Fundacion Jala.
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */

public class ProjectsTest {
    private static String token;
    private static String base_uri;
    @BeforeAll
    public static void token() throws IOException{
        token = PropertiesReader.readFileProperty("env.conf", "token");
        base_uri = PropertiesReader.readFileProperty("env.conf", "base_uri");
    }

    @Test
    public void getAccountsShouldReturnOk() {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.GET_ACCOUNTS)
                .setMethod(RequestMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assertions.assertEquals(200, apiResponse.getStatusCode());
    }

    @Test
    public void getProjectsShouldReturnOk() {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.GET_PROJECTS)
                .setMethod(RequestMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assertions.assertEquals(200, apiResponse.getStatusCode());
    }

    @Test
    public void getProjectStoriesShouldReturnOk() {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.GET_PROJECT_STORIES)
                .addPathParam("project_id", "2504532")
                .setMethod(RequestMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assertions.assertEquals(200, apiResponse.getStatusCode());
    }
}
