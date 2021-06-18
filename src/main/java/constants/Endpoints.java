/**
 * Copyright (c) 2021 Fundacion Jala.
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package constants;

public class Endpoints {

    public static final String GET_ACCOUNTS = "/account_summaries";
    public static final String GET_ACCOUNT = "/accounts/{account_id}";
    public static final String GET_PROJECTS = "/projects";
    public static final String GET_PROJECT = "/projects/{project_id}";
    public static final String CREATE_PROJECT = "/projects";
    public static final String DELETE_PROJECT = "/projects/{project_id}";
    public static final String GET_PROJECT_ITERATIONS = "/projects/{project_id}/iterations";
    public static final String GET_PROJECT_ITERATION = "/projects/{project_id}/iterations/{iteration_number}";
    public static final String UPDATE_PROJECT_ITERATION_OVERRIDES = "projects/{project_id}/iteration_overrides/{iteration_number}";
    public static final String ADD_USER_TO_PROJECT = "/projects/{project_id}/memberships";
    public static final String GET_MEMBERS_OF_PROJECT = "/projects/{project_id}/memberships";
    public static final String GET_PROJECT_LABELS = "/projects/{project_id}/labels";
    public static final String CREATE_PROJECT_LABEL = "/projects/{project_id}/labels";
    public static final String GET_PROJECT_STORIES = "/projects/{project_id}/stories";
    public static final String CREATE_STORY = "/projects/{project_id}/stories";
}
