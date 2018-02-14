/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.apigeneration.Beta;
import com.microsoft.azure.management.apigeneration.Fluent;

/**
 * Response containing the Azure SQL Database metric availability.
 */
@Fluent
@Beta(Beta.SinceVersion.V2_0_0)
public interface SqlDatabaseMetricAvailability {
    /**
     * @return the length of retention for the database metric
     */
    String retention();

    /**
     * @return the granularity of the database metric
     */
    String timeGrain();
}
