/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.apigeneration.Beta;
import com.microsoft.azure.management.apigeneration.Fluent;
import com.microsoft.azure.management.resources.fluentcore.arm.models.Resource;
import com.microsoft.azure.management.resources.fluentcore.collection.SupportsCreating;
import com.microsoft.azure.management.resources.fluentcore.model.Creatable;

/**
 * A representation of the Azure SQL Database operations.
 */
@Fluent
@Beta(Beta.SinceVersion.V2_0_0)
public interface SqlDatabaseOperations extends
    SupportsCreating<SqlDatabaseOperations.DefinitionStages.WithSqlServer>,
    SqlChildrenOperations<SqlDatabase> {

    /**
     * Container interface for all the definitions that need to be implemented.
     */
    interface SqlDatabaseOperationsDefinition extends
        SqlDatabaseOperations.DefinitionStages.WithSqlServer,
        SqlDatabaseOperations.DefinitionStages.WithIPAddressRange,
        SqlDatabaseOperations.DefinitionStages.WithElasticPool,
        SqlDatabaseOperations.DefinitionStages.WithCreate {
    }

    /**
     * Grouping of all the SQL Database definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of the SQL Database rule definition.
         */
        interface WithSqlServer {
            /**
             * Sets the parent SQL server name and resource group it belongs to.
             *
             * @param resourceGroupName the name of the resource group the parent SQL server
             * @param sqlServerName     the parent SQL server name
             * @param location          the parent SQL server location
             * @return The next stage of the definition.
             */
            WithIPAddressRange withExistingSqlServer(String resourceGroupName, String sqlServerName, String location);

            /**
             * Sets the parent SQL server for the new SQL Database.
             *
             * @param sqlServer the parent SQL server ID
             * @return The next stage of the definition.
             */
            WithIPAddressRange withSqlServer(SqlServer sqlServer);
        }

        /**
         * The SQL Database definition to set the IP address range for the parent SQL Server.
         */
        interface WithIPAddressRange {
            /**
             * Sets the starting IP address of SQL server's firewall rule.
             *
             * @param startIPAddress starting IP address in IPv4 format.
             * @param endIPAddress   starting IP address in IPv4 format.
             * @return The next stage of the definition.
             */
            SqlDatabaseOperations.DefinitionStages.WithCreate withIPAddressRange(String startIPAddress, String endIPAddress);
        }

        /**
         * The SQL Database definition to add the Database in the Elastic Pool.
         */
        interface WithElasticPool {
            /**
             * Adds the database in an existing SQL elastic pool.
             *
             * @param elasticPoolName name of the existing SQL Elastic Pool the current database will be added to
             * @return The next stage of the definition.
             */
            SqlDatabaseOperations.DefinitionStages.WithCreate withExistingElasticPool(String elasticPoolName);

            /**
             * Adds the database in an existing SQL elastic pool.
             *
             * @param elasticPool the SQL Elastic Pool instance the current database will be added to
             * @return The next stage of the definition.
             */
            SqlDatabaseOperations.DefinitionStages.WithCreate withExistingDatabase(SqlElasticPool elasticPool);

            /**
             * Begins the definition of a new SQL Elastic Pool to be added to this database parent SQL server.
             *
             * @param name the name of the new SQL Elastic Pool
             * @return the first stage of the new SQL Elastic Pool definition
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPool.DefinitionStages.Blank<SqlDatabaseOperations.DefinitionStages.WithCreate> defineElasticPool(String name);
        }

        /**
         * The final stage of the SQL Database definition.
         */
        interface WithCreate extends
            Resource.DefinitionWithTags<SqlDatabase>,
            Creatable<SqlDatabase> {
        }
    }

    /**
     * Grouping of the Azure SQL Database rule common actions.
     */
    interface SqlDatabaseActionsDefinition extends SqlChildrenActionsDefinition<SqlDatabase> {
    }
}