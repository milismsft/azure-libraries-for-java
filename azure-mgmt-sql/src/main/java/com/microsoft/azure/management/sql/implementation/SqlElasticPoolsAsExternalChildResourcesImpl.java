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

    /**
     * Creates a new ExternalNonInlineChildResourcesImpl.
     *
     * @param parent            the parent Azure resource
     * @param childResourceName the child resource name
     */
    protected SqlElasticPoolsAsExternalChildResourcesImpl(SqlServerImpl parent, String childResourceName) {
        super(parent, parent.taskGroup(), childResourceName);
    }

    /**
     * Creates a new ExternalNonInlineChildResourcesImpl.
     *
     * @param parent            the parent Azure resource
     * @param childResourceName the child resource name
     */
    protected SqlElasticPoolsAsExternalChildResourcesImpl(SqlDatabaseImpl parent, String childResourceName) {
        super(null, parent.taskGroup(), childResourceName);
    }

    SqlElasticPoolImpl defineElasticPool(String name) {
        return prepareDefine(new SqlElasticPoolImpl(name, this.parent(), new ElasticPoolInner(), this.parent().manager()));
    }

    SqlElasticPoolImpl updateElasticPool(String name) {
        return prepareUpdate(new SqlElasticPoolImpl(name, this.parent(), new ElasticPoolInner(), this.parent().manager()));
    }

    void withoutElasticPool(String name) {
        prepareRemove(new SqlElasticPoolImpl(name, this.parent(), new ElasticPoolInner(), this.parent().manager()));
    }
}
