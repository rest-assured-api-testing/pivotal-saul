/**
 *  Copyright (c) 2021 Fundacion Jala.
 *  This software is the confidential and proprietary information of Fundacion Jala
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Fundacion Jala
 */
package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "account_id",
        "atom_enabled",
        "automatic_planning",
        "bugs_and_chores_are_estimatable",
        "created_at",
        "current_iteration_number",
        "description",
        "enable_following",
        "enable_incoming_emails",
        "enable_tasks",
        "has_google_domain",
        "id",
        "initial_velocity",
        "iteration_length",
        "kind",
        "name",
        "number_of_done_iterations_to_show",
        "point_scale",
        "point_scale_is_custom",
        "profile_content",
        "project_type",
        "public",
        "start_date",
        "start_time",
        "time_zone",
        "updated_at",
        "velocity_averaged_over",
        "version",
        "week_start_day"
})
public class Project {

    @JsonProperty("account_id")
    private Integer accountId;
    @JsonProperty("atom_enabled")
    private Boolean atomEnabled;
    @JsonProperty("automatic_planning")
    private Boolean automaticPlanning;
    @JsonProperty("bugs_and_chores_are_estimatable")
    private Boolean bugsAndChoresAreEstimatable;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("current_iteration_number")
    private Integer currentIterationNumber;
    @JsonProperty("description")
    private String description;
    @JsonProperty("enable_following")
    private Boolean enableFollowing;
    @JsonProperty("enable_incoming_emails")
    private Boolean enableIncomingEmails;
    @JsonProperty("enable_tasks")
    private Boolean enableTasks;
    @JsonProperty("has_google_domain")
    private Boolean hasGoogleDomain;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("initial_velocity")
    private Integer initialVelocity;
    @JsonProperty("iteration_length")
    private Integer iterationLength;
    @JsonProperty("kind")
    private String kind;
    @JsonProperty("name")
    private String name;
    @JsonProperty("number_of_done_iterations_to_show")
    private Integer numberOfDoneIterationsToShow;
    @JsonProperty("point_scale")
    private String pointScale;
    @JsonProperty("point_scale_is_custom")
    private Boolean pointScaleIsCustom;
    @JsonProperty("profile_content")
    private String profileContent;
    @JsonProperty("project_type")
    private String projectType;
    @JsonProperty("public")
    private Boolean _public;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("time_zone")
    private TimeZone timeZone;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("velocity_averaged_over")
    private Integer velocityAveragedOver;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("week_start_day")
    private String weekStartDay;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("account_id")
    public Integer getAccountId() {
        return accountId;
    }

    @JsonProperty("account_id")
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("atom_enabled")
    public Boolean getAtomEnabled() {
        return atomEnabled;
    }

    @JsonProperty("atom_enabled")
    public void setAtomEnabled(Boolean atomEnabled) {
        this.atomEnabled = atomEnabled;
    }

    @JsonProperty("automatic_planning")
    public Boolean getAutomaticPlanning() {
        return automaticPlanning;
    }

    @JsonProperty("automatic_planning")
    public void setAutomaticPlanning(Boolean automaticPlanning) {
        this.automaticPlanning = automaticPlanning;
    }

    @JsonProperty("bugs_and_chores_are_estimatable")
    public Boolean getBugsAndChoresAreEstimatable() {
        return bugsAndChoresAreEstimatable;
    }

