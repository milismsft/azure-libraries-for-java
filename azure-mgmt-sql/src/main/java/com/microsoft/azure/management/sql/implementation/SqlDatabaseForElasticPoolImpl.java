/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.sql.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.management.sql.CreateMode;
import com.microsoft.azure.management.sql.SqlDatabase;
import com.microsoft.azure.management.sql.SqlElasticPoolOperations;

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

    public SqlDatabaseForElasticPoolImpl(SqlElasticPoolImpl sqlElasticPool, SqlDatabaseImpl sqlDatabase) {
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
        return this;
    }

    @Override
    public SqlDatabaseForElasticPoolImpl withMaxSizeBytes(long maxSizeBytes) {
        return this;
    }
}
