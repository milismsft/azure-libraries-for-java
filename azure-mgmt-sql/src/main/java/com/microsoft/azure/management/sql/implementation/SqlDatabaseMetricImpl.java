/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.management.resources.fluentcore.model.implementation.WrapperImpl;
import com.microsoft.azure.management.sql.MetricName;
import com.microsoft.azure.management.sql.MetricValue;
import com.microsoft.azure.management.sql.SqlDatabaseMetric;
import com.microsoft.azure.management.sql.UnitType;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Response containing the SQL Elastic Pool database metrics.
 */
@LangDefinition
public class SqlDatabaseMetricImpl extends WrapperImpl<MetricInner> implements SqlDatabaseMetric {
    protected SqlDatabaseMetricImpl(MetricInner innerObject) {
        super(innerObject);
    }

    @Override
    public MetricName name() {
        return this.inner().name();
    }

    @Override
    public DateTime startTime() {
        return this.inner().startTime();
    }

    @Override
    public DateTime endTime() {
        return this.inner().endTime();
    }

    @Override
    public String timeGrain() {
        return this.inner().timeGrain();
    }

    @Override
    public UnitType unit() {
        return this.inner().unit();
    }

    @Override
    public List<MetricValue> metricValues() {
        return this.inner().metricValues();
    }
}
