/**
 * Copyright (c) 2021 Fundacion Jala.
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import constants.Endpoints;
import entities.Project;
import io.cucumber.java.Before;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.PropertiesReader;

import javax.swing.*;
import java.io.IOException;

@SuppressWarnings("unchecked")
public class ProjectsTest {
    private static String token;
    private static String base_uri;
    private Project project;
    @BeforeClass
    public static void token() throws IOException{
        token = PropertiesReader.readFileProperty("env.conf", "token");
        base_uri = PropertiesReader.readFileProperty("env.conf", "base_uri");
    }
    @BeforeMethod(onlyForGroups = "deleteProject")
    public void createProject() {
        Project project = new Project();
        project.setName("projectToDelete");
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.CREATE_PROJECT)
                .setMethod(RequestMethod.POST)
                .setBody(project)
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        this.project = apiResponse.getBody(Project.class);
    }
    @Test
    public void getAccountsShouldReturnOk() {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.GET_ACCOUNTS)
                .setMethod(RequestMethod.GET)
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test
    public void getProjectsShouldReturnOk() {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.GET_PROJECTS)
                .setMethod(RequestMethod.GET)
                .build();

        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
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

        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test
    public void createProjectShouldReturnProject() throws JsonProcessingException {
        Project project = new Project();
        project.setProjectType("public");
        project.setName("test project7");
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.CREATE_PROJECT)
                .setMethod(RequestMethod.POST)
                .setBody(project)
                .build();

        ApiResponse apiResponse =ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test(groups = "deleteProject")
    public void deleteProjectShouldReturn204() throws JsonProcessingException {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.DELETE_PROJECT)
                .setMethod(RequestMethod.DELETE)
                .addPathParam("project_id", this.project.getId().toString())
                .build();

        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals( apiResponse.getStatusCode(), 204);
    }
}
