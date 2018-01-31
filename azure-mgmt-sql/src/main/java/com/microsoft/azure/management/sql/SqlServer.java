/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.apigeneration.Beta;
import com.microsoft.azure.management.apigeneration.Fluent;
import com.microsoft.azure.management.apigeneration.Method;
import com.microsoft.azure.management.resources.fluentcore.arm.models.GroupableResource;
import com.microsoft.azure.management.resources.fluentcore.arm.models.Resource;
import com.microsoft.azure.management.resources.fluentcore.model.Appliable;
import com.microsoft.azure.management.resources.fluentcore.model.Creatable;
import com.microsoft.azure.management.resources.fluentcore.model.Refreshable;
import com.microsoft.azure.management.resources.fluentcore.model.Updatable;
import com.microsoft.azure.management.sql.implementation.ServerInner;
import com.microsoft.azure.management.sql.implementation.SqlServerManager;

/**
 * An immutable client-side representation of an Azure SQL Server.
 */
@Fluent
@Beta(Beta.SinceVersion.V2_0_0)
public interface SqlServer extends
        GroupableResource<SqlServerManager, ServerInner>,
        Refreshable<SqlServer>,
        Updatable<SqlServer.Update> {

    /**
     * @return fully qualified name of the SQL Server
     */
    String fullyQualifiedDomainName();

    /**
     * @return the administrator login user name for the SQL Server
     */
    String administratorLogin();

    /**
     * @return the SQL Server "kind"
     */
    String kind();

    /**
     * @return the SQL Server version
     */
    String version();


    // Actions

    /**
     * Sets the Azure services default access to this server to true.
     * <p>
     * A firewall rule named "AllowAllWindowsAzureIps" with the start IP "0.0.0.0" will be added
     *   to the SQL server if one does not exist.
     *
     * @return the SQL Firewall rule
     */
    @Method
    SqlFirewallRule setAccessFromAzureServices();

    /**
     * Sets the Azure services default access to this server to false.
     * <p>
     * The firewall rule named "AllowAllWindowsAzureIps" will be removed from the SQL server.
     */
    @Method
    void removeAccessFromAzureServices();

    /**
     * Sets an Active Directory administrator to this server.
     * <p>
     * Azure Active Directory authentication allows you to centrally manage identity and access
     *   to your Azure SQL Database V12.
     *
     * @param userLogin the user or group login; it can be the name or the email address
     * @param id the user or group unique ID
     * @return a representation of a SQL Server Active Directory administrator object
     */
    @Method
    SqlActiveDirectoryAdministrator setActiveDirectoryAdministrator(String userLogin, String id);

    /**
     * Gets the Active Directory administrator for this server.
     *
     * @return a representation of a SQL Server Active Directory administrator object (null if one is not set)
     */
    @Method
    SqlActiveDirectoryAdministrator getActiveDirectoryAdministrator();

    /**
     * Removes the Active Directory administrator from this server.
     */
    @Method
    void removeActiveDirectoryAdministrator();


    // Collections

    /**
     * @return returns entry point to manage SQL Firewall rules for this server.
     */
    SqlFirewallRuleOperations.SqlFirewallRuleActionsDefinition firewallRules();

    /**
     * @return returns entry point to manage the SQL Elastic Pools for this server.
     */
    SqlElasticPoolOperations.SqlElasticPoolActionsDefinition elasticPools();

//    /**
//     * @return entry point to manage Databases for this SQL server.
//     */
//    Databases databases();
//
//    /**
//     * @return entry point to manage Databases for this SQL server.
//     */
//    @Beta(Beta.SinceVersion.V2_0_0)
//    FailoverGroups failoverGroups();
//
//    /**
//     * @return returns the list of usage metrics for an Azure SQL Server
//     */
//    @Beta(Beta.SinceVersion.V2_0_0)
//    List<ServerUsage> listUsageMetrics();
//
//    /**
//     * @return the list of information on all service objectives
//     */
//    @Beta(Beta.SinceVersion.V2_0_0)
//    List<ServiceObjective> listServiceObjectives();
//
//    /**
//     * Gets the information on a particular Sql Server Service Objective.
//     * @param serviceObjectiveName name of the service objective to be fetched
//     * @return information of the service objective
//     */
//    @Beta(Beta.SinceVersion.V2_0_0)
//    ServiceObjective getServiceObjective(String serviceObjectiveName);
//
//    /**
//     * Returns all the recommended elastic pools for the server.
//     *
//     * @return list of recommended elastic pools for the server
//     */
//    Map<String, RecommendedElasticPool> listRecommendedElasticPools();
//
//    /**
//     * Entry point to firewall rules from the SQL Server.
//     */
//    interface FirewallRules {
//        /**
//         * Gets a particular firewall rule.
//         *
//         * @param firewallRuleName name of the firewall rule to get
//         * @return Returns the SqlFirewall rule with in the SQL Server
//         */
//        SqlFirewallRule get(String firewallRuleName);
//
//        /**
//         * Creates a new firewall rule in SQL Server.
//         *
//         * @param firewallRuleName name of the firewall rule to be created
//         * @return Returns a stage to specify arguments of the firewall rule
//         */
//        SqlFirewallRule.DefinitionStages.Blank define(String firewallRuleName);
//
//        /**
//         * Returns all the firewall rules for the server.
//         *
//         * @return list of firewall rules for the server.
//         */
//        List<SqlFirewallRule> list();
//
//        /**
//         * Delete specified firewall rule in the server.
//         *
//         * @param firewallRuleName name of the firewall rule to delete
//         */
//        void delete(String firewallRuleName);
//
//        /**
//         * Delete specified firewall rule in the server.
//         *
//         * @param firewallRuleName name of the firewall rule to delete
//         * @return observable for the delete operation
//         */
//        Completable deleteAsync(String firewallRuleName);
//    }
//
//    /**
//     * Entry point to elastic pools from the SQL Server.
//     */
//    interface ElasticPools {
//
//        // TODO: add implementation for THIS
//
////        /**
////         * Gets a particular elastic pool.
////         *
////         * @param elasticPoolName name of the elastic pool to get
////         * @return Returns the elastic pool with in the SQL Server
////         */
////        SqlElasticPool get(String elasticPoolName);
////
////        /**
////         * Creates a new elastic pool in SQL Server.
////         *
////         * @param elasticPoolName name of the elastic pool to be created
////         * @return Returns a stage to specify arguments of the elastic pool
////         */
////        SqlElasticPool.DefinitionStages.Blank define(String elasticPoolName);
////
////        /**
////         * Returns all the elastic pools for the server.
////         *
////         * @return list of elastic pools for the server.
////         */
////        List<SqlElasticPool> list();
//
//        /**
//         * Delete specified elastic pool in the server.
//         *
//         * @param elasticPoolName name of the elastic pool to delete
//         */
//        void delete(String elasticPoolName);
//
//        /**
//         * Delete specified elastic pool in the server.
//         *
//         * @param elasticPoolName name of the elastic pool to delete
//         * @return observable for the delete operation
//         */
//        Completable deleteAsync(String elasticPoolName);
//    }
//
//    /**
//     * Entry point to databases from the SQL Server.
//     */
//    interface Databases {
//        // TODO: add implementation for THIS
//
////        /**
////         * Gets a particular sql database.
////         *
////         * @param databaseName name of the sql database to get
////         * @return Returns the database with in the SQL Server
////         */
////        SqlDatabase get(String databaseName);
////
////        /**
////         * Creates a new database in SQL Server.
////         *
////         * @param databaseName name of the database to be created
////         * @return Returns a stage to specify arguments of the database
////         */
////        SqlDatabase.DefinitionStages.Blank define(String databaseName);
////
////        /**
////         * Returns all the databases for the server.
////         *
////         * @return list of databases for the server.
////         */
////        List<SqlDatabase> list();
//
//        /**
//         * Delete specified database in the server.
//         *
//         * @param databaseName name of the database to delete
//         */
//        void delete(String databaseName);
//
//        /**
//         * Delete specified database in the server.
//         *
//         * @param databaseName name of the database to delete
//         * @return observable for the delete operation
//         */
//        Completable deleteAsync(String databaseName);
//    }

    /**************************************************************
     * Fluent interfaces to provision a SqlServer
     **************************************************************/

    /**
     * Container interface for all the definitions that need to be implemented.
     */
    interface Definition extends
        DefinitionStages.Blank,
        DefinitionStages.WithGroup,
        DefinitionStages.WithAdministratorLogin,
        DefinitionStages.WithAdministratorPassword,
        DefinitionStages.WithElasticPool,
//        DefinitionStages.WithDatabase,
        DefinitionStages.WithFirewallRule,
        DefinitionStages.WithCreate {
    }

    /**
     * Grouping of all the storage account definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of the SQL Server definition.
         */
        interface Blank extends DefinitionWithRegion<WithGroup> {
        }

        /**
         * A SQL Server definition allowing resource group to be set.
         */
        interface WithGroup extends GroupableResource.DefinitionStages.WithGroup<WithAdministratorLogin> {
        }

        /**
         * A SQL Server definition setting administrator user name.
         */
        interface WithAdministratorLogin {
            /**
             * Sets the administrator login user name.
             *
             * @param administratorLogin administrator login user name
             * @return Next stage of the SQL Server definition
             */
            WithAdministratorPassword withAdministratorLogin(String administratorLogin);
        }

        /**
         * A SQL Server definition setting admin user password.
         */
        interface WithAdministratorPassword {
            /**
             * Sets the administrator login password.
             *
             * @param administratorLoginPassword password for administrator login
             * @return Next stage of the SQL Server definition
             */
            WithCreate withAdministratorPassword(String administratorLoginPassword);
        }

        /**
         * A SQL Server definition setting the Active Directory administrator.
         */
        interface WithActiveDirectoryAdministrator {
            /**
             * Sets the SQL Active Directory administrator.
             * <p>
             * Azure Active Directory authentication allows you to centrally manage identity and access
             *   to your Azure SQL Database V12.
             *
             * @param userLogin the user or group login; it can be the name or the email address
             * @param id the user or group unique ID
             * @return Next stage of the SQL Server definition
             */
            WithCreate withActiveDirectoryAdministrator(String userLogin, String id);
        }

        /**
         * A SQL Server definition for specifying elastic pool.
         */
        interface WithElasticPool {
            /**
             * Begins the definition of a new SQL Elastic Pool to be added to this server.
             *
             * @param name the name of the new SQL Elastic Pool
             * @return the first stage of the new SQL Elastic Pool definition
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlElasticPool.DefinitionStages.Blank<WithCreate> defineElasticPool(String name);

            /**
             * Creates new elastic pool in the SQL Server.
             * @param elasticPoolName name of the elastic pool to be created
             * @param elasticPoolEdition edition of the elastic pool
             * @param databaseNames names of the database to be included in the elastic pool
             * @return Next stage of the SQL Server definition
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            @Deprecated
            WithCreate withNewElasticPool(String elasticPoolName, ElasticPoolEdition elasticPoolEdition, String... databaseNames);

            /**
             * Creates new elastic pool in the SQL Server.
             * @param elasticPoolName name of the elastic pool to be created
             * @param elasticPoolEdition edition of the elastic pool
             * @return Next stage of the SQL Server definition
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            @Deprecated
            WithCreate withNewElasticPool(String elasticPoolName, ElasticPoolEdition elasticPoolEdition);
        }

        /**
         * A SQL Server definition for specifying the databases.
         */
        interface WithDatabase {
            /**
             * Begins the definition of a new SQL Database to be added to this server.
             *
             * @param name the name of the new SQL Database
             * @return the first stage of the new SQL Database definition
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            SqlDatabase.DefinitionStages.Blank<WithCreate> defineDatabase(String name);

            /**
             * Creates new database in the SQL Server.
             * @param databaseName name of the database to be created
             * @return Next stage of the SQL Server definition
             */
            @Deprecated
            WithCreate withNewDatabase(String databaseName);
        }

        /**
         * The stage of the SQL Server definition allowing to specify the SQL Firewall rules.
         */
        interface WithFirewallRule {
            /**
             * Sets the Azure services default access to this server to false.
             * <p>
             * The default is to allow Azure services default access to this server via a special
             *   firewall rule named "AllowAllWindowsAzureIps" with the start IP "0.0.0.0".
             *
             * @return Next stage of the SQL Server definition
             */
            WithCreate withoutAccessFromAzureServices();

            /**
             * Begins the definition of a new SQL Firewall rule to be added to this server.
             *
             * @param name the name of the new SQL Firewall rule
             * @return the first stage of the new SQL Firewall rule definition
             */
            SqlFirewallRule.DefinitionStages.Blank<WithCreate> defineFirewallRule(String name);

            /**
             * Creates new firewall rule in the SQL Server.
             *
             * @param ipAddress ipAddress for the firewall rule
             * @return Next stage of the SQL Server definition
             */
            @Deprecated
            WithCreate withNewFirewallRule(String ipAddress);

            /**
             * Creates new firewall rule in the SQL Server.
             *
             * @param startIPAddress start IP address for the firewall rule
             * @param endIPAddress end IP address for the firewall rule
             * @return Next stage of the SQL Server definition
             */
            @Deprecated
            WithCreate withNewFirewallRule(String startIPAddress, String endIPAddress);

            /**
             * Creates new firewall rule in the SQL Server.
             *
             * @param startIPAddress start IP address for the firewall rule
             * @param endIPAddress end IP address for the firewall rule
             * @param firewallRuleName name for the firewall rule
             * @return Next stage of the SQL Server definition
             */
            @Deprecated
            WithCreate withNewFirewallRule(String startIPAddress, String endIPAddress, String firewallRuleName);
        }

        /**
         * A SQL Server definition with sufficient inputs to create a new
         * SQL Server in the cloud, but exposing additional optional inputs to
         * specify.
         */
        interface WithCreate extends
            Creatable<SqlServer>,
            WithActiveDirectoryAdministrator,
            WithElasticPool,
            WithDatabase,
            WithFirewallRule,
            DefinitionWithTags<WithCreate> {
        }
    }

    /**
     * The template for a SQLServer update operation, containing all the settings that can be modified.
     */
    interface Update extends
            Appliable<SqlServer>,
            UpdateStages.WithAdministratorPassword,
            UpdateStages.WithElasticPool,
            UpdateStages.WithDatabase,
            UpdateStages.WithFirewallRule,
            Resource.UpdateWithTags<Update> {
    }

    /**
     * Grouping of all the SQLServer update stages.
     */
    interface UpdateStages {
        /**
         * A SQL Server update stage setting admin user password.
         */
        interface WithAdministratorPassword {
            /**
             * Sets the administrator login password.
             *
             * @param administratorLoginPassword password for administrator login
             * @return Next stage of the update.
             */
            Update withAdministratorPassword(String administratorLoginPassword);
        }


        /**
         * A SQL Server definition for specifying elastic pool.
         */
        interface WithElasticPool {
            /**
             * Create new elastic pool in the SQL Server.
             * @param elasticPoolName name of the elastic pool to be created
             * @param elasticPoolEdition edition of the elastic pool
             * @param databaseNames names of the database to be included in the elastic pool
             * @return Next stage of the SQL Server update
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            @Deprecated
            Update withNewElasticPool(String elasticPoolName, ElasticPoolEdition elasticPoolEdition, String... databaseNames);

            /**
             * Create new elastic pool in the SQL Server.
             * @param elasticPoolName name of the elastic pool to be created
             * @param elasticPoolEdition edition of the elastic pool
             * @return Next stage of the SQL Server update
             */
            @Beta(Beta.SinceVersion.V2_0_0)
            @Deprecated
            Update withNewElasticPool(String elasticPoolName, ElasticPoolEdition elasticPoolEdition);

            /**
             * Removes elastic pool from the SQL Server.
             * @param elasticPoolName name of the elastic pool to be removed
             * @return Next stage of the SQL Server update
             */
            @Deprecated
            Update withoutElasticPool(String elasticPoolName);
        }

        /**
         * A SQL Server definition for specifying the databases.
         */
        interface WithDatabase {
            /**
             * Create new database in the SQL Server.
             * @param databaseName name of the database to be created
             * @return Next stage of the SQL Server update
             */
            @Deprecated
            Update withNewDatabase(String databaseName);

            /**
             * Remove database from the SQL Server.
             * @param databaseName name of the database to be removed
             * @return Next stage of the SQL Server update
             */
            @Deprecated
            Update withoutDatabase(String databaseName);
        }



        /**
         * The stage of the SQL Server update definition allowing to specify the SQL Firewall rules.
         */
        interface WithFirewallRule {
            /**
             * Create new firewall rule in the SQL Server.
             *
             * @param ipAddress IP address for the firewall rule
             * @return Next stage of the SQL Server update
             */
            @Deprecated
            Update withNewFirewallRule(String ipAddress);

            /**
             * Create new firewall rule in the SQL Server.
             *
             * @param startIPAddress Start IP address for the firewall rule
             * @param endIPAddress IP address for the firewall rule
             * @return Next stage of the SQL Server update
             */
            @Deprecated
            Update withNewFirewallRule(String startIPAddress, String endIPAddress);

            /**
             * Creates new firewall rule in the SQL Server.
             *
             * @param startIPAddress start IP address for the firewall rule
             * @param endIPAddress end IP address for the firewall rule
             * @param firewallRuleName name for the firewall rule
             * @return Next stage of the SQL Server update
             */
            @Deprecated
            Update withNewFirewallRule(String startIPAddress, String endIPAddress, String firewallRuleName);

            /**
             * Removes firewall rule from the SQL Server.
             *
             * @param firewallRuleName name of the firewall rule to be removed
             * @return Next stage of the SQL Server update
             */
            @Deprecated
            Update withoutFirewallRule(String firewallRuleName);
        }
    }
}

