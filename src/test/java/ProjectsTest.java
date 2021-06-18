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
import org.testng.Assert;
import org.testng.annotations.*;
import utils.PropertiesReader;
import java.io.IOException;

@SuppressWarnings("unchecked")
public class ProjectsTest {
    private static String token;
    private static String base_uri;
    private Project project;
    private ApiResponse apiResponse;
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

    @DataProvider(name = "projectTestProvider")
    public static Object[][] projectTestProvider() {
        return new Object[][]{
                {"test1234567890", "public", 1},
                {"projectTest a b c d", "private", 10},
                {"projectTest!#%$%^&*()", "public", 100},
                {"asdfghjklmasdfghjklmasdfghjklmasdfghjklmasdfghjklm", "private", 999}
        };
    }


    @Test(dataProvider = "projectTestProvider", groups = "createProject")
    public void createProjectShouldReturnProject(String name, String project_type, int velocity) {
        Project project = new Project();
        project.setProjectType(project_type);
        project.setName(name);
        project.setInitialVelocity(velocity);
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.CREATE_PROJECT)
                .setMethod(RequestMethod.POST)
                .setBody(project)
                .build();
        apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        apiResponse.validateBodySchema("schemas/project.json");
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
