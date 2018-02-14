/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.sql.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.management.sql.CreateMode;
import com.microsoft.azure.management.sql.DatabaseEdition;
import com.microsoft.azure.management.sql.ServiceObjectiveName;
import com.microsoft.azure.management.sql.SqlDatabase;
import com.microsoft.azure.management.sql.SqlDatabasePremiumRSServiceObjective;
import com.microsoft.azure.management.sql.SqlDatabasePremiumRSStorage;
import com.microsoft.azure.management.sql.SqlDatabasePremiumServiceObjective;
import com.microsoft.azure.management.sql.SqlDatabasePremiumStorage;
import com.microsoft.azure.management.sql.SqlDatabaseStandardServiceObjective;
import com.microsoft.azure.management.sql.SqlDatabaseStandardStorage;
import com.microsoft.azure.management.sql.SqlElasticPoolOperations;
import com.microsoft.azure.management.sql.SqlRestorableDroppedDatabase;

import java.util.Objects;

/**
 * Implementation for SqlDatabase as inline definition inside a SqlElasticPool definition.
 */
@LangDefinition
public class SqlDatabaseForElasticPoolImpl
    implements
        SqlDatabase.SqlDatabaseDefinition<SqlElasticPoolOperations.DefinitionStages.WithCreate> {

    private SqlDatabaseImpl sqlDatabase;
    private SqlElasticPoolImpl sqlElasticPool;

    SqlDatabaseForElasticPoolImpl(SqlElasticPoolImpl sqlElasticPool, SqlDatabaseImpl sqlDatabase) {
        Objects.requireNonNull(sqlElasticPool);
        Objects.requireNonNull(sqlDatabase);
        Objects.requireNonNull(sqlDatabase.inner());
        this.sqlElasticPool = sqlElasticPool;
        this.sqlDatabase = sqlDatabase;
        this.sqlDatabase.inner().withLocation(sqlElasticPool.regionName());
        this.sqlDatabase.inner().withElasticPoolName(this.sqlElasticPool.name());
    }

    @Override
    public SqlElasticPoolImpl attach() {
//        this.sqlDatabase.addParentDependency(this.sqlElasticPool);
        return this.sqlElasticPool;
    }

    @Override
    public SqlDatabaseForElasticPoolImpl fromRestorableDroppedDatabase(SqlRestorableDroppedDatabase restorableDroppedDatabase) {
        return this.withSourceDatabase(restorableDroppedDatabase.id())
            .withMode(CreateMode.RESTORE);
    }

    @Override
    public SqlDatabaseForElasticPoolImpl withSourceDatabase(String sourceDatabaseId) {
        this.sqlDatabase.inner().withSourceDatabaseId(sourceDatabaseId);
        return this;
    }

    @Override
    public SqlDatabaseForElasticPoolImpl withSourceDatabase(SqlDatabase sourceDatabase) {
        this.sqlDatabase.inner().withSourceDatabaseId(sourceDatabase.databaseId());
        return this;
    }

    @Override
    public SqlDatabaseForElasticPoolImpl withMode(CreateMode createMode) {
        this.sqlDatabase.withMode(createMode);
        return this;
    }

    @Override
    public SqlDatabaseForElasticPoolImpl withCollation(String collation) {
        this.sqlDatabase.withCollation(collation);
        return this;
    }

    @Override
    public SqlDatabaseForElasticPoolImpl withMaxSizeBytes(long maxSizeBytes) {
        this.sqlDatabase.withMaxSizeBytes(maxSizeBytes);
        return this;
    }

    @Override
    public SqlDatabaseForElasticPoolImpl withEdition(DatabaseEdition edition) {
        this.sqlDatabase.withEdition(edition);
        return this;
    }

    @Override
    public SqlDatabaseForElasticPoolImpl withBasicEdition() {
        this.sqlDatabase.withEdition(DatabaseEdition.BASIC);
        return this;
    }

    @Override
    public SqlDatabaseForElasticPoolImpl withStandardEdition(SqlDatabaseStandardServiceObjective serviceObjective) {
        this.sqlDatabase.withEdition(DatabaseEdition.STANDARD);
        this.sqlDatabase.withServiceObjective(ServiceObjectiveName.fromString(serviceObjective.toString()));
        return this;
    }

    @Override
    public SqlDatabaseForElasticPoolImpl withStandardEdition(SqlDatabaseStandardServiceObjective serviceObjective, SqlDatabaseStandardStorage maxStorageCapacity) {
        this.sqlDatabase.withEdition(DatabaseEdition.STANDARD);
        this.sqlDatabase.withServiceObjective(ServiceObjectiveName.fromString(serviceObjective.toString()));
        this.sqlDatabase.withMaxSizeBytes(maxStorageCapacity.capacityInMB());
        return this;
    }

    @Override
    public SqlDatabaseForElasticPoolImpl withPremiumEdition(SqlDatabasePremiumServiceObjective serviceObjective) {
        this.sqlDatabase.withEdition(DatabaseEdition.PREMIUM);
        this.sqlDatabase.withServiceObjective(ServiceObjectiveName.fromString(serviceObjective.toString()));
        return this;
    }

    @Override
    public SqlDatabaseForElasticPoolImpl withPremiumEdition(SqlDatabasePremiumServiceObjective serviceObjective, SqlDatabasePremiumStorage maxStorageCapacity) {
        this.sqlDatabase.withEdition(DatabaseEdition.PREMIUM);
        this.sqlDatabase.withServiceObjective(ServiceObjectiveName.fromString(serviceObjective.toString()));
        this.sqlDatabase.withMaxSizeBytes(maxStorageCapacity.capacityInMB());
        return this;
    }

    @Override
    public SqlDatabaseForElasticPoolImpl withPremiumRSEdition(SqlDatabasePremiumRSServiceObjective serviceObjective) {
        this.sqlDatabase.withEdition(DatabaseEdition.PREMIUM_RS);
        this.sqlDatabase.withServiceObjective(ServiceObjectiveName.fromString(serviceObjective.toString()));
        return this;
    }

    @Override
    public SqlDatabaseForElasticPoolImpl withPremiumRSEdition(SqlDatabasePremiumRSServiceObjective serviceObjective, SqlDatabasePremiumRSStorage maxStorageCapacity) {
        this.sqlDatabase.withEdition(DatabaseEdition.PREMIUM_RS);
        this.sqlDatabase.withServiceObjective(ServiceObjectiveName.fromString(serviceObjective.toString()));
        this.sqlDatabase.withMaxSizeBytes(maxStorageCapacity.capacityInMB());
        return this;
    }

    @Override
    public SqlDatabaseForElasticPoolImpl withServiceObjective(ServiceObjectiveName serviceLevelObjective) {
        this.sqlDatabase.withServiceObjective(serviceLevelObjective);
        return this;
    }
}
