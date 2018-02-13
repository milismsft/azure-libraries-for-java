/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.sql.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import com.microsoft.azure.management.resources.fluentcore.arm.ResourceUtils;
import com.microsoft.azure.management.resources.fluentcore.arm.models.implementation.ExternalChildResourceImpl;
import com.microsoft.azure.management.resources.fluentcore.dag.TaskGroup;
import com.microsoft.azure.management.resources.fluentcore.model.Creatable;
import com.microsoft.azure.management.sql.CreateMode;
import com.microsoft.azure.management.sql.DatabaseEdition;
import com.microsoft.azure.management.sql.ReplicationLink;
import com.microsoft.azure.management.sql.RestorePoint;
import com.microsoft.azure.management.sql.ServiceObjectiveName;
import com.microsoft.azure.management.sql.SqlDatabase;
import com.microsoft.azure.management.sql.SqlDatabaseOperations;
import com.microsoft.azure.management.sql.SqlDatabasePremiumRSServiceObjective;
import com.microsoft.azure.management.sql.SqlDatabasePremiumRSStorage;
import com.microsoft.azure.management.sql.SqlDatabasePremiumServiceObjective;
import com.microsoft.azure.management.sql.SqlDatabasePremiumStorage;
import com.microsoft.azure.management.sql.SqlDatabaseStandardServiceObjective;
import com.microsoft.azure.management.sql.SqlDatabaseStandardStorage;
import com.microsoft.azure.management.sql.SqlElasticPool;
import com.microsoft.azure.management.sql.SqlServer;
import com.microsoft.azure.management.sql.SqlWarehouse;
import com.microsoft.azure.management.sql.UpgradeHintInterface;
import org.joda.time.DateTime;
import rx.Completable;
import rx.Observable;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        SqlDatabaseOperations.DefinitionStages.WithExistingDatabase,
        SqlDatabaseOperations.DefinitionStages.WithCreateWithElasticPoolOptions,
        SqlDatabaseOperations.SqlDatabaseOperationsDefinition {

    private SqlElasticPoolsAsExternalChildResourcesImpl sqlElasticPools;
    private TaskGroup.HasTaskGroup parentSqlElasticPool;

    protected SqlServerManager sqlServerManager;
    protected String resourceGroupName;
    protected String sqlServerName;
    protected String sqlServerLocation;
    private boolean isPatchUpdate;

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
        this.sqlServerLocation = parent.regionName();

        this.sqlElasticPools = null;
        this.parentSqlElasticPool = null;
        this.isPatchUpdate = false;
    }

    /**
     * Creates an instance of external child resource in-memory.
     *
     * @param resourceGroupName the resource group name
     * @param sqlServerName the parent SQL server name
     * @param sqlServerLocation the parent SQL server location
     * @param name        the name of this external child resource
     * @param innerObject reference to the inner object representing this external child resource
     * @param sqlServerManager reference to the SQL server manager that accesses firewall rule operations
     */
    SqlDatabaseImpl(String resourceGroupName, String sqlServerName, String sqlServerLocation, String name, DatabaseInner innerObject, SqlServerManager sqlServerManager) {
        super(name, null, innerObject);
        Objects.requireNonNull(sqlServerManager);
        this.sqlServerManager = sqlServerManager;
        this.resourceGroupName = resourceGroupName;
        this.sqlServerName = sqlServerName;
        this.sqlServerLocation = sqlServerLocation;

        this.sqlElasticPools = new SqlElasticPoolsAsExternalChildResourcesImpl(this.sqlServerManager, "SqlElasticPool");
        this.parentSqlElasticPool = null;
        this.isPatchUpdate = false;
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

        this.sqlElasticPools = new SqlElasticPoolsAsExternalChildResourcesImpl(this.sqlServerManager, "SqlElasticPool");
        this.parentSqlElasticPool = null;
        this.isPatchUpdate = false;
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
        return this.inner().edition().toString().equalsIgnoreCase(DatabaseEdition.DATA_WAREHOUSE.toString());
    }

    @Override
    public SqlWarehouse asWarehouse() {
        if (this.isDataWarehouse()) {
            return (SqlWarehouse) this;
        }

        return null;
    }

    @Override
    public List<RestorePoint> listRestorePoints() {
        List<RestorePoint> restorePoints = new ArrayList<>();
        List<RestorePointInner> restorePointInners = this.sqlServerManager.inner()
            .restorePoints().listByDatabase(this.resourceGroupName, this.sqlServerName, this.name());
        if (restorePointInners != null) {
            for (RestorePointInner inner : restorePointInners) {
                restorePoints.add(new RestorePointImpl(this.resourceGroupName, this.sqlServerName, inner));
            }
        }
        return Collections.unmodifiableList(restorePoints);
    }

    @Override
    public Map<String, ReplicationLink> listReplicationLinks() {
        Map<String, ReplicationLink> replicationLinkMap = new HashMap<>();
        List<ReplicationLinkInner> replicationLinkInners = this.sqlServerManager.inner()
            .replicationLinks().listByDatabase(this.resourceGroupName, this.sqlServerName, this.name());
        if (replicationLinkInners != null) {
            for (ReplicationLinkInner inner : replicationLinkInners) {
                replicationLinkMap.put(inner.name(), new ReplicationLinkImpl(this.resourceGroupName, this.sqlServerName, inner, this.sqlServerManager));
            }
        }
        return Collections.unmodifiableMap(replicationLinkMap);
    }

