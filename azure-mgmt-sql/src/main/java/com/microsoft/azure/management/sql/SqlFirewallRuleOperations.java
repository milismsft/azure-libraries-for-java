/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.apigeneration.Beta;
import com.microsoft.azure.management.apigeneration.Fluent;
import com.microsoft.azure.management.resources.fluentcore.collection.SupportsCreating;

/**
 * A representation of the Azure SQL Firewall rule operations.
 */
@Fluent
@Beta(Beta.SinceVersion.V2_0_0)
public interface SqlFirewallRuleOperations extends
    SupportsCreating<SqlFirewallRuleOperations.DefinitionStages.WithSqlServer>,
    SqlChildrenOperations<SqlFirewallRule> {

    /**
     * Container interface for all the definitions that need to be implemented.
     */
    interface SqlFirewallRuleOperationsDefinition extends
        SqlFirewallRuleOperations.DefinitionStages.WithSqlServer,
        SqlFirewallRuleOperations.DefinitionStages.WithIPAddressRange,
        SqlFirewallRuleOperations.DefinitionStages.WithCreate {
    }

    /**
     * Grouping of all the SQL Firewall rule definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of the SQL Server Firewall rule definition.
         */
        interface WithSqlServer {
            /**
             * Sets the parent SQL server name and resource group it belongs to.
             *
             * @param resourceGroupName the name of the resource group the parent SQL server
             * @param sqlServerName     the parent SQL server name
             * @return The next stage of the definition.
             */
            WithIPAddressRange withExistingSqlServer(String resourceGroupName, String sqlServerName);

            /**
             * Sets the parent SQL server for the new Firewall rule.
             *
             * @param sqlServerId the parent SQL server ID
             * @return The next stage of the definition.
             */
            WithIPAddressRange withSqlServerId(String sqlServerId);
        }

        /**
         * The SQL Firewall Rule definition to set the IP address range for the parent SQL Server.
         */
        interface WithIPAddressRange {
            /**
             * Sets the starting IP address of SQL server's firewall rule.
             *
             * @param startIPAddress starting IP address in IPv4 format.
             * @param endIPAddress   starting IP address in IPv4 format.
             * @return The next stage of the definition.
             */
            WithCreate withIPAddressRange(String startIPAddress, String endIPAddress);

            /**
             * Sets the ending IP address of SQL server's firewall rule.
             *
             * @param ipAddress IP address in IPv4 format.
             * @return The next stage of the definition.
             */
            WithCreate withIPAddress(String ipAddress);
        }

        /**
         * The final stage of the SQL Firewall Rule definition.
         */
//        interface WithCreate extends SqlCreatable<SqlFirewallRule> {
        interface WithCreate extends SqlChildrenCreatableDefinition<SqlFirewallRule> {
        }
    }

    /**
     * Grouping of the Azure SQL Server Firewall rule common actions.
     */
    interface SqlFirewallRuleActionsDefinition extends SqlChildrenActionsDefinition<SqlFirewallRule> {
    }
}