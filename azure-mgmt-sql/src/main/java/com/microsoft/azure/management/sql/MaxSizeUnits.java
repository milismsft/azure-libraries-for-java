/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.sql;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for MaxSizeUnits.
 */
public enum MaxSizeUnits {
    /** Enum value Megabytes. */
    MEGABYTES("Megabytes"),

    /** Enum value Gigabytes. */
    GIGABYTES("Gigabytes"),

    /** Enum value Terabytes. */
    TERABYTES("Terabytes"),

    /** Enum value Petabytes. */
    PETABYTES("Petabytes");

    /** The actual serialized value for a MaxSizeUnits instance. */
    private String value;

    MaxSizeUnits(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a MaxSizeUnits instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed MaxSizeUnits object, or null if unable to parse.
     */
    @JsonCreator
    public static MaxSizeUnits fromString(String value) {
        MaxSizeUnits[] items = MaxSizeUnits.values();
        for (MaxSizeUnits item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