//    @Override
//    public List<DatabaseMetric> listUsages() {
//        return null;
//    }
//
//    @Override
//    public TransparentDataEncryption getTransparentDataEncryption() {
//        return null;
//    }
//
//    @Override
//    public Map<String, ServiceTierAdvisor> listServiceTierAdvisors() {
//        return null;
//    }

    @Override
    public String parentId() {
        return ResourceUtils.parentResourceIdFromResourceId(this.id());
    }

    @Override
    public String regionName() {
        return this.inner().location();
    }

    @Override
    public Region region() {
        return Region.findByLabelOrName(this.regionName());
    }

    @Override
    public UpgradeHintInterface getUpgradeHint() {
        return null;
    }

    SqlDatabaseImpl withPatchUpdate() {
        this.isPatchUpdate = true;
        return this;
    }

    @Override
    protected Observable<DatabaseInner> getInnerAsync() {
        return this.sqlServerManager.inner().databases().getAsync(this.resourceGroupName, this.sqlServerName, this.name());
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
    public Observable<SqlDatabase> createResourceAsync() {
        final SqlDatabaseImpl self = this;
        this.inner().withLocation(this.sqlServerLocation);
        return this.sqlServerManager.inner().databases()
            .createOrUpdateAsync(this.resourceGroupName, this.sqlServerName, this.name(), this.inner())
            .map(new Func1<DatabaseInner, SqlDatabase>() {
                @Override
                public SqlDatabase call(DatabaseInner inner) {
                    self.setInner(inner);
                    return self;
                }
            });
    }

    @Override
    public Observable<SqlDatabase> updateResourceAsync() {
        if (this.isPatchUpdate) {
            final SqlDatabaseImpl self = this;
            DatabaseUpdateInner databaseUpdateInner = new DatabaseUpdateInner()
                .withTags(self.inner().getTags())
                .withCollation(self.inner().collation())
                .withSourceDatabaseId(self.inner().sourceDatabaseId())
                .withCreateMode(self.inner().createMode())
                .withEdition(self.inner().edition())
                .withRequestedServiceObjectiveName(this.inner().requestedServiceObjectiveName())
                .withMaxSizeBytes(this.inner().maxSizeBytes())
                .withElasticPoolName(this.inner().elasticPoolName());
            return this.sqlServerManager.inner().databases()
                .updateAsync(this.resourceGroupName, this.sqlServerName, this.name(), databaseUpdateInner)
                .map(new Func1<DatabaseInner, SqlDatabase>() {
                    @Override
                    public SqlDatabase call(DatabaseInner inner) {
                        self.setInner(inner);
                        return self;
                    }
                });

        } else {
            return this.createResourceAsync();
        }
    }

    @Override
    public SqlDatabaseImpl update() {
        super.prepareUpdate();
        return this;
    }

    @Override
    public Completable afterPostRunAsync(boolean isGroupFaulted) {
        if (this.sqlElasticPools != null) {
            this.sqlElasticPools.clear();
        }

        return Completable.complete();
    }

    @Override
    public Observable<Void> deleteResourceAsync() {
        return this.sqlServerManager.inner().databases().deleteAsync(this.resourceGroupName, this.sqlServerName, this.name());
    }

    @Override
    public void delete() {
        this.sqlServerManager.inner().databases().delete(this.resourceGroupName, this.sqlServerName, this.name());
    }

    @Override
    public Completable deleteAsync() {
        return this.deleteResourceAsync().toCompletable();
    }

    @Override
    public SqlDatabaseImpl withExistingSqlServer(String resourceGroupName, String sqlServerName, String sqlServerLocation) {
        this.resourceGroupName = resourceGroupName;
        this.sqlServerName = sqlServerName;
        this.sqlServerLocation = sqlServerLocation;

        return this;
    }

    @Override
    public SqlDatabaseImpl withSqlServer(SqlServer sqlServer) {
        this.resourceGroupName = sqlServer.resourceGroupName();
        this.sqlServerName = sqlServer.name();
        this.sqlServerLocation = sqlServer.regionName();

        return this;
    }

    @Override
    public SqlServerImpl attach() {
        return this.parent();
    }

    @Override
    public SqlDatabaseImpl withoutElasticPool() {
        this.inner().withElasticPoolName(null);

        return this;
    }

    @Override
    public SqlDatabaseImpl withExistingElasticPool(String elasticPoolName) {
        this.inner().withElasticPoolName(elasticPoolName);

        return this;
    }

    @Override
    public SqlDatabaseImpl withExistingElasticPool(SqlElasticPool sqlElasticPool) {
        this.inner().withElasticPoolName(sqlElasticPool.name());

        return this;
    }

    @Override
    public SqlDatabaseImpl withNewElasticPool(final Creatable<SqlElasticPool> sqlElasticPool) {
        this.addDependency(sqlElasticPool);

        return this;
    }

    @Override
    public SqlElasticPoolForDatabaseImpl defineElasticPool(String elasticPoolName) {
        if (this.sqlElasticPools == null) {
            this.sqlElasticPools = new SqlElasticPoolsAsExternalChildResourcesImpl(this.taskGroup(), this.sqlServerManager, "SqlElasticPool");
        }

        return new SqlElasticPoolForDatabaseImpl(this, this.sqlElasticPools
            .defineIndependentElasticPool(elasticPoolName).withExistingSqlServer(this.resourceGroupName, this.sqlServerName, this.sqlServerLocation));
    }

    @Override
    public SqlDatabaseImpl withSourceDatabase(String sourceDatabaseId) {
        this.inner().withSourceDatabaseId(sourceDatabaseId);

        return this;
    }

    @Override
    public SqlDatabaseImpl withSourceDatabase(SqlDatabase sourceDatabase) {
        return this.withSourceDatabase(sourceDatabase.id());
    }

    @Override
    public SqlDatabaseImpl withMode(CreateMode createMode) {
        this.inner().withCreateMode(createMode);

        return this;
    }

    @Override
    public SqlDatabaseImpl withCollation(String collation) {
        this.inner().withCollation(collation);

        return this;
    }

    @Override
    public SqlDatabaseImpl withMaxSizeBytes(long maxSizeBytes) {
        this.inner().withMaxSizeBytes(Long.toString(maxSizeBytes));

        return this;
    }

    @Override
    public SqlDatabaseImpl withEdition(DatabaseEdition edition) {
        this.inner().withElasticPoolName(null);
        this.inner().withEdition(edition);

        return this;
    }

    @Override
    public SqlDatabaseImpl withBasicEdition() {
        this.inner().withEdition(DatabaseEdition.BASIC);
        return this;
    }

    @Override
    public SqlDatabaseImpl withStandardEdition(SqlDatabaseStandardServiceObjective serviceObjective) {
        this.inner().withEdition(DatabaseEdition.STANDARD);
        this.inner().withRequestedServiceObjectiveName(ServiceObjectiveName.fromString(serviceObjective.toString()));
        return this;
    }

    @Override
    public SqlDatabaseImpl withStandardEdition(SqlDatabaseStandardServiceObjective serviceObjective, SqlDatabaseStandardStorage maxStorageCapacity) {
        this.inner().withEdition(DatabaseEdition.STANDARD);
        this.inner().withRequestedServiceObjectiveName(ServiceObjectiveName.fromString(serviceObjective.toString()));
        this.inner().withMaxSizeBytes(Long.toString(maxStorageCapacity.capacityInMB()));
        return this;
    }

    @Override
    public SqlDatabaseImpl withPremiumEdition(SqlDatabasePremiumServiceObjective serviceObjective) {
        this.inner().withEdition(DatabaseEdition.PREMIUM);
        this.inner().withRequestedServiceObjectiveName(ServiceObjectiveName.fromString(serviceObjective.toString()));
        return this;
    }

    @Override
    public SqlDatabaseImpl withPremiumEdition(SqlDatabasePremiumServiceObjective serviceObjective, SqlDatabasePremiumStorage maxStorageCapacity) {
        this.inner().withEdition(DatabaseEdition.PREMIUM);
        this.inner().withRequestedServiceObjectiveName(ServiceObjectiveName.fromString(serviceObjective.toString()));
        this.inner().withMaxSizeBytes(Long.toString(maxStorageCapacity.capacityInMB()));
        return this;
    }

    @Override
    public SqlDatabaseImpl withPremiumRSEdition(SqlDatabasePremiumRSServiceObjective serviceObjective) {
        this.inner().withEdition(DatabaseEdition.PREMIUM_RS);
        this.inner().withRequestedServiceObjectiveName(ServiceObjectiveName.fromString(serviceObjective.toString()));
        return this;
    }

    @Override
    public SqlDatabaseImpl withPremiumRSEdition(SqlDatabasePremiumRSServiceObjective serviceObjective, SqlDatabasePremiumRSStorage maxStorageCapacity) {
        this.inner().withEdition(DatabaseEdition.PREMIUM_RS);
        this.inner().withRequestedServiceObjectiveName(ServiceObjectiveName.fromString(serviceObjective.toString()));
        this.inner().withMaxSizeBytes(Long.toString(maxStorageCapacity.capacityInMB()));
        return this;
    }

    @Override
    public SqlDatabaseImpl withServiceObjective(ServiceObjectiveName serviceLevelObjective) {
        this.inner().withElasticPoolName(null);
        this.inner().withRequestedServiceObjectiveName(serviceLevelObjective);

        return this;
    }

    @Override
    public SqlDatabaseImpl withTags(Map<String, String> tags) {
        this.inner().withTags(tags);
        return this;
    }

    @Override
    public SqlDatabaseImpl withTag(String key, String value) {
        this.inner().getTags().put(key, value);
        return this;
    }

    @Override
    public SqlDatabaseImpl withoutTag(String key) {
        this.inner().getTags().remove(key);
        return this;
    }

}
