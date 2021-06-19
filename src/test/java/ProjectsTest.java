/**
 * Copyright (c) 2021 Fundacion Jala.
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
import api.*;
import constants.Endpoints;
import entities.Project;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.PropertiesReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class ProjectsTest {
    private static String token;
    private static String base_uri;
    private Project projectToDelete;
    private ApiResponse apiResponse;
    private List<Integer> createdProjectsList = new ArrayList<>();

    @BeforeClass
    public static void token() throws IOException{
        token = PropertiesReader.readFileProperty("env.conf", "token");
        base_uri = PropertiesReader.readFileProperty("env.conf", "base_uri");
    }

    @BeforeMethod(onlyForGroups = "deleteProject")
    public void createProjectToDelete() {
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
        this.projectToDelete = apiResponse.getBody(Project.class);
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
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
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
                {"test1234567890", "public", 1, 3, 200},
                {"test a b c d", "private", 1, 3, 200},
                {"test!#%$%^&*()", "public", 1, 3, 200},
                {"asdfghjklmasdfghjklmasdfghjklmasdfghjklmasdfghjklm", "private", 1, 3, 200},
                {"asdfghjklmasdfghjklmasdfghjklmasdfghjklmasdfghjklma", "private", 1, 3, 400},
                {"", "public", 1, 3, 400},
                {" ", "public", 1, 3, 400},
                {"test1", "public", -1, 1, 400},
                {"test2", "public", 0, 3, 400},
                {"test3", "public", 1, 3, 200},
                {"test4", "public", 999, 3, 200},
                {"test5", "public", 1000, 3, 400},
                {"test6", "public", 1, 0, 400},
                {"test7", "public", 1, -1, 400},
                {"test8", "public", 1, 3, 200},
                {"test9", "public", 1, 999, 200},
                {"test0", "public", 1, 1000, 400},
        };
    }

    @DataProvider(name = "velocityAveragedOverProjectProvider")
    public static Object[][] velocityAveragedOverProjectProvider() {
        return new Object[][]{
                {"testAvg1", -1, 400},
                {"testAvg2", 0, 400},
                {"testAvg3", 1, 200},
                {"testAvg4", 999, 200},
                {"testAvg5", 1000, 400},
        };
    }

    @Test(dataProvider = "projectTestProvider", groups = "createProject")
    public void createProjectShouldReturnExpectedStatus(String name, String project_type, int initialVelocity, int numberOfIterationsToShow,int expectedStatus) {
        Project project = new Project();
        project.setProjectType(project_type);
        project.setName(name);
        project.setInitialVelocity(initialVelocity);
        project.setNumberOfDoneIterationsToShow(numberOfIterationsToShow);
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.CREATE_PROJECT)
                .setMethod(RequestMethod.POST)
                .setBody(project)
                .build();
        apiResponse = ApiManager.execute(apiRequest);
        int actual = apiResponse.getStatusCode();
        storeCreatedElementId(apiResponse);
        int expected = expectedStatus;
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "velocityAveragedOverProjectProvider")
    public void createProjectWithVelocityAveragedValue(String name, int velocityAveragedOver, int expectedStatus) {
        Project project = new Project();
        project.setName("testVelocityAveragedValue");
        project.setVelocityAveragedOver(velocityAveragedOver);
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.CREATE_PROJECT)
                .setMethod(RequestMethod.POST)
                .setBody(project)
                .build();
        apiResponse = ApiManager.execute(apiRequest);
        int actual = apiResponse.getStatusCode();
        storeCreatedElementId(apiResponse);
        int expected = expectedStatus;
        Assert.assertEquals(actual, expected);
    }

    @Test()
    public void deleteNonExistentProjectShouldReturn403() {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.DELETE_PROJECT)
                .setMethod(RequestMethod.DELETE)
                .addPathParam("project_id", String.valueOf(1234))
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_FORBIDDEN);
    }

    @Test(groups = "deleteProject")
    public void deleteProjectShouldReturn204() {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.DELETE_PROJECT)
                .setMethod(RequestMethod.DELETE)
                .addPathParam("project_id", projectToDelete.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }

    private void storeCreatedElementId(ApiResponse apiResponse) {
        if (apiResponse.getStatusCode() == HttpStatus.SC_OK) {
            createdProjectsList.add(apiResponse.getBody(Project.class).getId());
        }
    }

    @AfterClass
    public void deleteCreatedProjects() {
        for (int id:createdProjectsList) {
            ApiRequest apiRequest = new ApiRequestBuilder()
                    .setToken(token)
                    .setBaseUri(base_uri)
                    .setEndpoint(Endpoints.DELETE_PROJECT)
                    .setMethod(RequestMethod.DELETE)
                    .addPathParam("project_id", String.valueOf(id))
                    .build();
            ApiManager.execute(apiRequest);
        }
    }
}
