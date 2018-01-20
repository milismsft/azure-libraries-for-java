/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.apigeneration.Beta;
import com.microsoft.azure.management.apigeneration.Fluent;
import com.microsoft.azure.management.apigeneration.Method;
import com.microsoft.azure.management.resources.fluentcore.arm.models.ExternalChildResource;
import com.microsoft.azure.management.resources.fluentcore.model.Appliable;
import com.microsoft.azure.management.resources.fluentcore.model.Attachable;
import com.microsoft.azure.management.resources.fluentcore.model.HasInner;
import com.microsoft.azure.management.resources.fluentcore.model.Refreshable;
import com.microsoft.azure.management.resources.fluentcore.model.Updatable;
import com.microsoft.azure.management.sql.implementation.ElasticPoolInner;

import org.joda.time.DateTime;

import java.util.List;

/**
 * An immutable client-side representation of an Azure SQL Elastic Pool.
 */
@Fluent
@Beta(Beta.SinceVersion.V2_0_0)
public interface SqlElasticPool
    extends
        ExternalChildResource<SqlElasticPool, SqlServer>,
        HasInner<ElasticPoolInner>,
        Refreshable<SqlElasticPool>,
        Updatable<SqlElasticPool.Update> {

    /**
     * @return name of the SQL Server to which this elastic pool belongs
     */
    String sqlServerName();

    /**
     * @return the creation date of the Azure SQL Elastic Pool
     */
    DateTime creationDate();

    /**
     * @return the state of the Azure SQL Elastic Pool
     */
    ElasticPoolState state();

    /**
     * @return the edition of Azure SQL Elastic Pool
     */
    ElasticPoolEdition edition();

    /**
     * @return The total shared DTU for the SQL Azure Database Elastic Pool
     */
    int dtu();

    /**
     * @return the maximum DTU any one SQL Azure database can consume.
     */
    int databaseDtuMax();

    /**
     * @return the minimum DTU all SQL Azure Databases are guaranteed
     */
    int databaseDtuMin();

    /**
     * @return the storage limit for the SQL Azure Database Elastic Pool in MB
     */
    @Deprecated
    int storageMB();

    /**
     * @return the storage capacity limit for the SQL Azure Database Elastic Pool in MB
     */
    int storageCapacityInMB();

    /**
     * @return the elastic pool's database metrics
     */
    List<SqlDatabaseMetric> listDatabaseMetrics();

    /**
     * @return the elastic pool's metric definitions
     */
    List<SqlDatabaseMetricDefinition> listDatabaseMetricDefinitions();

//    /**
//     * @return the information about elastic pool activities
//     */
//    List<ElasticPoolActivity> listActivities();
//
//    /**
//     * @return the information about elastic pool database activities
//     */
//    List<ElasticPoolDatabaseActivity> listDatabaseActivities();
//
    /**
     * @return the information about databases in elastic pool
     */
    List<SqlDatabase> listDatabases();

    /**
     * Gets the specific database in the elastic pool.
     *
     * @param databaseName name of the database to look into
     * @return the information about specific database in elastic pool
     */
    SqlDatabase getDatabase(String databaseName);

    /**
     * Adds a new SQL Database to the Elastic Pool.
     *
     * @param databaseName name of the database
     * @return the database
     */
    SqlDatabase addNewDatabase(String databaseName);

    /**
     * Adds an existing SQL Database to the Elastic Pool.
     *
     * @param databaseName name of the database
     * @return the database
     */
    SqlDatabase addExistingDatabase(String databaseName);

    /**
     * Removes an existing SQL Database from the Elastic Pool.
     *
     * @param databaseName name of the database
     * @return the database
     */
    SqlDatabase removeDatabase(String databaseName);

    /**
     * Deletes the elastic pool from the server.
     */
    @Method
    void delete();


    /**************************************************************
     * Fluent interfaces to provision a Sql Elastic pool
     **************************************************************/

    /**
     * Container interface for all the definitions that need to be implemented.
     */
    interface SqlElasticPoolDefinition<ParentT> extends
            DefinitionStages.Blank<ParentT>,
            DefinitionStages.WithEdition<ParentT>,
            DefinitionStages.WithBasicEdition<ParentT>,
            DefinitionStages.WithStandardEdition<ParentT>,
            DefinitionStages.WithPremiumEdition<ParentT>,
            DefinitionStages.WithAttach<ParentT> {
    }

    /**
     * Grouping of all the storage account definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of the SQL Server definition.
         *
         * @param <ParentT> the stage of the parent definition to return to after attaching this definition
         */
        interface Blank<ParentT> extends SqlElasticPool.DefinitionStages.WithEdition<ParentT> {
        }

        /**
         * The SQL Elastic Pool definition to set the edition for database.
         *
         * @param <ParentT> the stage of the parent definition to return to after attaching this definition
         */
        interface WithEdition<ParentT> {
            /**
             * Sets the edition for the SQL Elastic Pool.
             *
             * @param edition edition to be set for elastic pool.
             * @return The next stage of the definition.
             */
            @Deprecated
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPool.DefinitionStages.WithAttach<ParentT> withEdition(ElasticPoolEdition edition);

            /**
             * Sets the basic edition for the SQL Elastic Pool.
             *
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            @Method
            SqlElasticPool.DefinitionStages.WithBasicEdition<ParentT> withBasicPool();

            /**
             * Sets the standard edition for the SQL Elastic Pool.
             *
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            @Method
            SqlElasticPool.DefinitionStages.WithStandardEdition<ParentT> withStandardPool();

            /**
             * Sets the premium edition for the SQL Elastic Pool.
             *
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            @Method
            SqlElasticPool.DefinitionStages.WithPremiumEdition<ParentT> withPremiumPool();
        }

        /**
         * The SQL Elastic Pool definition to set the eDTU and storage capacity limits for a basic pool.
         *
         * @param <ParentT> the stage of the parent definition to return to after attaching this definition
         */
        interface WithBasicEdition<ParentT> extends SqlElasticPool.DefinitionStages.WithAttach<ParentT> {
            /**
             * Sets the total shared eDTU for the SQL Azure Database Elastic Pool.
             *
             * @param eDTU total shared eDTU for the SQL Azure Database Elastic Pool
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPool.DefinitionStages.WithBasicEdition<ParentT> withReservedDtu(SqlElasticPoolBasicEDTUs eDTU);

            /**
             * Sets the maximum number of eDTU a database in the pool can consume.
             *
             * @param eDTU maximum eDTU a database in the pool can consume
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPool.DefinitionStages.WithBasicEdition<ParentT> withDatabaseDtuMax(SqlElasticPoolBasicMaxEDTUs eDTU);

            /**
             * Sets the minimum number of eDTU for each database in the pool are regardless of its activity.
             *
             * @param eDTU minimum eDTU for all SQL Azure databases
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPool.DefinitionStages.WithBasicEdition<ParentT> withDatabaseDtuMin(SqlElasticPoolBasicMinEDTUs eDTU);
        }

        /**
         * The SQL Elastic Pool definition to set the eDTU and storage capacity limits for a standard pool.
         *
         * @param <ParentT> the stage of the parent definition to return to after attaching this definition
         */
        interface WithStandardEdition<ParentT> extends SqlElasticPool.DefinitionStages.WithAttach<ParentT> {
            /**
             * Sets the total shared eDTU for the SQL Azure Database Elastic Pool.
             *
             * @param eDTU total shared eDTU for the SQL Azure Database Elastic Pool
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPool.DefinitionStages.WithStandardEdition<ParentT> withReservedDtu(SqlElasticPoolStandardEDTUs eDTU);

            /**
             * Sets the maximum number of eDTU a database in the pool can consume.
             *
             * @param eDTU maximum eDTU a database in the pool can consume
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPool.DefinitionStages.WithStandardEdition<ParentT> withDatabaseDtuMax(SqlElasticPoolStandardMaxEDTUs eDTU);

            /**
             * Sets the minimum number of eDTU for each database in the pool are regardless of its activity.
             *
             * @param eDTU minimum eDTU for all SQL Azure databases
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPool.DefinitionStages.WithStandardEdition<ParentT> withDatabaseDtuMin(SqlElasticPoolStandardMinEDTUs eDTU);

            /**
             * Sets the storage capacity for the SQL Azure Database Elastic Pool.
             *
             * @param storageCapacity storage capacity for the SQL Azure Database Elastic Pool
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPool.DefinitionStages.WithStandardEdition<ParentT> withStorageCapacity(SqlElasticPoolStandardStorage storageCapacity);
        }

        /**
         * The SQL Elastic Pool definition to set the eDTU and storage capacity limits for a premium pool.
         *
         * @param <ParentT> the stage of the parent definition to return to after attaching this definition
         */
        interface WithPremiumEdition<ParentT> extends SqlElasticPool.DefinitionStages.WithAttach<ParentT> {
            /**
             * Sets the total shared eDTU for the SQL Azure Database Elastic Pool.
             *
             * @param eDTU total shared eDTU for the SQL Azure Database Elastic Pool
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPool.DefinitionStages.WithPremiumEdition<ParentT> withReservedDtu(SqlElasticPoolPremiumEDTUs eDTU);

            /**
             * Sets the maximum number of eDTU a database in the pool can consume.
             *
             * @param eDTU maximum eDTU a database in the pool can consume
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPool.DefinitionStages.WithPremiumEdition<ParentT> withDatabaseDtuMax(SqlElasticPoolPremiumMaxEDTUs eDTU);

            /**
             * Sets the minimum number of eDTU for each database in the pool are regardless of its activity.
             *
             * @param eDTU minimum eDTU for all SQL Azure databases
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPool.DefinitionStages.WithPremiumEdition<ParentT> withDatabaseDtuMin(SqlElasticPoolPremiumMinEDTUs eDTU);

            /**
             * Sets the storage capacity for the SQL Azure Database Elastic Pool.
             *
             * @param storageCapacity storage capacity for the SQL Azure Database Elastic Pool
             * @return The next stage of the definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPool.DefinitionStages.WithPremiumEdition<ParentT> withStorageCapacity(SqlElasticPoolPremiumSorage storageCapacity);
        }

        /**
         * The SQL Elastic Pool definition to set the minimum DTU for database.
         *
         * @param <ParentT> the stage of the parent definition to return to after attaching this definition
         */
        interface WithDatabaseDtuMin<ParentT> {
            /**
             * Sets the minimum DTU all SQL Azure Databases are guaranteed.
             *
             * @param databaseDtuMin minimum DTU for all SQL Azure databases
             * @return The next stage of the definition.
             */
            @Deprecated
            SqlElasticPool.DefinitionStages.WithAttach<ParentT> withDatabaseDtuMin(int databaseDtuMin);
        }

        /**
         * The SQL Elastic Pool definition to set the maximum DTU for one database.
         *
         * @param <ParentT> the stage of the parent definition to return to after attaching this definition
         */
        interface WithDatabaseDtuMax<ParentT> {
            /**
             * Sets the maximum DTU any one SQL Azure Database can consume.
             *
             * @param databaseDtuMax maximum DTU any one SQL Azure Database can consume
             * @return The next stage of the definition.
             */
            @Deprecated
            SqlElasticPool.DefinitionStages.WithAttach<ParentT> withDatabaseDtuMax(int databaseDtuMax);
        }

        /**
         * The SQL Elastic Pool definition to set the number of shared DTU for elastic pool.
         *
         * @param <ParentT> the stage of the parent definition to return to after attaching this definition
         */
        interface WithDtu<ParentT> {
            /**
             * Sets the total shared DTU for the SQL Azure Database Elastic Pool.
             *
             * @param dtu total shared DTU for the SQL Azure Database Elastic Pool
             * @return The next stage of the definition.
             */
            @Deprecated
            SqlElasticPool.DefinitionStages.WithAttach<ParentT> withDtu(int dtu);
        }

        /**
         * The SQL Elastic Pool definition to set the storage limit for the SQL Azure Database Elastic Pool in MB.
         *
         * @param <ParentT> the stage of the parent definition to return to after attaching this definition
         */
        interface WithStorageCapacity<ParentT> {
            /**
             * Sets the storage limit for the SQL Azure Database Elastic Pool in MB.
             *
             * @param storageMB storage limit for the SQL Azure Database Elastic Pool in MB
             * @return The next stage of the definition.
             */
            @Deprecated
            SqlElasticPool.DefinitionStages.WithAttach<ParentT> withStorageCapacity(int storageMB);
        }

        /** The final stage of the SQL Elastic Pool definition.
         * <p>
         * At this stage, any remaining optional settings can be specified, or the SQL Elastic Pool definition
         * can be attached to the parent SQL Server definition.
         * @param <ParentT> the stage of the parent definition to return to after attaching this definition
         */
        interface WithAttach<ParentT> extends
                Attachable.InDefinition<ParentT>,
                WithDatabaseDtuMin<ParentT>,
                WithDatabaseDtuMax<ParentT>,
                WithDtu<ParentT>,
                WithStorageCapacity<ParentT> {
        }
    }

    /**
     * The template for a SQL Elastic Pool update operation, containing all the settings that can be modified.
     */
    interface Update extends
            UpdateStages.WithReservedDTUAndStorageCapacity,
            UpdateStages.WithDatabaseDtuMax,
            UpdateStages.WithDatabaseDtuMin,
            UpdateStages.WithDtu,
            UpdateStages.WithStorageCapacity,
            UpdateStages.WithDatabase,
            Appliable<SqlElasticPool> {
    }

    /**
     * Grouping of all the SQL Elastic Pool update stages.
     */
    interface UpdateStages {

        /**
         * The SQL Elastic Pool definition to set the minimum DTU for database.
         */
        interface WithDatabaseDtuMin {
            /**
             * Sets the minimum DTU all SQL Azure Databases are guaranteed.
             *
             * @param databaseDtuMin minimum DTU for all SQL Azure databases
             * @return The next stage of definition.
             */
            @Deprecated
            Update withDatabaseDtuMin(int databaseDtuMin);
        }

        /**
         * The SQL Elastic Pool definition to set the maximum DTU for one database.
         */
        interface WithDatabaseDtuMax {
            /**
             * Sets the maximum DTU any one SQL Azure Database can consume.
             *
             * @param databaseDtuMax maximum DTU any one SQL Azure Database can consume
             * @return The next stage of definition.
             */
            @Deprecated
            Update withDatabaseDtuMax(int databaseDtuMax);
        }

        /**
         * The SQL Elastic Pool definition to set the number of shared DTU for elastic pool.
         */
        interface WithDtu {
            /**
             * Sets the total shared DTU for the SQL Azure Database Elastic Pool.
             *
             * @param dtu total shared DTU for the SQL Azure Database Elastic Pool
             * @return The next stage of definition.
             */
            @Deprecated
            Update withDtu(int dtu);
        }

        /**
         * The SQL Elastic Pool definition to set the storage limit for the SQL Azure Database Elastic Pool in MB.
         */
        interface WithStorageCapacity {
            /**
             * Sets the storage limit for the SQL Azure Database Elastic Pool in MB.
             *
             * @param storageMB storage limit for the SQL Azure Database Elastic Pool in MB
             * @return The next stage of definition.
             */
            @Deprecated
            Update withStorageCapacity(int storageMB);
        }

        /**
         * The SQL Elastic Pool update definition to set the eDTU and storage capacity limits.
         */
        interface WithReservedDTUAndStorageCapacity {
            /**
             * Sets the total shared eDTU for the SQL Azure Database Elastic Pool.
             *
             * @param eDTU total shared eDTU for the SQL Azure Database Elastic Pool
             * @return The next stage of the update definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            Update withReservedDtu(SqlElasticPoolBasicEDTUs eDTU);

            /**
             * Sets the maximum number of eDTU a database in the pool can consume.
             *
             * @param eDTU maximum eDTU a database in the pool can consume
             * @return The next stage of the update definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            Update withDatabaseDtuMax(SqlElasticPoolBasicMaxEDTUs eDTU);

            /**
             * Sets the minimum number of eDTU for each database in the pool are regardless of its activity.
             *
             * @param eDTU minimum eDTU for all SQL Azure databases
             * @return The next stage of the update definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            Update withDatabaseDtuMin(SqlElasticPoolBasicMinEDTUs eDTU);

            /**
             * Sets the total shared eDTU for the SQL Azure Database Elastic Pool.
             *
             * @param eDTU total shared eDTU for the SQL Azure Database Elastic Pool
             * @return The next stage of the update definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            Update withReservedDtu(SqlElasticPoolStandardEDTUs eDTU);

            /**
             * Sets the maximum number of eDTU a database in the pool can consume.
             *
             * @param eDTU maximum eDTU a database in the pool can consume
             * @return The next stage of the update definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            Update withDatabaseDtuMax(SqlElasticPoolStandardMaxEDTUs eDTU);

            /**
             * Sets the minimum number of eDTU for each database in the pool are regardless of its activity.
             *
             * @param eDTU minimum eDTU for all SQL Azure databases
             * @return The next stage of the update definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            Update withDatabaseDtuMin(SqlElasticPoolStandardMinEDTUs eDTU);

            /**
             * Sets the storage capacity for the SQL Azure Database Elastic Pool.
             *
             * @param storageCapacity storage capacity for the SQL Azure Database Elastic Pool
             * @return The next stage of the update definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            Update withStorageCapacity(SqlElasticPoolStandardStorage storageCapacity);

            /**
             * Sets the total shared eDTU for the SQL Azure Database Elastic Pool.
             *
             * @param eDTU total shared eDTU for the SQL Azure Database Elastic Pool
             * @return The next stage of the update definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            Update withReservedDtu(SqlElasticPoolPremiumEDTUs eDTU);

            /**
             * Sets the maximum number of eDTU a database in the pool can consume.
             *
             * @param eDTU maximum eDTU a database in the pool can consume
             * @return The next stage of the update definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            Update withDatabaseDtuMax(SqlElasticPoolPremiumMaxEDTUs eDTU);

            /**
             * Sets the minimum number of eDTU for each database in the pool are regardless of its activity.
             *
             * @param eDTU minimum eDTU for all SQL Azure databases
             * @return The next stage of the update definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            Update withDatabaseDtuMin(SqlElasticPoolPremiumMinEDTUs eDTU);

            /**
             * Sets the storage capacity for the SQL Azure Database Elastic Pool.
             *
             * @param storageCapacity storage capacity for the SQL Azure Database Elastic Pool
             * @return The next stage of the update definition.
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            Update withStorageCapacity(SqlElasticPoolPremiumSorage storageCapacity);
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
            Update withNewDatabase(String databaseName);

            /**
             * Adds an existing database in the SQL elastic pool.
             *
             * @param databaseName name of the existing database to be added in the elastic pool
             * @return The next stage of the definition.
             */
            Update withExistingDatabase(String databaseName);

            /**
             * Adds the database in the SQL elastic pool.
             *
             * @param database database instance to be added in SQL elastic pool
             * @return The next stage of the definition.
             */
            Update withExistingDatabase(SqlDatabase database);
        }
    }
}
