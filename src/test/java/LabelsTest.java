/**
 * Copyright (c) 2021 Fundacion Jala.
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
import api.*;
import constants.Endpoints;
import entities.Label;
import entities.Project;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.PropertiesReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LabelsTest {
    private String token;
    private String base_uri;
    private int project_id;
    private ApiResponse apiResponse;
    private List<Integer> createdStoriesIdList = new ArrayList<>();

    @BeforeClass
    public void setUp() throws IOException {
        token = PropertiesReader.readFileProperty("env.conf", "token");
        base_uri = PropertiesReader.readFileProperty("env.conf", "base_uri");
        createTestLabelsProject();
    }

    private void createTestLabelsProject() {
        Project project = new Project();
        project.setName("labelsProject");
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.CREATE_PROJECT)
                .setMethod(RequestMethod.POST)
                .setBody(project)
                .build();
        project_id = ApiManager.execute(apiRequest).getBody(Project.class).getId();
    }

    @DataProvider(name = "createLabelTestsProvider")
    public static Object[][] createLabelTestsProvider() {
        return new Object[][]{
                {"", 400},
                {" ", 400},
                {"labelExample!#$%^&(", 200},
                {"++^_&(?labelExample!#$%^&(", 200},
                {"50charsnamelabel7890123456789012345678901234567890", 200},
                {"51charsnamelabel78901234567890123456789012345678901", 400},
        };
    }

    @Test(dataProvider = "createLabelTestsProvider", groups = "createLabel")
    public void createLabelShouldReturnExpectedStatus(String name, int expectedStatus) {
        Label label = new Label();
        label.setName(name);
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.CREATE_LABEL)
                .setMethod(RequestMethod.POST)
                .addPathParam("project_id", String.valueOf(project_id))
                .setBody(label)
                .build();
        apiResponse = ApiManager.execute(apiRequest);
        int actual = apiResponse.getStatusCode();
        int expected = expectedStatus;
        Assert.assertEquals(actual, expected);
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
