/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.sql.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.management.resources.fluentcore.arm.models.implementation.ExternalChildResourceImpl;
import com.microsoft.azure.management.sql.SqlDatabase;
import com.microsoft.azure.management.sql.SqlServer;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import rx.Observable;

import java.util.Objects;

/**
 * Implementation for SqlDatabase and its parent interfaces.
 */
@LangDefinition
class SqlDatabaseImpl
    extends
        ExternalChildResourceImpl<SqlDatabase, DatabaseInner, SqlServerImpl, SqlServer>
    implements
        SqlDatabase,
        SqlDatabase.SqlDatabaseDefinition<SqlServer.DefinitionStages.WithCreate>,
        SqlDatabase.Update {
//        SqlDatabaseOperations.SqlDatabaseOperationsDefinition {

    private SqlElasticPoolsAsExternalChildResourcesImpl sqlElasticPools;

    private SqlServerManager sqlServerManager;
    private String resourceGroupName;
    private String sqlServerName;

    /**
     * Creates an instance of external child resource in-memory.
     *
     * @param name        the name of this external child resource
     * @param parent      reference to the parent of this external child resource
     * @param innerObject reference to the inner object representing this external child resource
     * @param sqlServerManager reference to the SQL server manager that accesses firewall rule operations
     */
    SqlDatabaseImpl(String name, SqlServerImpl parent, DatabaseInner innerObject, SqlServerManager sqlServerManager) {
        super(name, parent, innerObject);

        Objects.requireNonNull(parent);
        Objects.requireNonNull(sqlServerManager);
        this.sqlServerManager = sqlServerManager;
        this.resourceGroupName = parent.resourceGroupName();
        this.sqlServerName = parent.name();

        this.sqlElasticPools = null;
    }

    /**
     * Creates an instance of external child resource in-memory.
     *
     * @param resourceGroupName the resource group name
     * @param sqlServerName the parent SQL server name
     * @param name        the name of this external child resource
     * @param innerObject reference to the inner object representing this external child resource
     * @param sqlServerManager reference to the SQL server manager that accesses firewall rule operations
     */
    SqlDatabaseImpl(String resourceGroupName, String sqlServerName, String name, DatabaseInner innerObject, SqlServerManager sqlServerManager) {
        super(name, null, innerObject);
        this.sqlServerManager = sqlServerManager;
        this.resourceGroupName = resourceGroupName;
        this.sqlServerName = sqlServerName;

        this.sqlElasticPools = new SqlElasticPoolsAsExternalChildResourcesImpl(this, "SqlElasticPool");
    }

    @Override
    public String id() {
        return null;
    }

    @Override
    public SqlDatabase apply() {
        return null;
    }

    @Override
    public Observable<SqlDatabase> createResourceAsync() {
        return null;
    }

    @Override
    public Observable<SqlDatabase> updateResourceAsync() {
        return null;
    }

    @Override
    public Observable<Void> deleteResourceAsync() {
        return null;
    }

    @Override
    public Observable<SqlDatabase> applyAsync() {
        return null;
    }

    @Override
    public ServiceFuture<SqlDatabase> applyAsync(ServiceCallback<SqlDatabase> callback) {
        return null;
    }

    @Override
    public SqlDatabaseImpl update() {
        return null;
    }

    @Override
    public SqlServerImpl attach() {
        return null;
    }

    @Override
    public SqlDatabaseImpl withIPAddress(String ipAddress) {
        return null;
    }

    @Override
    public SqlDatabaseImpl withEndIPAddress(String endIPAddress) {
        return null;
    }

    @Override
    protected Observable<DatabaseInner> getInnerAsync() {
        return null;
    }
}
