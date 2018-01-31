/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.sql.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.management.resources.fluentcore.arm.ResourceUtils;
import com.microsoft.azure.management.resources.fluentcore.arm.models.implementation.ExternalChildResourceImpl;
import com.microsoft.azure.management.resources.fluentcore.dag.TaskGroup;
import com.microsoft.azure.management.sql.ServiceObjectiveName;
import com.microsoft.azure.management.sql.SqlDatabase;
import com.microsoft.azure.management.sql.SqlDatabaseOperations;
import com.microsoft.azure.management.sql.SqlElasticPool;
import com.microsoft.azure.management.sql.SqlElasticPoolOperations;
import com.microsoft.azure.management.sql.SqlServer;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import org.joda.time.DateTime;
import rx.Completable;
import rx.Observable;

import java.util.Objects;
import java.util.UUID;

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
        SqlDatabase.Update,
        SqlDatabaseOperations.SqlDatabaseOperationsDefinition {

    private SqlElasticPoolsAsExternalChildResourcesImpl sqlElasticPools;
    private TaskGroup.HasTaskGroup parentSqlElasticPool;

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
        this.parentSqlElasticPool = null;
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
        Objects.requireNonNull(sqlServerManager);
        this.sqlServerManager = sqlServerManager;
        this.resourceGroupName = resourceGroupName;
        this.sqlServerName = sqlServerName;

        this.sqlElasticPools = new SqlElasticPoolsAsExternalChildResourcesImpl(this.sqlServerManager, "SqlElasticPool");
        this.parentSqlElasticPool = null;
    }

    /**
     * Creates an instance of external child resource in-memory.
     *
     * @param parentSqlElasticPool the parent SqlElasticPool this database belongs to
     * @param name        the name of this external child resource
     * @param innerObject reference to the inner object representing this external child resource
     * @param sqlServerManager reference to the SQL server manager that accesses firewall rule operations
     */
    SqlDatabaseImpl(TaskGroup.HasTaskGroup parentSqlElasticPool, String name, DatabaseInner innerObject, SqlServerManager sqlServerManager) {
        super(name, null, innerObject);
        Objects.requireNonNull(parentSqlElasticPool);
        Objects.requireNonNull(sqlServerManager);
        this.sqlServerManager = sqlServerManager;
        this.resourceGroupName = resourceGroupName;
        this.sqlServerName = sqlServerName;

        this.sqlElasticPools = new SqlElasticPoolsAsExternalChildResourcesImpl(this.sqlServerManager, "SqlElasticPool");
        this.parentSqlElasticPool = null;
    }

    @Override
    public String id() {
        return null;
    }

    @Override
    public String sqlServerName() {
        return null;
    }

    @Override
    public String collation() {
        return null;
    }

    @Override
    public DateTime creationDate() {
        return null;
    }

    @Override
    public UUID currentServiceObjectiveId() {
        return null;
    }

    @Override
    public String databaseId() {
        return null;
    }

    @Override
    public DateTime earliestRestoreDate() {
        return null;
    }

    @Override
    public UUID requestedServiceObjectiveId() {
        return null;
    }

    @Override
    public long maxSizeBytes() {
        return 0;
    }

    @Override
    public ServiceObjectiveName requestedServiceObjectiveName() {
        return null;
    }

    @Override
    public ServiceObjectiveName serviceLevelObjective() {
        return null;
    }

    @Override
    public String status() {
        return null;
    }

    @Override
    public String elasticPoolName() {
        return null;
    }

    @Override
    public String defaultSecondaryLocation() {
        return null;
    }

    @Override
    public boolean isDataWarehouse() {
        return false;
    }

    @Override
    public String parentId() {
        return ResourceUtils.parentResourceIdFromResourceId(this.id());
    }

    @Override
    public void delete() {
        this.sqlServerManager.inner().elasticPools().delete(this.resourceGroupName, this.sqlServerName, this.name());
    }

    void addParentDependency(TaskGroup.HasTaskGroup parentDependency) {
        this.addDependency(parentDependency);
    }

    @Override
    public void beforeGroupCreateOrUpdate() {
        if (parentSqlElasticPool != null) {
            this.addParentDependency(parentSqlElasticPool);
        }
    }

    @Override
    public Completable deleteAsync() {
        return this.deleteResourceAsync().toCompletable();
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
    public SqlDatabaseImpl update() {
        super.prepareUpdate();
        return this;
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

    @Override
    public SqlDatabaseImpl withExistingSqlServer(String resourceGroupName, String sqlServerName) {
        return null;
    }

    @Override
    public SqlDatabaseImpl withSqlServerId(String sqlServerId) {
        return null;
    }

    @Override
    public SqlDatabaseImpl withIPAddressRange(String startIPAddress, String endIPAddress) {
        return null;
    }

    @Override
    public SqlServerImpl attach() {
        return this.parent();
    }

    @Override
    public SqlDatabaseOperations.DefinitionStages.WithCreate withExistingElasticPool(String elasticPoolName) {
        return null;
    }

    @Override
    public SqlDatabaseOperations.DefinitionStages.WithCreate withExistingDatabase(SqlElasticPool elasticPool) {
        return null;
    }

    @Override
    public SqlElasticPool.DefinitionStages.Blank<SqlDatabaseOperations.DefinitionStages.WithCreate> defineElasticPool(String name) {
        return null;
    }
}
