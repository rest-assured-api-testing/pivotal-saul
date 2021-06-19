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
import entities.Account;
import entities.Project;
import entities.Story;
import io.cucumber.java.Before;
import io.cucumber.java.bs.A;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.PropertiesReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class StoriesTest {
    private String token;
    private String base_uri;
    private int user_id;
    private int project_id;
    private Story story;
    private ApiResponse apiResponse;
    private List<Integer> createdStoriesIdList = new ArrayList<>();

    @BeforeClass
    public void setUp() throws IOException {
        token = PropertiesReader.readFileProperty("env.conf", "token");
        base_uri = PropertiesReader.readFileProperty("env.conf", "base_uri");
        setUserId();
        createTestStoryProject();
    }

    private void setUserId() {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.GET_USER_INFO)
                .setMethod(RequestMethod.GET)
                .setBody(story)
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        user_id = ApiManager.execute(apiRequest).getBody(Account.class).getId();
        this.story = apiResponse.getBody(Story.class);
    }

    private void createTestStoryProject() {
        Project project = new Project();
        project.setName("storiesProject");
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.CREATE_PROJECT)
                .setMethod(RequestMethod.POST)
                .setBody(project)
                .build();
        project_id = ApiManager.execute(apiRequest).getBody(Project.class).getId();
        System.out.println("PROJCET_ID\n\n\n" + project_id);
    }
    @BeforeMethod(onlyForGroups = "deleteStory")
    public void createStory() {
        Story story = new Story();
        story.setName("storyToDelete");
        story.setRequestedById(user_id);
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.CREATE_STORY)
                .setMethod(RequestMethod.POST)
                .addPathParam("project_id", String.valueOf(project_id))
                .setBody(story)
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        this.story = apiResponse.getBody(Story.class);
        System.out.println("ID\n\n\n\n" + story.getId());
    }

    @Test
    public void getStoriesOfAProjectShouldReturnOk() {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.GET_PROJECT_STORIES)
                .setMethod(RequestMethod.GET)
                .addPathParam("project_id", String.valueOf(project_id))
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @DataProvider(name = "storyTestProvider")
    public static Object[][] storyTestProvider() {
        return new Object[][]{
                {"1234567890", "description", 0, "feature", 200},
                {"", "description", 0, "feature", 400},
                {" ", "description", 0, "feature", 400},
                {"storywith50charslong123456789012345678901234567890", "description", 0, "feature", 200},
                {"storywith51charslong1234567890123456789012345678901", "description", 0, "feature", 400},
                {"storywithdescription1", "", 0, "feature", 200},
                {"storywithdescription2", " ", 0, "feature", 200},
                {"storywithdescription3", "description", 0, "feature", 200},
                {"storywithdescription4", "description250charslong45678901234567890123456789012345678901234567890123456789012345678901234567890" +
                        "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                        "12345678901234567890123456789012345678901234567890", 0, "feature", 200},
                {"storywithdescription5", "description251charslong45678901234567890123456789012345678901234567890123456789012345678901234567890" +
                        "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901" +
                        "12345678901234567890123456789012345678901234567890", 0, "feature", 400},
                {"story_estimate-1", "description", -1, "Feature", 400},
                {"story_estimate0", "description", 0, "Feature", 200},
                {"story_estimate1", "description", 1, "Feature", 200},
                {"story_estimate2", "description", 2, "Feature", 200},
                {"story_estimate3", "description", 3, "Feature", 200},
                {"story_estimate4", "description", 4, "Feature", 400},
                {"story_type1", "description", 0, "feature", 200},
                {"story_type2", "description", 0, "bug", 200},
                {"story_type3", "description", 0, "chore", 200},
                {"story_type4", "description", 0, "release", 200},
                {"story_type5", "description", 0, "Feature", 400},
                {"story_type6", "description", 0, "", 400},
        };
    }

    @Test(dataProvider = "storyTestProvider")
    public void createStoryShouldReturnOk(String name, String description, int estimate, String type, int expectedStatus) {
        Story story = new Story();
        story.setName(name);
        story.setDescription(description);
        story.setEstimate(estimate);
        story.setStoryType(type);
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.CREATE_STORY)
                .setMethod(RequestMethod.POST)
                .addPathParam("project_id", String.valueOf(project_id))
                .setBody(story)
                .build();
        apiResponse = ApiManager.execute(apiRequest);
        storeCreatedElementId(apiResponse);
        int actual = apiResponse.getStatusCode();
        int expected = expectedStatus;
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "deleteStory")
    public void deleteStoryShouldReturn204() throws JsonProcessingException {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.DELETE_STORY)
                .setMethod(RequestMethod.DELETE)
                .addPathParam("project_id", String.valueOf(project_id) )
                .addPathParam("story_id", this.story.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals( apiResponse.getStatusCode(), 204);
    }

    private void storeCreatedElementId(ApiResponse apiResponse) {
        if (apiResponse.getStatusCode() == HttpStatus.SC_OK) {
            createdStoriesIdList.add(apiResponse.getBody(Story.class).getId());
        }
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
//    private void deleteCreatedStories() {
//        for (int id:createdStoriesIdList) {
//            ApiRequest apiRequest = new ApiRequestBuilder()
//                    .setToken(token)
//                    .setBaseUri(base_uri)
//                    .setEndpoint(Endpoints.DELETE_STORY)
//                    .setMethod(RequestMethod.DELETE)
//                    .addPathParam("story_id", String.valueOf(id))
//                    .build();
//            ApiManager.execute(apiRequest);
//        }
//    }
}
