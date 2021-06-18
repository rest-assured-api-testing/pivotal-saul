import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import constants.Endpoints;
import entities.Project;
import entities.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.PropertiesReader;

import java.io.IOException;

/**
 * Copyright (c) 2021 Fundacion Jala.
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */

@SuppressWarnings("unchecked")
public class StoriesTest {
    private static String token;
    private static String base_uri;
    private final int project_id = 2504532;
    private Story story;
    private ApiResponse apiResponse;
    @BeforeClass
    public static void token() throws IOException {
        token = PropertiesReader.readFileProperty("env.conf", "token");
        base_uri = PropertiesReader.readFileProperty("env.conf", "base_uri");
    }
    @BeforeMethod(onlyForGroups = "deleteStory")
    public void createProject() {
        Story story = new Story();
        story.setName("projectToTestStory");
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.CREATE_PROJECT)
                .setMethod(RequestMethod.POST)
                .addPathParam("project_id", String.valueOf(project_id))
                .setBody(story)
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        this.story = apiResponse.getBody(Story.class);
    }

    @Test
    public void getStoriesShouldReturnOk() {
        ApiRequest apiRequest = new ApiRequestBuilder()
                .setToken(token)
                .setBaseUri(base_uri)
                .setEndpoint(Endpoints.GET_PROJECT_STORIES)
                .setMethod(RequestMethod.GET)
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @DataProvider(name = "storyTestProvider")
    public static Object[][] storyTestProvider() {
        return new Object[][]{
                {"storyt1234567890", "story to test", "Feature"},
                {"storyTest a b c d", "asdfghjklmasdfghjklmasdfghjklm!@#$%^^", "Bug"},
                {"storyTest!#%$%^&*()", " ", "Chore"},
                {"storyhjklmasdfghjklmasdfghjklmasdfghjklmasdfghjklm", "123456789=_+", "Release"}
        };
    }


    @Test(dataProvider = "storyTestProvider")
    public void createStoryShouldReturnOk(String name, String description, String type) {
        Story story = new Story();
        story.setName(name);
        story.setDescription(description);
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
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        apiResponse.validateBodySchema("schemas/story.json");
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
}
