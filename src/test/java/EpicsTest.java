/**
 * Copyright (c) 2021 Fundacion Jala.
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
import api.*;
import constants.Endpoints;
import entities.Epic;
import entities.Label;
import entities.Project;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.PropertiesReader;
import java.io.IOException;
import static utils.BasicData.*;

@SuppressWarnings("unchecked")
public class EpicsTest {

    private String token;
    private String base_uri;
    private int project_id;
    private Epic epicTest;
    private ApiResponse apiResponse;
    private int label_id;

    @BeforeClass
    public void setUp() throws IOException {
        token = PropertiesReader.readFileProperty("env.conf", "token");
        base_uri = PropertiesReader.readFileProperty("env.conf", "base_uri");
        createTestEpicsProject();
    }

    private void createTestEpicsProject() {
        Project project = new Project();
        project.setName("testEpicsProject");
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.CREATE_PROJECT)
                .setMethod(RequestMethod.POST)
                .setBody(project)
                .build();
        project_id = ApiManager.execute(apiRequest).getBody(Project.class).getId();
    }

    @BeforeMethod(onlyForGroups = "deleteEpic")
    public void createEpicToDelete() {
        Epic epic = new Epic();
        epic.setName("epictest");
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.CREATE_EPIC)
                .setMethod(RequestMethod.POST)
                .setBody(epic)
                .addPathParam("project_id", String.valueOf(project_id))
                .build();
        apiResponse = ApiManager.execute(apiRequest);
        this.epicTest = apiResponse.getBody(Epic.class);
    }

    @Test(groups = "getEpic")
    public void getEpicsShouldReturnOK() {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.GET_EPICS)
                .setMethod(RequestMethod.GET)
                .addPathParam("project_id", String.valueOf(project_id))
                .build();
        int actual = ApiManager.execute(apiRequest).getStatusCode();
        Assert.assertEquals(actual, HttpStatus.SC_OK);
    }

    @DataProvider(name = "createEpicTestsProvider")
    public static Object[][] createEpicTestsProvider() {
        return new Object[][]{
                {"", "description", "labelname1", 400},
                {" ", "description", "labelname2", 400},
                {"testEpic1", "description", "labelname3", 200},
                {specialchars, "description", "labelname4", 200},
                {text50charslong, "description", "labelname5", 200},
                {text51charslong, "description", "labelname6", 400},
                {"testEpicDescription1", "", "labelname7", 200},
                {"testEpicDescription2", " ", "labelname8", 200},
                {"testEpicDescription3", "testdescription", "labelname9", 200},
                {"testEpicDescription4", specialchars, "labelname10", 200},
                {"testEpicDescription5", text250charslong, "labelname11", 200},
                {"testEpicDescription6", text250charslong, "labelname12", 400},
                {"testEpicDescription7", text250charslong, "labelname12", 400},
                {"testEpicLabel1", text250charslong, "", 400},
                {"testEpicLabel1", text250charslong, " ", 400},
                {"testEpicLabel1", text250charslong, text50charslong, 200},
                {"testEpicLabel1", text250charslong, text51charslong, 400}
        };
    }

    @Test(dataProvider = "createEpicTestsProvider", groups = "createEpic")
    public void createEpicShouldReturnExpectedStatus(String name, String description, String labelName, int expectedStatus) {
        Label label = new Label();
        label.setName(labelName);
        Epic epic = new Epic();
        epic.setName(name);
        epic.setDescription(description);
        epic.setLabel(label);
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.CREATE_EPIC)
                .setMethod(RequestMethod.POST)
                .addPathParam("project_id", String.valueOf(project_id))
                .setBody(epic)
                .build();
        apiResponse = ApiManager.execute(apiRequest);
        int actual = apiResponse.getStatusCode();
        int expected = expectedStatus;
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "deleteEpic")
    public void deleteEpicShouldReturn204() {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.DELETE_EPIC)
                .setMethod(RequestMethod.DELETE)
                .addPathParam("project_id", String.valueOf(project_id))
                .addPathParam("epic_id", epicTest.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }

    @Test(groups = "deleteEpic")
    public void deleteEpicWithNoPathParamShouldReturn404() {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.DELETE_EPIC)
                .setMethod(RequestMethod.DELETE)
                .addPathParam("project_id", String.valueOf(project_id))
                .addPathParam("epic_id", "")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

    @Test(groups = "deleteEpic")
    public void deleteEpicWithWithInvalidIdShouldReturn400() {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.DELETE_EPIC)
                .setMethod(RequestMethod.DELETE)
                .addPathParam("project_id", String.valueOf(project_id))
                .addPathParam("epic_id", "12345")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @AfterClass
    private void deleteTestProject() {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.DELETE_PROJECT)
                .setMethod(RequestMethod.DELETE)
                .addPathParam("project_id", String.valueOf(project_id))
                .build();
        ApiManager.execute(apiRequest);
    }
}
