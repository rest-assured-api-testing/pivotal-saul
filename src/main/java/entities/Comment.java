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
        "file_attachment_ids",
        "id",
        "kind",
        "person_id",
        "story_id",
        "text",
        "updated_at"
})
public class Comment {
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("file_attachment_ids")
    private List<Integer> fileAttachmentIds = null;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("kind")
    private String kind;
    @JsonProperty("person_id")
    private Integer personId;
    @JsonProperty("story_id")
    private Integer storyId;
    @JsonProperty("text")
    private String text;
    @JsonProperty("updated_at")
    private String updatedAt;
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

    @JsonProperty("file_attachment_ids")
    public List<Integer> getFileAttachmentIds() {
        return fileAttachmentIds;
    }

    @JsonProperty("file_attachment_ids")
    public void setFileAttachmentIds(List<Integer> fileAttachmentIds) {
        this.fileAttachmentIds = fileAttachmentIds;
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

    @JsonProperty("person_id")
    public Integer getPersonId() {
        return personId;
    }

    @JsonProperty("person_id")
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @JsonProperty("story_id")
    public Integer getStoryId() {
        return storyId;
    }

    @JsonProperty("story_id")
    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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
