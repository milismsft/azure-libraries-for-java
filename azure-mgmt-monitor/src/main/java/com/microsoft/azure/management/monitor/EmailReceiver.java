/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.microsoft.azure.management.monitor;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An email receiver.
 */
public class EmailReceiver {
    /**
     * The name of the email receiver. Names must be unique across all
     * receivers within an action group.
     */
    @JsonProperty(value = "name", required = true)
    private String name;

    /**
     * The email address of this receiver.
     */
    @JsonProperty(value = "emailAddress", required = true)
    private String emailAddress;

    /**
     * The receiver status of the e-mail. Possible values include:
     * 'NotSpecified', 'Enabled', 'Disabled'.
     */
    @JsonProperty(value = "status", access = JsonProperty.Access.WRITE_ONLY)
    private ReceiverStatus status;

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
     * @return the EmailReceiver object itself.
     */
    public EmailReceiver withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the emailAddress value.
     *
     * @return the emailAddress value
     */
    public String emailAddress() {
        return this.emailAddress;
    }

    /**
     * Set the emailAddress value.
     *
     * @param emailAddress the emailAddress value to set
     * @return the EmailReceiver object itself.
     */
    public EmailReceiver withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    /**
     * Get the status value.
     *
     * @return the status value
     */
    public ReceiverStatus status() {
        return this.status;
    }

}