    @JsonProperty("bugs_and_chores_are_estimatable")
    public void setBugsAndChoresAreEstimatable(Boolean bugsAndChoresAreEstimatable) {
        this.bugsAndChoresAreEstimatable = bugsAndChoresAreEstimatable;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("current_iteration_number")
    public Integer getCurrentIterationNumber() {
        return currentIterationNumber;
    }

    @JsonProperty("current_iteration_number")
    public void setCurrentIterationNumber(Integer currentIterationNumber) {
        this.currentIterationNumber = currentIterationNumber;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("enable_following")
    public Boolean getEnableFollowing() {
        return enableFollowing;
    }

    @JsonProperty("enable_following")
    public void setEnableFollowing(Boolean enableFollowing) {
        this.enableFollowing = enableFollowing;
    }

    @JsonProperty("enable_incoming_emails")
    public Boolean getEnableIncomingEmails() {
        return enableIncomingEmails;
    }

    @JsonProperty("enable_incoming_emails")
    public void setEnableIncomingEmails(Boolean enableIncomingEmails) {
        this.enableIncomingEmails = enableIncomingEmails;
    }

    @JsonProperty("enable_tasks")
    public Boolean getEnableTasks() {
        return enableTasks;
    }

    @JsonProperty("enable_tasks")
    public void setEnableTasks(Boolean enableTasks) {
        this.enableTasks = enableTasks;
    }

    @JsonProperty("has_google_domain")
    public Boolean getHasGoogleDomain() {
        return hasGoogleDomain;
    }

    @JsonProperty("has_google_domain")
    public void setHasGoogleDomain(Boolean hasGoogleDomain) {
        this.hasGoogleDomain = hasGoogleDomain;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("initial_velocity")
    public Integer getInitialVelocity() {
        return initialVelocity;
    }

    @JsonProperty("initial_velocity")
    public void setInitialVelocity(Integer initialVelocity) {
        this.initialVelocity = initialVelocity;
    }

    @JsonProperty("iteration_length")
    public Integer getIterationLength() {
        return iterationLength;
    }

    @JsonProperty("iteration_length")
    public void setIterationLength(Integer iterationLength) {
        this.iterationLength = iterationLength;
    }

    @JsonProperty("kind")
    public String getKind() {
        return kind;
    }

    @JsonProperty("kind")
    public void setKind(String kind) {
        this.kind = kind;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("number_of_done_iterations_to_show")
    public Integer getNumberOfDoneIterationsToShow() {
        return numberOfDoneIterationsToShow;
    }

    @JsonProperty("number_of_done_iterations_to_show")
    public void setNumberOfDoneIterationsToShow(Integer numberOfDoneIterationsToShow) {
        this.numberOfDoneIterationsToShow = numberOfDoneIterationsToShow;
    }

    @JsonProperty("point_scale")
    public String getPointScale() {
        return pointScale;
    }

    @JsonProperty("point_scale")
    public void setPointScale(String pointScale) {
        this.pointScale = pointScale;
    }

    @JsonProperty("point_scale_is_custom")
    public Boolean getPointScaleIsCustom() {
        return pointScaleIsCustom;
    }

    @JsonProperty("point_scale_is_custom")
    public void setPointScaleIsCustom(Boolean pointScaleIsCustom) {
        this.pointScaleIsCustom = pointScaleIsCustom;
    }

    @JsonProperty("profile_content")
    public String getProfileContent() {
        return profileContent;
    }

    @JsonProperty("profile_content")
    public void setProfileContent(String profileContent) {
        this.profileContent = profileContent;
    }

    @JsonProperty("project_type")
    public String getProjectType() {
        return projectType;
    }

    @JsonProperty("project_type")
    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    @JsonProperty("public")
    public Boolean getPublic() {
        return _public;
    }

    @JsonProperty("public")
    public void setPublic(Boolean _public) {
        this._public = _public;
    }

    @JsonProperty("start_date")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("start_date")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("start_time")
    public String getStartTime() {
        return startTime;
    }

    @JsonProperty("start_time")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("time_zone")
    public TimeZone getTimeZone() {
        return timeZone;
    }

    @JsonProperty("time_zone")
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("velocity_averaged_over")
    public Integer getVelocityAveragedOver() {
        return velocityAveragedOver;
    }

    @JsonProperty("velocity_averaged_over")
    public void setVelocityAveragedOver(Integer velocityAveragedOver) {
        this.velocityAveragedOver = velocityAveragedOver;
    }

    @JsonProperty("version")
    public Integer getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Integer version) {
        this.version = version;
    }

    @JsonProperty("week_start_day")
    public String getWeekStartDay() {
        return weekStartDay;
    }

    @JsonProperty("week_start_day")
    public void setWeekStartDay(String weekStartDay) {
        this.weekStartDay = weekStartDay;
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
