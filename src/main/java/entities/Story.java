/**
 * Copyright (c) 2021 Fundacion Jala.
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "created_at",
        "current_state",
        "description",
        "estimate",
        "id",
        "kind",
        "labels",
        "name",
        "owner_ids",
        "project_id",
        "requested_by_id",
        "story_type",
        "updated_at",
        "url"
})
public class Story {

    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("current_state")
    private String currentState;
    @JsonProperty("description")
    private String description;
    @JsonProperty("estimate")
    private Integer estimate;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("kind")
    private String kind;
    @JsonProperty("labels")
    private List<Object> labels = null;
    @JsonProperty("name")
    private String name;
    @JsonProperty("owner_ids")
    private List<Object> ownerIds = null;
    @JsonProperty("project_id")
    private Integer projectId;
    @JsonProperty("requested_by_id")
    private Integer requestedById;
    @JsonProperty("story_type")
    private String storyType;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("url")
    private String url;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("current_state")
    public String getCurrentState() {
        return currentState;
    }

    @JsonProperty("current_state")
    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("estimate")
    public Integer getEstimate() {
        return estimate;
    }

    @JsonProperty("estimate")
    public void setEstimate(Integer estimate) {
        this.estimate = estimate;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("kind")
    public String getKind() {
        return kind;
    }

    @JsonProperty("kind")
    public void setKind(String kind) {
        this.kind = kind;
    }

    @JsonProperty("labels")
    public List<Object> getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(List<Object> labels) {
        this.labels = labels;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("owner_ids")
    public List<Object> getOwnerIds() {
        return ownerIds;
    }

    @JsonProperty("owner_ids")
    public void setOwnerIds(List<Object> ownerIds) {
        this.ownerIds = ownerIds;
    }

    @JsonProperty("project_id")
    public Integer getProjectId() {
        return projectId;
    }

    @JsonProperty("project_id")
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @JsonProperty("requested_by_id")
    public Integer getRequestedById() {
        return requestedById;
    }

    @JsonProperty("requested_by_id")
    public void setRequestedById(Integer requestedById) {
        this.requestedById = requestedById;
    }

    @JsonProperty("story_type")
    public String getStoryType() {
        return storyType;
    }

    @JsonProperty("story_type")
    public void setStoryType(String storyType) {
        this.storyType = storyType;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
