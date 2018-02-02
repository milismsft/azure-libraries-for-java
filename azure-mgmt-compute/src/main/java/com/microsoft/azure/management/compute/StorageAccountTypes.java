/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.compute;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for StorageAccountTypes.
 */
public enum StorageAccountTypes {
    /** Enum value Standard_LRS. */
    STANDARD_LRS("Standard_LRS"),

    /** Enum value Premium_LRS. */
    PREMIUM_LRS("Premium_LRS"),

    /** Enum value StandardSSD_LRS. */
    STANDARD_SSD_LRS("StandardSSD_LRS"),

    /** Enum value Standard_ZRS. */
    STANDARD_ZRS("Standard_ZRS");

    /** The actual serialized value for a StorageAccountTypes instance. */
    private String value;

    StorageAccountTypes(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a StorageAccountTypes instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed StorageAccountTypes object, or null if unable to parse.
     */
    @JsonCreator
    public static StorageAccountTypes fromString(String value) {
        StorageAccountTypes[] items = StorageAccountTypes.values();
        for (StorageAccountTypes item : items) {
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