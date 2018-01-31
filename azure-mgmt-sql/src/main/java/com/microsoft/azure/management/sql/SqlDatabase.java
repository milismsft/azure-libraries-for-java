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
        SqlDatabase.DefinitionStages.WithAttach<ParentT> {
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
            SqlDatabase.DefinitionStages.WithIPAddress<ParentT> {
        }

        /**
         * The SQL Firewall Rule definition to set the IP address for the parent SQL Server.
         */
        interface WithIPAddress<ParentT> {
            /**
             * Sets the ending IP address of SQL server's firewall rule.
             *
             * @param ipAddress IP address in IPv4 format.
             * @return The next stage of the definition.
             */
            SqlDatabase.DefinitionStages.WithAttach<ParentT> withIPAddress(String ipAddress);
        }

        /** The final stage of the SQL Database definition.
         * <p>
         * At this stage, any remaining optional settings can be specified, or the SQL Database definition
         * can be attached to the parent SQL Server definition.
         * @param <ParentT> the stage of the parent definition to return to after attaching this definition
         */
        interface WithAttach<ParentT> extends
            Attachable.InDefinition<ParentT> {
        }
    }


    /**
     * The template for a SqlDatabase update operation, containing all the settings that can be modified.
     */
    interface Update extends
        SqlDatabase.UpdateStages.WithEndIPAddress,
        Appliable<SqlDatabase> {
    }

    /**
     * Grouping of all the SqlDatabase update stages.
     */
    interface UpdateStages {

        /**
         * The SQL Firewall Rule definition to set the starting IP Address for the server.
         */
        interface WithEndIPAddress {
            /**
             * Sets the ending IP address of SQL server's firewall rule.
             *
             * @param endIPAddress end IP address in IPv4 format.
             * @return The next stage of the update.
             */
            SqlDatabase.Update withEndIPAddress(String endIPAddress);
        }
    }
}
