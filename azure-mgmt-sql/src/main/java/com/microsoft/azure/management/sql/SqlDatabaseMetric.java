/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.apigeneration.Beta;
import com.microsoft.azure.management.apigeneration.Fluent;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Response containing the Azure SQL Database metric.
 */
@Fluent
@Beta(Beta.SinceVersion.V2_0_0)
public interface SqlDatabaseMetric {
    /**
     * @return the metric name
     */
    MetricName name();

    /**
     * @return the start time
     */
    DateTime startTime();

    /**
     * @return the end time
     */
    DateTime endTime();

    /**
     * @return the time grain
     */
    String timeGrain();

    /**
     * @return the metric's unit type
     */
    UnitType unit();

    /**
     * @return the metric values
     */
    List<MetricValue> metricValues();
}