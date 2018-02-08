/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.apigeneration.Beta;
import com.microsoft.azure.management.apigeneration.Fluent;
import com.microsoft.azure.management.apigeneration.Method;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import com.microsoft.azure.management.resources.fluentcore.arm.models.ExternalChildResource;
import com.microsoft.azure.management.resources.fluentcore.arm.models.Resource;
import com.microsoft.azure.management.resources.fluentcore.model.Appliable;
import com.microsoft.azure.management.resources.fluentcore.model.Attachable;
import com.microsoft.azure.management.resources.fluentcore.model.Creatable;
import com.microsoft.azure.management.resources.fluentcore.model.HasInner;
import com.microsoft.azure.management.resources.fluentcore.model.Refreshable;
import com.microsoft.azure.management.resources.fluentcore.model.Updatable;
import com.microsoft.azure.management.sql.implementation.DatabaseInner;
import org.joda.time.DateTime;
import rx.Completable;

import java.util.UUID;

/**
 * An immutable client-side representation of an Azure SQL Server Database.
 */
@Fluent
@Beta(Beta.SinceVersion.V2_0_0)
public interface SqlDatabase
    extends
        ExternalChildResource<SqlDatabase, SqlServer>,
        HasInner<DatabaseInner>,
        Refreshable<SqlDatabase>,
        Updatable<SqlDatabase.Update> {

    /**
     * @return name of the SQL Server to which this database belongs
     */
    String sqlServerName();

    /**
     * @return the collation of the Azure SQL Database
     */
    String collation();

    /**
     * @return the creation date of the Azure SQL Database
     */
    DateTime creationDate();

    /**
     * @return the current Service Level Objective Id of the Azure SQL Database, this is the Id of the
     * Service Level Objective that is currently active
     */
    UUID currentServiceObjectiveId();


    /**
     * @return the Id of the Azure SQL Database
     */
    String databaseId();

    /**
     * @return the recovery period start date of the Azure SQL Database. This
     * records the start date and time when recovery is available for this
     * Azure SQL Database.
     */
    DateTime earliestRestoreDate();

//    /**
//     * @return the edition of the Azure SQL Database
//     */
//    DatabaseEditions edition();

    /**
     *
     * @return the configured Service Level Objective Id of the Azure SQL
     * Database, this is the Service Level Objective that is being applied to
     * the Azure SQL Database
     */
    UUID requestedServiceObjectiveId();

    /**
     * @return the max size of the Azure SQL Database expressed in bytes.
     */
    long maxSizeBytes();

    /**
     * @return the name of the configured Service Level Objective of the Azure
     * SQL Database, this is the Service Level Objective that is being
     * applied to the Azure SQL Database
     */
    ServiceObjectiveName requestedServiceObjectiveName();

    /**
     * @return the Service Level Objective of the Azure SQL Database.
     */
    ServiceObjectiveName serviceLevelObjective();

    /**
     * @return the status of the Azure SQL Database
     */
    String status();

    /**
     * @return the elasticPoolName value
     */
    String elasticPoolName();

    /**
     * @return the defaultSecondaryLocation value
     */
    String defaultSecondaryLocation();

//    /**
//     * @return the upgradeHint value
//     */
//    UpgradeHintInterface getUpgradeHint();

    /**
     * @return true if this Database is SqlWarehouse
     */
    boolean isDataWarehouse();

//    /**
//     * @return SqlWarehouse instance for more operations
//     */
//    @Method
//    SqlWarehouse asWarehouse();
//
//    /**
//     * @return returns the list of all restore points on the database
//     */
//    List<RestorePoint> listRestorePoints();
//
//    /**
//     * @return returns the list of usages (DatabaseMetrics) of the database
//     */
//    List<DatabaseMetric> listUsages();
//
//    /**
//     * Gets an Azure SQL Database Transparent Data Encryption for the database.
//     *
//     * @return an Azure SQL Database Transparent Data Encryption for the database
//     */
//    TransparentDataEncryption getTransparentDataEncryption();
//
//    /**
//     * @return information about service tier advisors for specified database
//     */
//    Map<String, ServiceTierAdvisor> listServiceTierAdvisors();
//
//    /**
//     * @return all the replication links associated with the database
//     */
//    Map<String, ReplicationLink> listReplicationLinks();

    /**
     * @return the parent SQL server ID
     */
    String parentId();

    /**
     * @return the name of the region the resource is in
     */
    String regionName();

    /**
     * @return the region the resource is in
     */
    Region region();

    /**
     * Deletes the database from the server.
     */
    @Method
    void delete();

    /**
     * Deletes the firewall rule asynchronously.
     *
     * @return a representation of the deferred computation of this call
     */
    @Method
    Completable deleteAsync();


    /**************************************************************
     * Fluent interfaces to provision a SQL Database
     **************************************************************/

    /**
     * Container interface for all the definitions that need to be implemented.
     */
    interface SqlDatabaseDefinition<ParentT> extends
        SqlDatabase.DefinitionStages.Blank<ParentT>,
        SqlDatabase.DefinitionStages.WithSourceDatabaseId<ParentT>,
        SqlDatabase.DefinitionStages.WithCreateMode<ParentT>,
        SqlDatabase.DefinitionStages.WithAttachForElasticPool<ParentT>,
        SqlDatabase.DefinitionStages.WithAttachFinal<ParentT> {
    }

    /**
     * Grouping of all the SQL Database definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of the SQL Server Firewall rule definition.
         *
         * @param <ParentT> the stage of the parent definition to return to after attaching this definition
         */
        interface Blank<ParentT> extends
            SqlDatabase.DefinitionStages.WithSourceDatabaseId<ParentT> {
        }

        /**
         * The SQL Database definition to set the source database id for database.
         */
        interface WithSourceDatabaseId<ParentT> extends WithAttachForElasticPool<ParentT> {
            /**
             * Sets the resource if of source database for the SQL Database.
             * Collation, Edition, and MaxSizeBytes must remain the same while the link is
             * active. Values specified for these parameters will be ignored.
             *
             * @param sourceDatabaseId id of the source database
             * @return The next stage of the definition.
             */
            WithCreateMode<ParentT> withSourceDatabase(String sourceDatabaseId);

            /**
             * Sets the resource if of source database for the SQL Database.
             * Collation, Edition, and MaxSizeBytes must remain the same while the link is
             * active. Values specified for these parameters will be ignored.
             *
             * @param sourceDatabase instance of the source database
             * @return The next stage of the definition.
             */
            WithCreateMode<ParentT> withSourceDatabase(SqlDatabase sourceDatabase);
        }

        /**
         * The SQL Database definition to set the create mode for database.
         */
        interface WithCreateMode<ParentT> {
            /**
             * Sets the create mode for the SQL Database.
             *
             * @param createMode create mode for the database, should not be default in this flow
             * @return The next stage of the definition.
             */
            WithAttachFinal<ParentT> withMode(CreateMode createMode);
        }

        /**
         * The SQL Database definition to set the collation for database.
         */
        interface WithCollation<ParentT> {
            /**
             * Sets the collation for the SQL Database.
             * <p>
             * Default collation for an Azure SQL Database is "SQL_Latin1_General_CP1_CI_AS"
             *
             * @param collation collation to be set for database
             * @return The next stage of the definition
             */
            WithAttachForElasticPool<ParentT> withCollation(String collation);
        }

        /**
         * The SQL Database definition to set the Max Size in Bytes for database.
         */
        interface WithMaxSizeBytes<ParentT> {
            /**
             * Sets the max size in bytes for SQL Database.
             *
             * @param maxSizeBytes max size of the Azure SQL Database expressed in bytes. Note: Only
             * the following sizes are supported (in addition to limitations being
             * placed on each edition): { 100 MB | 500 MB |1 GB | 5 GB | 10 GB | 20
             * GB | 30 GB … 150 GB | 200 GB … 500 GB }
             * @return The next stage of the definition.
             */
            WithAttachForElasticPool<ParentT> withMaxSizeBytes(long maxSizeBytes);
        }

        /** The final stage of the SQL Database definition with an SQL Elastic Pool.
         *
         * @param <ParentT> the stage of the parent definition to return to after attaching this definition
         */
        interface WithAttachForElasticPool<ParentT> extends
            WithCollation<ParentT>,
            WithMaxSizeBytes<ParentT>,
            WithAttachFinal<ParentT> {
        }

        /** The final stage of the SQL Database definition.
         * <p>
         * At this stage, any remaining optional settings can be specified, or the SQL Database definition
         * can be attached to the parent SQL Server definition.
         * @param <ParentT> the stage of the parent definition to return to after attaching this definition
         */
        interface WithAttachFinal<ParentT> extends
            Attachable.InDefinition<ParentT> {
        }
    }


    /**
     * The template for a SqlDatabase update operation, containing all the settings that can be modified.
     */
    interface Update extends
        UpdateStages.WithEdition,
        UpdateStages.WithElasticPoolName,
        UpdateStages.WithMaxSizeBytes,
        UpdateStages.WithServiceObjective,
        Resource.UpdateWithTags<SqlDatabase>,
        Appliable<SqlDatabase> {
    }

    /**
     * Grouping of all the SqlDatabase update stages.
     */
    interface UpdateStages {

        /**
         * The SQL Database definition to set the edition for database.
         */
        interface WithEdition {
            /**
             * Sets the edition for the SQL Database.
             *
             * @param edition edition to be set for database
             * @return The next stage of the update.
             */
            Update withEdition(DatabaseEdition edition);
        }

        /**
         * The SQL Database definition to set the Max Size in Bytes for database.
         */
        interface WithMaxSizeBytes {
            /**
             * Sets the max size in bytes for SQL Database.
             * @param maxSizeBytes max size of the Azure SQL Database expressed in bytes. Note: Only
             * the following sizes are supported (in addition to limitations being
             * placed on each edition): { 100 MB | 500 MB |1 GB | 5 GB | 10 GB | 20
             * GB | 30 GB … 150 GB | 200 GB … 500 GB }
             * @return The next stage of the update.
             */
            Update withMaxSizeBytes(long maxSizeBytes);
        }

        /**
         * The SQL Database definition to set the service level objective.
         */
        interface WithServiceObjective {
            /**
             * Sets the service level objective for the SQL Database.
             *
             * @param serviceLevelObjective service level objected for the SQL Database
             * @return The next stage of the update.
             */
            Update withServiceObjective(ServiceObjectiveName serviceLevelObjective);
        }

        /**
         * The SQL Database definition to set the elastic pool for database.
         */
        interface WithElasticPoolName {
            /**
             * Removes database from it's elastic pool.
             *
             * @return The next stage of the update.
             */
            WithEdition withoutElasticPool();

            /**
             * Sets the existing elastic pool for the SQLDatabase.
             *
             * @param elasticPoolName for the SQL Database
             * @return The next stage of the update.
             */
            Update withExistingElasticPool(String elasticPoolName);

            /**
             * Sets the existing elastic pool for the SQLDatabase.
             *
             * @param sqlElasticPool for the SQL Database
             * @return The next stage of the update.
             */
            Update withExistingElasticPool(SqlElasticPool sqlElasticPool);

            /**
             * Sets the new elastic pool for the SQLDatabase, this will create a new elastic pool while creating database.
             *
             * @param sqlElasticPool creatable definition for new elastic pool to be created for the SQL Database
             * @return The next stage of the update.
             */
            Update withNewElasticPool(Creatable<SqlElasticPool> sqlElasticPool);
        }
    }
}
