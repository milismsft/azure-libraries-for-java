/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.management.resources.fluentcore.model.implementation.WrapperImpl;
import com.microsoft.azure.management.sql.MetricAvailability;
import com.microsoft.azure.management.sql.MetricName;
import com.microsoft.azure.management.sql.PrimaryAggregationType;
import com.microsoft.azure.management.sql.SqlDatabaseMetricDefinition;
import com.microsoft.azure.management.sql.UnitDefinitionType;

import java.util.List;

/**
 * Response containing the SQL Elastic Pool database metric definitions.
 */
@LangDefinition
public class SqlDatabaseMetricDefinitionImpl extends WrapperImpl<MetricDefinitionInner> implements SqlDatabaseMetricDefinition {
    protected SqlDatabaseMetricDefinitionImpl(MetricDefinitionInner innerObject) {
        super(innerObject);
    }

    @Override
    public MetricName name() {
        return this.inner().name();
    }

    @Override
    public PrimaryAggregationType primaryAggregationType() {
        return this.inner().primaryAggregationType();
    }

    @Override
    public String resourceUri() {
        return this.inner().resourceUri();
    }

    @Override
    public UnitDefinitionType unit() {
        return this.inner().unit();
    }

    @Override
    public List<MetricAvailability> metricAvailabilities() {
        return this.inner().metricAvailabilities();
    }
}
