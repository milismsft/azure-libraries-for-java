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

    SqlServerManager sqlServerManager;

    /**
     * Creates a new ExternalChildResourcesNonCachedImpl.
     *
     * @param parent            the parent Azure resource
     * @param childResourceName the child resource name
     */
    protected SqlDatabasesAsExternalChildResourcesImpl(SqlServerImpl parent, String childResourceName) {
        super(parent, parent.taskGroup(), childResourceName);

        this.sqlServerManager = parent.manager();
    }

    /**
     * Creates a new ExternalChildResourcesNonCachedImpl.
     *
     * @param sqlServerManager the manager
     * @param childResourceName the child resource name (for logging)
     */
    protected SqlDatabasesAsExternalChildResourcesImpl(SqlServerManager sqlServerManager, String childResourceName) {
        super(null, null, childResourceName);

        Objects.requireNonNull(sqlServerManager);
        this.sqlServerManager = sqlServerManager;
    }

    SqlDatabaseImpl defineIndependentDatabase(String name) {
        // resource group and server name will be set by the next method in the Fluent flow
        return prepareIndependentDefine(new SqlDatabaseImpl(null, null, name, new DatabaseInner(), this.sqlServerManager));
    }

    SqlDatabaseImpl defineInlineDatabase(String name) {
        return prepareInlineDefine(new SqlDatabaseImpl(name, this.parent(), new DatabaseInner(), this.parent().manager()));
    }

    SqlDatabaseImpl updateInlineDatabase(String name) {
        return prepareInlineUpdate(new SqlDatabaseImpl(name, this.parent(), new DatabaseInner(), this.parent().manager()));
    }

    void removeInlineDatabase(String name) {
        prepareInlineRemove(new SqlDatabaseImpl(name, this.parent(), new DatabaseInner(), this.parent().manager()));
    }
}
