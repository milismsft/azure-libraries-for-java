/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.apigeneration.Beta;
import com.microsoft.azure.management.apigeneration.Fluent;
import com.microsoft.azure.management.resources.fluentcore.arm.models.ExternalChildResource;
import com.microsoft.azure.management.resources.fluentcore.model.Appliable;
import com.microsoft.azure.management.resources.fluentcore.model.Attachable;
import com.microsoft.azure.management.resources.fluentcore.model.HasInner;
import com.microsoft.azure.management.resources.fluentcore.model.Refreshable;
import com.microsoft.azure.management.resources.fluentcore.model.Updatable;
import com.microsoft.azure.management.sql.implementation.DatabaseInner;

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
