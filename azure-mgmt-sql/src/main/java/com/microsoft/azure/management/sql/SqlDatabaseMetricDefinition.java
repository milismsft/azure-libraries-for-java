/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.apigeneration.Beta;
import com.microsoft.azure.management.apigeneration.Fluent;

import java.util.List;

/**
 * Response containing the Azure SQL Database metric definition.
 */
@Fluent
@Beta(Beta.SinceVersion.V2_0_0)
public interface SqlDatabaseMetricDefinition {
    /**
     * @return the name of the metric
     */
    String name();

    /**
     * @return the primary aggregation type
     */
    PrimaryAggregationType primaryAggregationType();

    /**
     * @return the resource URI
     */
    String resourceUri();

    /**
     * @return the unit type
     */
    UnitDefinitionType unit();

    /**
     * @return the metric availabilities
     */
    List<SqlDatabaseMetricAvailability> metricAvailabilities();
}
