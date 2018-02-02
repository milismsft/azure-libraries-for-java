/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.datalake.store.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Lake Store account name availability check parameters.
 */
public class CheckNameAvailabilityParameters {
    /**
     * the Data Lake Store name to check availability for.
     */
    @JsonProperty(value = "name", required = true)
    private String name;

    /**
     * the Resource type. Note: This should not be set by the user, as the
     * constant value is Microsoft.DataLakeStore/accounts.
     */
    @JsonProperty(value = "type", required = true)
    private String type;

    /**
     * Creates an instance of CheckNameAvailabilityParameters class.
     */
    public CheckNameAvailabilityParameters() {
        type = "Microsoft.DataLakeStore/accounts";
    }

    /**
     * Get the name value.
     *
     * @return the name value
     */
    public String name() {
        return this.name;
    }

    /**
     * Set the name value.
     *
     * @param name the name value to set
     * @return the CheckNameAvailabilityParameters object itself.
     */
    public CheckNameAvailabilityParameters withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the type value.
     *
     * @return the type value
     */
    public String type() {
        return this.type;
    }

    /**
     * Set the type value.
     *
     * @param type the type value to set
     * @return the CheckNameAvailabilityParameters object itself.
     */
    public CheckNameAvailabilityParameters withType(String type) {
        this.type = type;
        return this;
    }

}
