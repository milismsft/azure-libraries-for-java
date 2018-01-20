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

    /**
     * Creates a new ExternalNonInlineChildResourcesImpl.
     *
     * @param parent            the parent Azure resource
     * @param childResourceName the child resource name
     */
    protected SqlDatabasesAsExternalChildResourcesImpl(SqlServerImpl parent, String childResourceName) {
        super(parent, parent.taskGroup(), childResourceName);
    }

    /**
     * Creates a new ExternalNonInlineChildResourcesImpl.
     *
     * @param parent            the parent Azure resource
     * @param childResourceName the child resource name
     */
    protected SqlDatabasesAsExternalChildResourcesImpl(SqlElasticPoolImpl parent, String childResourceName) {
        super(null, parent.taskGroup(), childResourceName);
    }

    SqlDatabaseImpl defineElasticPool(String name) {
        return prepareDefine(new SqlDatabaseImpl(name, this.parent(), new DatabaseInner(), this.parent().manager()));
    }

    SqlDatabaseImpl updateElasticPool(String name) {
        return prepareUpdate(new SqlDatabaseImpl(name, this.parent(), new DatabaseInner(), this.parent().manager()));
    }

    void withoutElasticPool(String name) {
        prepareRemove(new SqlDatabaseImpl(name, this.parent(), new DatabaseInner(), this.parent().manager()));
    }
}
