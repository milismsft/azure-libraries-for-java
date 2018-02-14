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
        SqlDatabaseOperations.DefinitionStages.Blank,
        SqlDatabaseOperations.DefinitionStages.WithSqlServer,
        SqlDatabaseOperations.DefinitionStages.WithAllDifferentOptions,
        SqlDatabaseOperations.DefinitionStages.WithElasticPoolName,
        SqlDatabaseOperations.DefinitionStages.WithSourceDatabaseId,
        SqlDatabaseOperations.DefinitionStages.WithCreateMode,
        SqlDatabaseOperations.DefinitionStages.WithCreate,
        SqlDatabaseOperations.DefinitionStages.WithCreateWithLessOptions {
    }

    /**
     * Grouping of all the SQL database definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of the SQL database definition.
         */
        interface Blank extends SqlDatabaseOperations.DefinitionStages.WithAllDifferentOptions {
        }

        /**
         * The stage of the SQL Database rule definition allowing to specify the parent resource group, SQL server and location.
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
            SqlDatabaseOperations.DefinitionStages.WithAllDifferentOptions withExistingSqlServer(String resourceGroupName, String sqlServerName, String location);

            /**
             * Sets the parent SQL server for the new SQL Database.
             *
             * @param sqlServer the parent SQL server ID
             * @return The next stage of the definition.
             */
            SqlDatabaseOperations.DefinitionStages.WithAllDifferentOptions withSqlServer(SqlServer sqlServer);
        }

        /**
         * The SQL database interface with all starting options for definition.
         */
        interface WithAllDifferentOptions extends
            SqlDatabaseOperations.DefinitionStages.WithElasticPoolName,
            SqlDatabaseOperations.DefinitionStages.WithSourceDatabaseId,
            SqlDatabaseOperations.DefinitionStages.WithCreate {
        }

        /**
         * The SQL Database definition to set the elastic pool for database.
         */
        interface WithElasticPoolName {
            /**
             * Sets the existing elastic pool for the SQLDatabase.
             *
             * @param elasticPoolName for the SQL Database
             * @return The next stage of the definition.
             */
            SqlDatabaseOperations.DefinitionStages.WithExistingDatabase withExistingElasticPool(String elasticPoolName);

            /**
             * Sets the existing elastic pool for the SQLDatabase.
             *
             * @param sqlElasticPool for the SQL Database
             * @return The next stage of the definition.
             */
            SqlDatabaseOperations.DefinitionStages.WithExistingDatabase withExistingElasticPool(SqlElasticPool sqlElasticPool);

            /**
             * Sets the new elastic pool for the SQLDatabase, this will create a new elastic pool while creating database.
             *
             * @param sqlElasticPool creatable definition for new elastic pool to be created for the SQL Database
             * @return The next stage of the definition.
             */
            SqlDatabaseOperations.DefinitionStages.WithExistingDatabase withNewElasticPool(Creatable<SqlElasticPool> sqlElasticPool);

            /**
             * Begins the definition of a new SQL Elastic Pool to be added to this database parent SQL server.
             *
             * @param elasticPoolName the name of the new SQL Elastic Pool
             * @return the first stage of the new SQL Elastic Pool definition
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPool.DefinitionStages.Blank<SqlDatabaseOperations.DefinitionStages.WithExistingDatabase> defineElasticPool(String elasticPoolName);
        }

        /**
         * The stage to decide whether using existing database or not.
         */
        interface WithExistingDatabase extends
            SqlDatabaseOperations.DefinitionStages.WithSourceDatabaseId,
            SqlDatabaseOperations.DefinitionStages.WithCreateWithElasticPoolOptions {
        }

        /**
         * The SQL Database definition to set the source database id for database.
         */
        interface WithSourceDatabaseId {
            /**
             * Creates a new database from a previously deleted database (see restorable dropped database).
             * <p>
             * Collation, Edition, and MaxSizeBytes must remain the same while the link is
             * active. Values specified for these parameters will be ignored.
             *
             * @param restorableDroppedDatabase the restorable dropped database
             * @return The next stage of the definition.
             */
            SqlDatabaseOperations.DefinitionStages.WithCreateWithLessOptions fromRestorableDroppedDatabase(SqlRestorableDroppedDatabase restorableDroppedDatabase);

            /**
             * Sets the resource if of source database for the SQL Database.
             * <p>
             * Collation, Edition, and MaxSizeBytes must remain the same while the link is
             * active. Values specified for these parameters will be ignored.
             *
             * @param sourceDatabaseId id of the source database
             * @return The next stage of the definition.
             */
            SqlDatabaseOperations.DefinitionStages.WithCreateMode withSourceDatabase(String sourceDatabaseId);

            /**
             * Sets the resource if of source database for the SQL Database.
             * <p>
             * Collation, Edition, and MaxSizeBytes must remain the same while the link is
             * active. Values specified for these parameters will be ignored.
             *
             * @param sourceDatabase instance of the source database
             * @return The next stage of the definition.
             */
            SqlDatabaseOperations.DefinitionStages.WithCreateMode withSourceDatabase(SqlDatabase sourceDatabase);
        }

        /**
         * The SQL Database definition to set the create mode for database.
         */
        interface WithCreateMode {
            /**
             * Sets the create mode for the SQL Database.
             *
             * @param createMode create mode for the database, should not be default in this flow
             * @return The next stage of the definition.
             */
            SqlDatabaseOperations.DefinitionStages.WithCreateWithLessOptions withMode(CreateMode createMode);
        }

        /**
         *
         */
        interface WithCreateWithElasticPoolOptions extends
            SqlDatabaseOperations.DefinitionStages.WithCollation,
            SqlDatabaseOperations.DefinitionStages.WithMaxSizeBytes,
            SqlDatabaseOperations.DefinitionStages.WithCreateWithLessOptions {

        }

        /**
         * The SQL Database definition to set the collation for database.
         */
        interface WithCollationAllCreateOptions {
            /**
             * Sets the collation for the SQL Database.
             *
             * @param collation collation to be set for database
             * @return The next stage of the definition
             */
            SqlDatabaseOperations.DefinitionStages.WithCreate withCollation(String collation);
        }

        /**
         * The SQL Database definition to set the collation for database.
         */
        interface WithCollation {
            /**
             * Sets the collation for the SQL Database.
             *
             * @param collation collation to be set for database
             * @return The next stage of the definition
             */
            SqlDatabaseOperations.DefinitionStages.WithCreateWithElasticPoolOptions withCollation(String collation);
        }

        /**
         * The SQL Database definition to set the edition for database.
         */
        interface WithEdition {
            /**
             * Sets the edition for the SQL Database.
             *
             * @param edition edition to be set for database
             * @return The next stage of the definition
             */
            @Deprecated
            SqlDatabaseOperations.DefinitionStages.WithCreate withEdition(DatabaseEdition edition);

            /**
             * Sets a "Basic" edition for the SQL Database.
             *
             * @return The next stage of the definition
             */
            SqlDatabaseOperations.DefinitionStages.WithCreate withBasicEdition();

            /**
             * Sets a "Standard" edition for the SQL Database.
             *
             * @param serviceObjective edition to be set for database
             * @return The next stage of the definition
             */
            SqlDatabaseOperations.DefinitionStages.WithCreate withStandardEdition(SqlDatabaseStandardServiceObjective serviceObjective);

            /**
             * Sets a "Standard" edition and maximum storage capacity for the SQL Database.
             *
             * @param serviceObjective edition to be set for database
             * @param maxStorageCapacity edition to be set for database
             * @return The next stage of the definition
             */
            SqlDatabaseOperations.DefinitionStages.WithCreate withStandardEdition(SqlDatabaseStandardServiceObjective serviceObjective, SqlDatabaseStandardStorage maxStorageCapacity);

            /**
             * Sets a "Premium" edition for the SQL Database.
             *
             * @param serviceObjective edition to be set for database
             * @return The next stage of the definition
             */
            SqlDatabaseOperations.DefinitionStages.WithCreate withPremiumEdition(SqlDatabasePremiumServiceObjective serviceObjective);

            /**
             * Sets a "Premium" edition and maximum storage capacity for the SQL Database.
             *
             * @param serviceObjective edition to be set for database
             * @param maxStorageCapacity edition to be set for database
             * @return The next stage of the definition
             */
            SqlDatabaseOperations.DefinitionStages.WithCreate withPremiumEdition(SqlDatabasePremiumServiceObjective serviceObjective, SqlDatabasePremiumStorage maxStorageCapacity);

            /**
             * Sets a "PremiumRS" edition for the SQL Database.
             *
             * @param serviceObjective edition to be set for database
             * @return The next stage of the definition
             */
            SqlDatabaseOperations.DefinitionStages.WithCreate withPremiumRSEdition(SqlDatabasePremiumRSServiceObjective serviceObjective);

            /**
             * Sets a "PremiumRS" edition and maximum storage capacity for the SQL Database.
             *
             * @param serviceObjective edition to be set for database
             * @param maxStorageCapacity edition to be set for database
             * @return The next stage of the definition
             */
            SqlDatabaseOperations.DefinitionStages.WithCreate withPremiumRSEdition(SqlDatabasePremiumRSServiceObjective serviceObjective, SqlDatabasePremiumRSStorage maxStorageCapacity);
        }

        /**
         * The SQL Database definition to set the Max Size in Bytes for database.
         */
        interface WithMaxSizeBytesAllCreateOptions {
            /**
             * Sets the max size in bytes for SQL Database.
             *
             * @param maxSizeBytes max size of the Azure SQL Database expressed in bytes. Note: Only
             * the following sizes are supported (in addition to limitations being
             * placed on each edition): { 100 MB | 500 MB |1 GB | 5 GB | 10 GB | 20
             * GB | 30 GB … 150 GB | 200 GB … 500 GB }
             * @return The next stage of the definition.
             */
            @Deprecated
            SqlDatabaseOperations.DefinitionStages.WithCreate withMaxSizeBytes(long maxSizeBytes);
        }

        /**
         * The SQL Database definition to set the Max Size in Bytes for database.
         */
        interface WithMaxSizeBytes {
            /**
             * Sets the max size in bytes for SQL Database.
             *
             * @param maxSizeBytes max size of the Azure SQL Database expressed in bytes. Note: Only
             * the following sizes are supported (in addition to limitations being
             * placed on each edition): { 100 MB | 500 MB |1 GB | 5 GB | 10 GB | 20
             * GB | 30 GB … 150 GB | 200 GB … 500 GB }
             * @return The next stage of the definition.
             */
            SqlDatabaseOperations.DefinitionStages.WithCreateWithElasticPoolOptions withMaxSizeBytes(long maxSizeBytes);
        }

        /**
         * The SQL Database definition to set the service level objective.
         */
        interface WithServiceObjective {
            /**
             * Sets the service level objective for the SQL Database.
             *
             * @param serviceLevelObjective service level objected for the SQL Database
             * @return The next stage of the definition.
             */
            @Deprecated
            SqlDatabaseOperations.DefinitionStages.WithCreate withServiceObjective(ServiceObjectiveName serviceLevelObjective);
        }

        /**
         * A SQL Database definition with sufficient inputs to create a new
         * SQL database in the cloud, but exposing additional optional settings to
         * specify.
         */
        interface WithCreate extends
            SqlDatabaseOperations.DefinitionStages.WithServiceObjective,
            SqlDatabaseOperations.DefinitionStages.WithEdition,
            SqlDatabaseOperations.DefinitionStages.WithCollationAllCreateOptions,
            SqlDatabaseOperations.DefinitionStages.WithMaxSizeBytesAllCreateOptions,
            SqlDatabaseOperations.DefinitionStages.WithCreateWithLessOptions {
        }

        /**
         * A SQL Database definition with sufficient inputs to create a new
         * SQL Server in the cloud, but exposing additional optional inputs to
         * specify.
         */
        interface WithCreateWithLessOptions extends
            Resource.DefinitionWithTags<SqlDatabase>,
            Creatable<SqlDatabase> {
        }
    }

    /**
     * Grouping of the Azure SQL Database rule common actions.
     */
    interface SqlDatabaseActionsDefinition extends SqlChildrenActionsDefinition<SqlDatabase> {
        /**
         * Begins the definition of a new SQL Database to be added to this server.
         *
         * @param databaseName the name of the new SQL Database
         * @return the first stage of the new SQL Database definition
         */
        SqlDatabaseOperations.DefinitionStages.WithAllDifferentOptions define(String databaseName);
    }
}