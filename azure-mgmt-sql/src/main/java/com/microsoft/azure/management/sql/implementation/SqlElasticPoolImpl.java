/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.management.resources.fluentcore.arm.models.implementation.ExternalChildResourceImpl;
import com.microsoft.azure.management.sql.ElasticPoolEdition;
import com.microsoft.azure.management.sql.ElasticPoolState;
import com.microsoft.azure.management.sql.SqlDatabase;
import com.microsoft.azure.management.sql.SqlDatabaseMetric;
import com.microsoft.azure.management.sql.SqlDatabaseMetricDefinition;
import com.microsoft.azure.management.sql.SqlElasticPool;
import com.microsoft.azure.management.sql.SqlElasticPoolBasicEDTUs;
import com.microsoft.azure.management.sql.SqlElasticPoolBasicMaxEDTUs;
import com.microsoft.azure.management.sql.SqlElasticPoolBasicMinEDTUs;
import com.microsoft.azure.management.sql.SqlElasticPoolOperations;
import com.microsoft.azure.management.sql.SqlElasticPoolPremiumEDTUs;
import com.microsoft.azure.management.sql.SqlElasticPoolPremiumMaxEDTUs;
import com.microsoft.azure.management.sql.SqlElasticPoolPremiumMinEDTUs;
import com.microsoft.azure.management.sql.SqlElasticPoolPremiumSorage;
import com.microsoft.azure.management.sql.SqlElasticPoolStandardEDTUs;
import com.microsoft.azure.management.sql.SqlElasticPoolStandardMaxEDTUs;
import com.microsoft.azure.management.sql.SqlElasticPoolStandardMinEDTUs;
import com.microsoft.azure.management.sql.SqlElasticPoolStandardStorage;
import com.microsoft.azure.management.sql.SqlServer;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import org.joda.time.DateTime;
import rx.Observable;
import rx.functions.Func1;

import java.util.List;
import java.util.Objects;

/**
 * Implementation for SqlElasticPool.
 */
