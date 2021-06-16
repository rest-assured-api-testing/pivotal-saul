/**
 * Copyright (c) 2021 Fundacion Jala.
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package entities;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "kind",
        "id",
        "created_at",
        "updated_at",
        "story_type",
        "name",
        "current_state",
        "requested_by_id",
        "url",
        "project_id",
        "owner_ids",
        "labels"
})
public class Story {
    private String kind;
    private String id;
    private String created_at;
    private String updated_at;
    private String story_type;
    private String name;
    private String current_state;
    private int requested_by_id;
    private String url;
    private List<String> owner_ids;
    private List<String> labels;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getStory_type() {
        return story_type;
    }

    public void setStory_type(String story_type) {
        this.story_type = story_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrent_state() {
        return current_state;
    }

    public void setCurrent_state(String current_state) {
        this.current_state = current_state;
    }

    public int getRequested_by_id() {
        return requested_by_id;
    }

    public void setRequested_by_id(int requested_by_id) {
        this.requested_by_id = requested_by_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getOwner_ids() {
        return owner_ids;
    }

    public void setOwner_ids(List<String> owner_ids) {
        this.owner_ids = owner_ids;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}
