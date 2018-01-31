/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.management.resources.fluentcore.arm.collection.implementation.ExternalChildResourcesNonCachedImpl;
import com.microsoft.azure.management.sql.SqlElasticPool;
import com.microsoft.azure.management.sql.SqlServer;

/**
 * Represents a SQL Elastic Pool collection associated with an Azure SQL server.
 */
@LangDefinition
public class SqlElasticPoolsAsExternalChildResourcesImpl
    extends
        ExternalChildResourcesNonCachedImpl<SqlElasticPoolImpl,
            SqlElasticPool,
            ElasticPoolInner,
            SqlServerImpl,
            SqlServer> {

    SqlServerManager sqlServerManager;

    /**
     * Creates a new ExternalChildResourcesNonCachedImpl.
     *
     * @param parent            the parent Azure resource
     * @param childResourceName the child resource name
     */
    protected SqlElasticPoolsAsExternalChildResourcesImpl(SqlServerImpl parent, String childResourceName) {
        super(parent, parent.taskGroup(), childResourceName);
        this.sqlServerManager = parent.manager();
    }

    /**
     * Creates a new ExternalChildResourcesNonCachedImpl.
     *
     * @param sqlServerManager the manager
     * @param childResourceName the child resource name (for logging)
     */
    protected SqlElasticPoolsAsExternalChildResourcesImpl(SqlServerManager sqlServerManager,String childResourceName) {
        super(null, null, childResourceName);
        this.sqlServerManager = sqlServerManager;
    }

    SqlElasticPoolImpl defineIndependentElasticPool(String name) {
        // resource group, server name and location will be set by the next method in the Fluent flow
        return prepareIndependentDefine(new SqlElasticPoolImpl(null, null, null, name, new ElasticPoolInner(), this.sqlServerManager));
    }

    SqlElasticPoolImpl defineInlineElasticPool(String name) {
        return prepareInlineDefine(new SqlElasticPoolImpl(name, this.parent(), new ElasticPoolInner(), this.parent().manager()));
    }

    SqlElasticPoolImpl updateInlineElasticPool(String name) {
        return prepareInlineUpdate(new SqlElasticPoolImpl(name, this.parent(), new ElasticPoolInner(), this.parent().manager()));
    }

    void removeInlineElasticPool(String name) {
        prepareInlineRemove(new SqlElasticPoolImpl(name, this.parent(), new ElasticPoolInner(), this.parent().manager()));
    }
}