@LangDefinition
public class SqlElasticPoolImpl
    extends
        ExternalChildResourceImpl<SqlElasticPool, ElasticPoolInner, SqlServerImpl, SqlServer>
    implements
        SqlElasticPool,
        SqlElasticPool.SqlElasticPoolDefinition<SqlServer.DefinitionStages.WithCreate>,
        SqlElasticPool.Update,
        SqlElasticPoolOperations.SqlElasticPoolOperationsDefinition {

    private SqlServerManager sqlServerManager;
    private String resourceGroupName;
    private String sqlServerName;
    private String sqlServerLocation;

    private SqlDatabasesAsExternalChildResourcesImpl sqlDatabases;

    /**
     * Creates an instance of external child resource in-memory.
     *
     * @param name        the name of this external child resource
     * @param parent      reference to the parent of this external child resource
     * @param innerObject reference to the inner object representing this external child resource
     * @param sqlServerManager reference to the SQL server manager that accesses firewall rule operations
     */
    SqlElasticPoolImpl(String name, SqlServerImpl parent, ElasticPoolInner innerObject, SqlServerManager sqlServerManager) {
        super(name, parent, innerObject);

        Objects.requireNonNull(parent);
        Objects.requireNonNull(sqlServerManager);
        this.sqlServerManager = sqlServerManager;
        this.resourceGroupName = parent.resourceGroupName();
        this.sqlServerName = parent.name();
        this.sqlServerLocation = parent.regionName();

        this.sqlDatabases = null;
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
    SqlElasticPoolImpl(String resourceGroupName, String sqlServerName, String sqlServerLocation, String name, ElasticPoolInner innerObject, SqlServerManager sqlServerManager) {
        super(name, null, innerObject);
        this.sqlServerManager = sqlServerManager;
        this.resourceGroupName = resourceGroupName;
        this.sqlServerName = sqlServerName;
        this.sqlServerLocation = sqlServerLocation;

        this.sqlDatabases = new SqlDatabasesAsExternalChildResourcesImpl(resourceGroupName, sqlServerName, sqlServerLocation, this, "SqlDatabase");
    }

    @Override
    public String id() {
        return null;
    }

    @Override
    public SqlElasticPool apply() {
        return null;
    }

    @Override
    public Observable<SqlElasticPool> createResourceAsync() {
        return null;
    }

    @Override
    public Observable<SqlElasticPool> updateResourceAsync() {
        return null;
    }

    @Override
    public Observable<Void> deleteResourceAsync() {
        return null;
    }

    @Override
    public Observable<SqlElasticPool> applyAsync() {
        return null;
    }

    @Override
    public ServiceFuture<SqlElasticPool> applyAsync(ServiceCallback<SqlElasticPool> callback) {
        return null;
    }

    @Override
    public Update update() {
        return null;
    }

    @Override
    public SqlServer.DefinitionStages.WithCreate attach() {
        return null;
    }

    @Override
    public String sqlServerName() {
        return null;
    }

    @Override
    public DateTime creationDate() {
        return null;
    }

    @Override
    public ElasticPoolState state() {
        return null;
    }

    @Override
    public ElasticPoolEdition edition() {
        return null;
    }

    @Override
    public int dtu() {
        return 0;
    }

    @Override
    public int databaseDtuMax() {
        return 0;
    }

    @Override
    public int databaseDtuMin() {
        return 0;
    }

    @Override
    public int storageMB() {
        return 0;
    }

    @Override
    public int storageCapacityInMB() {
        return 0;
    }

    @Override
    public List<SqlDatabaseMetric> listDatabaseMetrics() {
        return null;
    }

    @Override
    public List<SqlDatabaseMetricDefinition> listDatabaseMetricDefinitions() {
        return null;
    }

    @Override
    public List<SqlDatabase> listDatabases() {
        return null;
    }

    @Override
    public SqlDatabase getDatabase(String databaseName) {
        return null;
    }

    @Override
    public SqlDatabase addNewDatabase(String databaseName) {
        return null;
    }

    @Override
    public SqlDatabase addExistingDatabase(String databaseName) {
        return null;
    }

    @Override
    public SqlDatabase removeDatabase(String databaseName) {
        return null;
    }

    @Override
    public void delete() {

    }

//    @Override
//    public SqlElasticPool create() {
//        return this.createAsync().toBlocking().last();
//    }
//
//    @Override
//    public ServiceFuture<SqlElasticPool> createAsync(ServiceCallback<SqlElasticPool> callback) {
//        return ServiceFuture.fromBody(this.createAsync(), callback);
//    }
//
//    @Override
//    public Observable<SqlElasticPool> createAsync() {
//        //this.enableCommitMode();
//        //commitAndGetAllAsync();
//
//        final SqlElasticPoolImpl self = this;
//        return this.sqlServerManager.inner().elasticPools()
//            .createOrUpdateAsync(this.resourceGroupName, this.sqlServerName, this.name(),this.inner())
//            .map(new Func1<ElasticPoolInner, SqlElasticPool>() {
//                @Override
//                public SqlElasticPool call(ElasticPoolInner inner) {
//                    self.setInner(inner);
//                    return self;
//                }
//            })
//            // TODO: this needs to be converted to work with the new TaskGroup framework
//            .flatMap(new Func1<SqlElasticPool, Observable<SqlElasticPool>>() {
//                @Override
//                public Observable<SqlElasticPool> call(SqlElasticPool sqlElasticPool) {
//                    return self.sqlDatabases.commitAndGetAllAsync()
//                        .map(new Func1<List<SqlDatabaseImpl>, SqlElasticPool>() {
//                            @Override
//                            public SqlElasticPool call(List<SqlDatabaseImpl> databases) {
//                                return self;
//                            }
//                        });
//                }
//            });
//    }
//
//    @Override
//    public Observable<SqlElasticPool> updateAsync() {
//        return null;
//    }
//
//    @Override
//    public Observable<Void> deleteAsync() {
//        return null;
//    }

    @Override
    protected Observable<ElasticPoolInner> getInnerAsync() {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withExistingSqlServer(String resourceGroupName, String sqlServerName, String location) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withSqlServer(SqlServer sqlServer) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withEdition(ElasticPoolEdition edition) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withBasicPool() {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withStandardPool() {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withPremiumPool() {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withReservedDtu(SqlElasticPoolBasicEDTUs eDTU) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withDatabaseDtuMax(SqlElasticPoolBasicMaxEDTUs eDTU) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withDatabaseDtuMin(SqlElasticPoolBasicMinEDTUs eDTU) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withReservedDtu(SqlElasticPoolStandardEDTUs eDTU) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withDatabaseDtuMax(SqlElasticPoolStandardMaxEDTUs eDTU) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withDatabaseDtuMin(SqlElasticPoolStandardMinEDTUs eDTU) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withStorageCapacity(SqlElasticPoolStandardStorage storageCapacity) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withReservedDtu(SqlElasticPoolPremiumEDTUs eDTU) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withDatabaseDtuMax(SqlElasticPoolPremiumMaxEDTUs eDTU) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withDatabaseDtuMin(SqlElasticPoolPremiumMinEDTUs eDTU) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withStorageCapacity(SqlElasticPoolPremiumSorage storageCapacity) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withDatabaseDtuMin(int databaseDtuMin) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withDatabaseDtuMax(int databaseDtuMax) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withDtu(int dtu) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withStorageCapacity(int storageMB) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withNewDatabase(String databaseName) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withExistingDatabase(String databaseName) {
        return null;
    }

    @Override
    public SqlElasticPoolImpl withExistingDatabase(SqlDatabase database) {
        return null;
    }
}
