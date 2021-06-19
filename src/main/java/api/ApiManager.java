/**
 * Copyright (c) 2021 Fundacion Jala.
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiManager {

    private static RequestSpecification buildRequest(ApiRequest apiRequest)
    {   if (apiRequest.getBody() != null) {
        return given()
                .headers(apiRequest.getHeaders())
                .queryParams(apiRequest.getQueryParams())
                .headers("X-TrackerToken","5f62ab12d0f6da95865c13157b80eed8")
                .pathParams(apiRequest.getPathParams())
                .baseUri(apiRequest.getBaseUri())
                .contentType(ContentType.JSON)
                //.auth().oauth2(apiRequest.getToken())
                .body(apiRequest.getBody())
                .log().all();
        } else {
            return given().headers(apiRequest.getHeaders())
                    .queryParams(apiRequest.getQueryParams())
                    .headers("X-TrackerToken","5f62ab12d0f6da95865c13157b80eed8")
                    .pathParams(apiRequest.getPathParams())
                    .baseUri(apiRequest.getBaseUri())
                    .contentType(ContentType.JSON)
                    //.auth().oauth2(apiRequest.getToken())
                    .log().all();
    }
    }
    public static ApiResponse execute(ApiRequest apiRequest){
        Response response = buildRequest(apiRequest)
                .request(apiRequest.getMethod().name()
                        ,apiRequest.getEndpoint());

        return new ApiResponse(response);
    }
}
