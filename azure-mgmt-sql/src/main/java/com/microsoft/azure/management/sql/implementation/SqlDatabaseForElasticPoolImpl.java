/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.sql.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.management.sql.SqlDatabase;
import com.microsoft.azure.management.sql.SqlElasticPoolOperations;

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
        this.sqlElasticPool = sqlElasticPool;
        this.sqlDatabase = sqlDatabase;
    }

    @Override
    public SqlElasticPoolImpl attach() {
        this.sqlDatabase.addParentDependency(this.sqlElasticPool);
        return this.sqlElasticPool;
    }

    @Override
    public SqlDatabaseForElasticPoolImpl withIPAddress(String ipAddress) {
        this.sqlDatabase.withIPAddress(ipAddress);

        return this;
    }
}
