/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.apigeneration.Beta;
import com.microsoft.azure.management.apigeneration.Fluent;
import com.microsoft.azure.management.apigeneration.Method;
import com.microsoft.azure.management.resources.fluentcore.collection.SupportsCreating;

/**
 * A representation of the Azure SQL Elastic Pool operations.
 */
@Fluent
@Beta(Beta.SinceVersion.V2_0_0)
public interface SqlElasticPoolOperations extends
    SupportsCreating<SqlElasticPoolOperations.DefinitionStages.WithSqlServer>,
    SqlChildrenOperations<SqlElasticPool> {

    /**
     * Container interface for all the definitions that need to be implemented.
     */
    interface SqlElasticPoolOperationsDefinition extends
        SqlElasticPoolOperations.DefinitionStages.WithSqlServer,
        SqlElasticPoolOperations.DefinitionStages.WithEdition,
        SqlElasticPoolOperations.DefinitionStages.WithBasicEdition,
        SqlElasticPoolOperations.DefinitionStages.WithStandardEdition,
        SqlElasticPoolOperations.DefinitionStages.WithPremiumEdition
    {
    }

    /**
     * Grouping of all the SQL Elastic Pool definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of the SQL Server Elastic Pool definition.
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
            SqlElasticPoolOperations.DefinitionStages.WithEdition withExistingSqlServer(String resourceGroupName, String sqlServerName, String location);

            /**
             * Sets the parent SQL server for the new Elastic Pool.
             *
             * @param sqlServer the parent SQL server ID
             * @return The next stage of the definition.
             */
            SqlElasticPoolOperations.DefinitionStages.WithEdition withSqlServer(SqlServer sqlServer);
        }

        /**
         * The SQL Elastic Pool definition to set the edition type.
         */
        interface WithEdition {
            /**
             * Sets the edition for the SQL Elastic Pool.
             *
             * @param edition edition to be set for Elastic Pool.
             * @return The next stage of the definition.
             */
            @Deprecated
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPoolOperations.DefinitionStages.WithCreate withEdition(ElasticPoolEdition edition);

            /**
             * Sets the basic edition for the SQL Elastic Pool.
             *
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            @Method
            SqlElasticPoolOperations.DefinitionStages.WithBasicEdition withBasicPool();

            /**
             * Sets the standard edition for the SQL Elastic Pool.
             *
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            @Method
            SqlElasticPoolOperations.DefinitionStages.WithStandardEdition withStandardPool();

            /**
             * Sets the premium edition for the SQL Elastic Pool.
             *
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            @Method
            SqlElasticPoolOperations.DefinitionStages.WithPremiumEdition withPremiumPool();
        }

        /**
         * The SQL Elastic Pool definition to set the eDTU and storage capacity limits for a basic pool.
         */
        interface WithBasicEdition extends SqlElasticPoolOperations.DefinitionStages.WithCreate {
            /**
             * Sets the total shared eDTU for the SQL Azure Database Elastic Pool.
             *
             * @param eDTU total shared eDTU for the SQL Azure Database Elastic Pool
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPoolOperations.DefinitionStages.WithBasicEdition withReservedDtu(SqlElasticPoolBasicEDTUs eDTU);

            /**
             * Sets the maximum number of eDTU a database in the pool can consume.
             *
             * @param eDTU maximum eDTU a database in the pool can consume
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPoolOperations.DefinitionStages.WithBasicEdition withDatabaseDtuMax(SqlElasticPoolBasicMaxEDTUs eDTU);

            /**
             * Sets the minimum number of eDTU for each database in the pool are regardless of its activity.
             *
             * @param eDTU minimum eDTU for all SQL Azure databases
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPoolOperations.DefinitionStages.WithBasicEdition withDatabaseDtuMin(SqlElasticPoolBasicMinEDTUs eDTU);
        }

        /**
         * The SQL Elastic Pool definition to set the eDTU and storage capacity limits for a standard pool.
         */
        interface WithStandardEdition extends SqlElasticPoolOperations.DefinitionStages.WithCreate {
            /**
             * Sets the total shared eDTU for the SQL Azure Database Elastic Pool.
             *
             * @param eDTU total shared eDTU for the SQL Azure Database Elastic Pool
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPoolOperations.DefinitionStages.WithStandardEdition withReservedDtu(SqlElasticPoolStandardEDTUs eDTU);

            /**
             * Sets the maximum number of eDTU a database in the pool can consume.
             *
             * @param eDTU maximum eDTU a database in the pool can consume
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPoolOperations.DefinitionStages.WithStandardEdition withDatabaseDtuMax(SqlElasticPoolStandardMaxEDTUs eDTU);

            /**
             * Sets the minimum number of eDTU for each database in the pool are regardless of its activity.
             *
             * @param eDTU minimum eDTU for all SQL Azure databases
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPoolOperations.DefinitionStages.WithStandardEdition withDatabaseDtuMin(SqlElasticPoolStandardMinEDTUs eDTU);

            /**
             * Sets the storage capacity for the SQL Azure Database Elastic Pool.
             *
             * @param storageCapacity storage capacity for the SQL Azure Database Elastic Pool
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPoolOperations.DefinitionStages.WithStandardEdition withStorageCapacity(SqlElasticPoolStandardStorage storageCapacity);
        }

        /**
         * The SQL Elastic Pool definition to set the eDTU and storage capacity limits for a premium pool.
         */
        interface WithPremiumEdition extends SqlElasticPoolOperations.DefinitionStages.WithCreate {
            /**
             * Sets the total shared eDTU for the SQL Azure Database Elastic Pool.
             *
             * @param eDTU total shared eDTU for the SQL Azure Database Elastic Pool
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPoolOperations.DefinitionStages.WithPremiumEdition withReservedDtu(SqlElasticPoolPremiumEDTUs eDTU);

            /**
             * Sets the maximum number of eDTU a database in the pool can consume.
             *
             * @param eDTU maximum eDTU a database in the pool can consume
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPoolOperations.DefinitionStages.WithPremiumEdition withDatabaseDtuMax(SqlElasticPoolPremiumMaxEDTUs eDTU);

            /**
             * Sets the minimum number of eDTU for each database in the pool are regardless of its activity.
             *
             * @param eDTU minimum eDTU for all SQL Azure databases
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPoolOperations.DefinitionStages.WithPremiumEdition withDatabaseDtuMin(SqlElasticPoolPremiumMinEDTUs eDTU);

            /**
             * Sets the storage capacity for the SQL Azure Database Elastic Pool.
             *
             * @param storageCapacity storage capacity for the SQL Azure Database Elastic Pool
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPoolOperations.DefinitionStages.WithPremiumEdition withStorageCapacity(SqlElasticPoolPremiumSorage storageCapacity);
        }

        /**
         * The SQL Elastic Pool definition to set the minimum DTU for database.
         */
        interface WithDatabaseDtuMin {
            /**
             * Sets the minimum DTU all SQL Azure Databases are guaranteed.
             *
             * @param databaseDtuMin minimum DTU for all SQL Azure databases
             * @return The next stage of the definition.
             */
            @Deprecated
            SqlElasticPoolOperations.DefinitionStages.WithCreate withDatabaseDtuMin(int databaseDtuMin);
        }

        /**
         * The SQL Elastic Pool definition to set the maximum DTU for one database.
         */
        interface WithDatabaseDtuMax {
            /**
             * Sets the maximum DTU any one SQL Azure Database can consume.
             *
             * @param databaseDtuMax maximum DTU any one SQL Azure Database can consume
             * @return The next stage of the definition.
             */
            @Deprecated
            SqlElasticPoolOperations.DefinitionStages.WithCreate withDatabaseDtuMax(int databaseDtuMax);
        }

        /**
         * The SQL Elastic Pool definition to set the number of shared DTU for elastic pool.
         */
        interface WithDtu {
            /**
             * Sets the total shared DTU for the SQL Azure Database Elastic Pool.
             *
             * @param dtu total shared DTU for the SQL Azure Database Elastic Pool
             * @return The next stage of the definition.
             */
            @Deprecated
            SqlElasticPoolOperations.DefinitionStages.WithCreate withDtu(int dtu);
        }

        /**
         * The SQL Elastic Pool definition to set the storage limit for the SQL Azure Database Elastic Pool in MB.
         */
        interface WithStorageCapacity {
            /**
             * Sets the storage limit for the SQL Azure Database Elastic Pool in MB.
             *
             * @param storageMB storage limit for the SQL Azure Database Elastic Pool in MB
             * @return The next stage of the definition.
             */
            @Deprecated
            SqlElasticPoolOperations.DefinitionStages.WithCreate withStorageCapacity(int storageMB);
        }

        /**
         * The SQL Elastic Pool definition to add the Database in the elastic pool.
         */
        interface WithDatabase {
            /**
             * Creates a new database in the SQL elastic pool.
             *
             * @param databaseName name of the new database to be added in the elastic pool
             * @return The next stage of the definition.
             */
            SqlElasticPoolOperations.DefinitionStages.WithCreate withNewDatabase(String databaseName);

            /**
             * Adds an existing database in the SQL elastic pool.
             *
             * @param databaseName name of the existing database to be added in the elastic pool
             * @return The next stage of the definition.
             */
            SqlElasticPoolOperations.DefinitionStages.WithCreate withExistingDatabase(String databaseName);

            /**
             * Adds the database in the SQL elastic pool.
             *
             * @param database database instance to be added in SQL elastic pool
             * @return The next stage of the definition.
             */
            SqlElasticPoolOperations.DefinitionStages.WithCreate withExistingDatabase(SqlDatabase database);
        }

        /**
         * A SQL Server definition with sufficient inputs to create a new SQL Elastic Pool in the cloud,
         * but exposing additional optional inputs to specify.
         */
        interface WithCreate extends
            SqlChildrenCreatableDefinition<SqlElasticPool>,
            SqlElasticPoolOperations.DefinitionStages.WithDatabaseDtuMin,
            SqlElasticPoolOperations.DefinitionStages.WithDatabaseDtuMax,
            SqlElasticPoolOperations.DefinitionStages.WithDtu,
            SqlElasticPoolOperations.DefinitionStages.WithStorageCapacity,
            SqlElasticPoolOperations.DefinitionStages.WithDatabase {
        }
    }

    /**
     * Grouping of the Azure SQL Server Firewall rule common actions.
     */
    interface SqlElasticPoolActionsDefinition extends SqlChildrenActionsDefinition<SqlElasticPool> {
    }
}
