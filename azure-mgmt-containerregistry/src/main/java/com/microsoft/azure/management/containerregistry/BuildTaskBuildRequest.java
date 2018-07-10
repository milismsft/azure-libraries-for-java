/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.containerregistry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.microsoft.azure.management.containerregistry.implementation.QueueBuildRequestInner;

/**
 * The queue build parameters based on a build task.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("BuildTask")
public class BuildTaskBuildRequest extends QueueBuildRequestInner {
    /**
     * The name of build task against which build has to be queued.
     */
    @JsonProperty(value = "buildTaskName", required = true)
    private String buildTaskName;

    /**
     * Get the buildTaskName value.
     *
     * @return the buildTaskName value
     */
    public String buildTaskName() {
        return this.buildTaskName;
    }

    /**
     * Set the buildTaskName value.
     *
     * @param buildTaskName the buildTaskName value to set
     * @return the BuildTaskBuildRequest object itself.
     */
    public BuildTaskBuildRequest withBuildTaskName(String buildTaskName) {
        this.buildTaskName = buildTaskName;
        return this;
    }

}
