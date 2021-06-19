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

public class ProjectSteps {
    private String token;
    private String base_uri;
    private ApiRequestBuilder apiRequestBuilder = new ApiRequestBuilder();
    private ApiResponse apiResponse;
    private Project project = new Project();
    private int created_project_id;

    @Before
    public void readProperties() throws IOException {
        token = PropertiesReader.readFileProperty("env.conf", "token");
        base_uri = PropertiesReader.readFileProperty("env.conf", "base_uri");
    }
    @BeforeMethod(groups = "delete project")
    public void createProjectToDelete() {
        Project projectTemp = new Project();
        projectTemp.setName("Task List");
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setBaseUri(base_uri)
                .setToken(token)
                .setEndpoint(Endpoints.CREATE_PROJECT)
                .setMethod(RequestMethod.POST)
                .setBody(projectTemp)
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        this.project = apiResponse.getBody(Project.class);
    }
    @Given("I build {string} request")
    public void iBuildRequest(String method) {
        apiRequestBuilder.setBaseUri(base_uri)
                .setToken(token)
                .setMethod(RequestMethod.valueOf(method));
    }
    @And("I set project {string} name")
    public void iSetProjectName(String name) {
        project.setName(name);
    }
    @And("I set project {string} description")
    public void iSetProjectDescription(String description) {
        project.setDescription(description);
    }
    @And("I set project {string} type")
    public void iSetProjectType(String type) {
        project.setProjectType(type);
    }
    @And("I set {string} enable")
    public void iSetIfItEnableTasks(String enables_tasks) {
        boolean enables = enables_tasks.equals("true");
        project.setEnableTasks(enables);
    }
    @When("I execute {string} request")
    public void iExecuteRequest(String endpoint) {
        apiRequestBuilder.setBody(project);
        ApiRequest apiRequest = apiRequestBuilder
                .setEndpoint(endpoint)
                .build();
        apiResponse = ApiManager.execute(apiRequest);
    }
    @Then("the response status code should be {string}")
    public void theResponseStatusCodeShouldBe(String statusCode) {
        created_project_id = apiResponse.getBody(Project.class).getId();
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        apiResponse.getResponse().then().log().body();
    }
    @After
    public void deleteCreatedProject() {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setBaseUri(base_uri)
                .setToken(token)
                .setEndpoint(Endpoints.DELETE_PROJECT)
                .setMethod(RequestMethod.DELETE)
                .addPathParam("project_id", String.valueOf(created_project_id))
                .build();
        ApiManager.execute(apiRequest);
    }
}
