/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.sql.implementation;

import com.microsoft.azure.management.sql.DatabaseEdition;
import com.microsoft.azure.management.sql.ServiceObjectiveName;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Import database parameters.
 */
public class ImportRequestInner extends ExportRequestInner {
    /**
     * The name of the database to import.
     */
    @JsonProperty(value = "databaseName", required = true)
    private String databaseName;

    /**
     * The edition for the database being created. Possible values include:
     * 'Web', 'Business', 'Basic', 'Standard', 'Premium', 'Free', 'Stretch',
     * 'DataWarehouse', 'System', 'System2'.
     */
    @JsonProperty(value = "edition", required = true)
    private DatabaseEdition edition;

    /**
     * The name of the service objective to assign to the database. Possible
     * values include: 'Basic', 'S0', 'S1', 'S2', 'S3', 'P1', 'P2', 'P3', 'P4',
     * 'P6', 'P11', 'P15', 'System', 'System2', 'ElasticPool'.
     */
    @JsonProperty(value = "serviceObjectiveName", required = true)
    private ServiceObjectiveName serviceObjectiveName;

    /**
     * The maximum size for the newly imported database.
     */
    @JsonProperty(value = "maxSizeBytes", required = true)
    private String maxSizeBytes;

    /**
     * Get the databaseName value.
     *
     * @return the databaseName value
     */
    public String databaseName() {
        return this.databaseName;
    }

    /**
     * Set the databaseName value.
     *
     * @param databaseName the databaseName value to set
     * @return the ImportRequestInner object itself.
     */
    public ImportRequestInner withDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    /**
     * Get the edition value.
     *
     * @return the edition value
     */
    public DatabaseEdition edition() {
        return this.edition;
    }

    /**
     * Set the edition value.
     *
     * @param edition the edition value to set
     * @return the ImportRequestInner object itself.
     */
    public ImportRequestInner withEdition(DatabaseEdition edition) {
        this.edition = edition;
        return this;
    }

    /**
     * Get the serviceObjectiveName value.
     *
     * @return the serviceObjectiveName value
     */
    public ServiceObjectiveName serviceObjectiveName() {
        return this.serviceObjectiveName;
    }

    /**
     * Set the serviceObjectiveName value.
     *
     * @param serviceObjectiveName the serviceObjectiveName value to set
     * @return the ImportRequestInner object itself.
     */
    public ImportRequestInner withServiceObjectiveName(ServiceObjectiveName serviceObjectiveName) {
        this.serviceObjectiveName = serviceObjectiveName;
        return this;
    }

    /**
     * Get the maxSizeBytes value.
     *
     * @return the maxSizeBytes value
     */
    public String maxSizeBytes() {
        return this.maxSizeBytes;
    }

    /**
     * Set the maxSizeBytes value.
     *
     * @param maxSizeBytes the maxSizeBytes value to set
     * @return the ImportRequestInner object itself.
     */
    public ImportRequestInner withMaxSizeBytes(String maxSizeBytes) {
        this.maxSizeBytes = maxSizeBytes;
        return this;
    }

}
