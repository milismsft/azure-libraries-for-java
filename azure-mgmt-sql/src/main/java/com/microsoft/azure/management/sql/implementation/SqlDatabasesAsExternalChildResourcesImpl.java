/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.management.resources.fluentcore.arm.collection.implementation.ExternalChildResourcesNonCachedImpl;
import com.microsoft.azure.management.sql.SqlDatabase;
import com.microsoft.azure.management.sql.SqlServer;

import java.util.Objects;

/**
 * Represents a SQL Database collection associated with an Azure SQL server.
 */
@LangDefinition
public class SqlDatabasesAsExternalChildResourcesImpl
    extends
        ExternalChildResourcesNonCachedImpl<SqlDatabaseImpl,
            SqlDatabase,
            DatabaseInner,
            SqlServerImpl,
            SqlServer> {

    private SqlElasticPoolImpl elasticPoolParent;
    private String resourceGroupName;
    private String sqlServerName;
    private String sqlServerLocation;

    /**
     * Creates a new ExternalNonInlineChildResourcesImpl.
     *
     * @param parent            the parent Azure resource
     * @param childResourceName the child resource name
     */
    protected SqlDatabasesAsExternalChildResourcesImpl(SqlServerImpl parent, String childResourceName) {
        super(parent, parent.taskGroup(), childResourceName);
        Objects.requireNonNull(parent);
        this.resourceGroupName = parent.resourceGroupName();
        this.sqlServerName = parent.name();
        this.sqlServerLocation = parent.regionName();

        this.elasticPoolParent = null;
    }

    /**
     * Creates a new ExternalNonInlineChildResourcesImpl.
     *
     * @param parent            the parent Azure resource
     * @param childResourceName the child resource name
     */
    protected SqlDatabasesAsExternalChildResourcesImpl(String resourceGroupName, String sqlServerName, String sqlServerLocation, SqlElasticPoolImpl parent, String childResourceName) {
        super(null, parent.taskGroup(), childResourceName);
        this.resourceGroupName = resourceGroupName;
        this.sqlServerName = sqlServerName;
        this.sqlServerLocation = sqlServerLocation;

        this.elasticPoolParent = parent;
    }

    SqlDatabaseImpl defineElasticPool(String name) {
        if (this.elasticPoolParent != null) {
            return prepareIndependentDefine(new SqlDatabaseImpl(this.resourceGroupName, this.sqlServerName, name, new DatabaseInner(), this.parent().manager()));
        } else {
            return prepareInlineDefine(new SqlDatabaseImpl(name, this.parent(), new DatabaseInner(), this.parent().manager()));
        }
    }

    SqlDatabaseImpl updateElasticPool(String name) {
        if (this.elasticPoolParent != null) {
            return prepareInlineUpdate(new SqlDatabaseImpl(this.resourceGroupName, this.sqlServerName, name, new DatabaseInner(), this.parent().manager()));
        } else {
            return prepareInlineUpdate(new SqlDatabaseImpl(name, this.parent(), new DatabaseInner(), this.parent().manager()));
        }
    }

    void withoutElasticPool(String name) {
        if (this.elasticPoolParent != null) {
            prepareInlineRemove(new SqlDatabaseImpl(this.resourceGroupName, this.sqlServerName, name, new DatabaseInner(), this.parent().manager()));
        } else {
            prepareInlineRemove(new SqlDatabaseImpl(name, this.parent(), new DatabaseInner(), this.parent().manager()));
        }
    }
}
