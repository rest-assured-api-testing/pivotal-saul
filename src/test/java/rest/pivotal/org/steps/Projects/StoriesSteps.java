/**
 * Copyright (c) 2021 Fundacion Jala.
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package rest.pivotal.org.steps.Projects;

import api.*;
import constants.Endpoints;
import entities.Project;
import entities.Story;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import utils.PropertiesReader;
import java.io.IOException;

public class StoriesSteps {
    private String token;
    private String base_uri;
    private ApiRequestBuilder apiRequestBuilder = new ApiRequestBuilder();
    private ApiResponse apiResponse;
    private Story story = new Story();
    private final int project_id = 2504532;
    private int created_story_id;

    @Before
    public void readProperties() throws IOException {
        token = PropertiesReader.readFileProperty("env.conf", "token");
        base_uri = PropertiesReader.readFileProperty("env.conf", "base_uri");
    }

    @BeforeMethod(groups = "delete story")
    public void createStoryToDelete() {
        Story storyTemp = new Story();
        storyTemp.setName("Task List");
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setBaseUri(base_uri)
                .setToken(token)
                .setEndpoint(Endpoints.CREATE_STORY)
                .setMethod(RequestMethod.POST)
                .addQueryParam("project_id", String.valueOf(project_id))
                .setBody(storyTemp)
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        this.story = apiResponse.getBody(Story.class);
    }
    @BeforeMethod(onlyForGroups = "createStory")
    public void createProject() {
        Story story = new Story();
        story.setName("storytest");
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.CREATE_PROJECT)
                .setMethod(RequestMethod.POST)
                .addQueryParam("project_id", String.valueOf(project_id))
                .setBody(story)
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        this.story = apiResponse.getBody(Story.class);
    }
    @Given("I build {string} request")
    public void iBuildRequest(String method) {
        apiRequestBuilder.setBaseUri(base_uri)
                .setToken(token)
                .setMethod(RequestMethod.valueOf(method));
    }

    @And("I set story {string} name")
    public void iSetProjectName(String name) {
        story.setName(name);
    }

    @And("I set story {string} description")
    public void iSetProjectDescription(String description) {
        story.setDescription(description);
    }

    @And("I set story {string} type")
    public void iSetProjectType(String type) {
        story.setStoryType(type);
    }

    @When("I execute {string} request")
    public void iExecuteRequest(String endpoint) {
        apiRequestBuilder.setBody(story);
        ApiRequest apiRequest = apiRequestBuilder
                .setEndpoint(endpoint)
                .build();
        apiResponse = ApiManager.execute(apiRequest);
    }

    @Then("the response status code should be {string}")
    public void theResponseStatusCodeShouldBe(String statusCode) {
        created_story_id = apiResponse.getBody(Project.class).getId();
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        apiResponse.getResponse().then().log().body();
    }

    @After
    public void deleteCreatedProject() {
        System.out.println(created_story_id);
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setBaseUri(base_uri)
                .setToken(token)
                .setEndpoint(Endpoints.DELETE_PROJECT)
                .setMethod(RequestMethod.DELETE)
                .addPathParam("story_id", String.valueOf(created_story_id))
                .build();
        ApiManager.execute(apiRequest);
    }
}
