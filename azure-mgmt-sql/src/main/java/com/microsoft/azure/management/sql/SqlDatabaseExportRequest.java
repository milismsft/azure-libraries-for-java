/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.apigeneration.Beta;
import com.microsoft.azure.management.apigeneration.Fluent;
import com.microsoft.azure.management.resources.fluentcore.arm.models.HasParent;
import com.microsoft.azure.management.resources.fluentcore.model.Creatable;
import com.microsoft.azure.management.resources.fluentcore.model.Executable;
import com.microsoft.azure.management.resources.fluentcore.model.HasInner;
import com.microsoft.azure.management.sql.implementation.ExportRequestInner;
import com.microsoft.azure.management.storage.StorageAccount;

/**
 * An immutable client-side representation of an Azure SQL Database export operation request.
 */
@Fluent
@Beta(Beta.SinceVersion.V2_0_0)
public interface SqlDatabaseExportRequest extends
    HasInner<ExportRequestInner>,
    Executable<SqlDatabaseImportExportResponse>,
    HasParent<SqlDatabase> {

    /**
     * The entirety of connectivity check parameters definition.
     */
    interface SqlDatabaseExportRequestDefinition extends
        SqlDatabaseExportRequest.DefinitionStages.ExportTo,
        SqlDatabaseExportRequest.DefinitionStages.WithStorageType,
        SqlDatabaseExportRequest.DefinitionStages.WithStorageKey,
        SqlDatabaseExportRequest.DefinitionStages.WithAuthenticationType,
        SqlDatabaseExportRequest.DefinitionStages.WithAdministratorLoginPassword,
        DefinitionStages.WithExecute {
    }

    /**
     * Grouping of connectivity check parameters definition stages.
     */
    interface DefinitionStages {
        /**
         * Sets the storage URI to use.
         */
        interface ExportTo {
            /**
             * @param storageUri the storage URI to use
             * @return next definition stage
             */
             SqlDatabaseExportRequest.DefinitionStages.WithStorageType exportTo(String storageUri);

            /**
             * @param storageAccount an existing storage account to be used
             * @param containerName the container name within the storage account to use
             * @param fileName the exported database file name
             */
            SqlDatabaseExportRequest.DefinitionStages.WithAuthenticationType exportTo(StorageAccount storageAccount, String containerName, String fileName);

            /**
             * @param storageAccountCreatable a storage account to be created as part of this execution flow
             * @param containerName the container name within the storage account to use
             * @param fileName the exported database file name
             * @return next definition stage
             */
            SqlDatabaseExportRequest.DefinitionStages.WithAuthenticationType exportTo(Creatable<StorageAccount> storageAccountCreatable, String containerName, String fileName);
        }

        /**
         * Sets the storage key type and value to use.
         */
        interface WithStorageType {
            /**
             * @param storageKeyType the type of the storage key to use
             * @return next definition stage
             */
            SqlDatabaseExportRequest.DefinitionStages.WithStorageKey withStorageKeyType(StorageKeyType storageKeyType);

            /**
             * @param storageAccessKey the storage access key to use
             * @return next definition stage
             */
            SqlDatabaseExportRequest.DefinitionStages.WithAuthenticationType withStorageAccessKey(String storageAccessKey);

            /**
             * @param sharedAccessKey the shared access key to use; it must be preceded with a "?."
             * @return next definition stage
             */
            SqlDatabaseExportRequest.DefinitionStages.WithAuthenticationType withSharedAccessKey(String sharedAccessKey);
        }

        /**
         * Sets the storage key to use.
         * <p>
         * If storage key type is SharedAccessKey, it must be preceded with a "?.".
         */
        interface WithStorageKey {
            /**
             * @param storageKey the storage key to use; if storage key type is SharedAccessKey, it must be preceded with a "?."
             * @return next definition stage
             */
            SqlDatabaseExportRequest.DefinitionStages.WithAuthenticationType withStorageKey(String storageKey);
        }

        /**
         * Sets the authentication type and SQL or Active Directory administrator login and password.
         */
        interface WithAuthenticationType {
            /**
             * @param authenticationType the authentication type
             * @return next definition stage
             */
            SqlDatabaseExportRequest.DefinitionStages.WithAdministratorLoginPassword withAuthenticationType(AuthenticationType authenticationType);

            /**
             * @param administratorLogin the SQL administrator login
             * @param administratorPassword the SQL administrator password
             * @return next definition stage
             */
            SqlDatabaseExportRequest.DefinitionStages.WithExecute withSqlAdministratorLoginAndPassword(String administratorLogin, String administratorPassword);

            /**
             * @param administratorLogin the Active Directory administrator login
             * @param administratorPassword the Active Directory administrator password
             * @return next definition stage
             */
            SqlDatabaseExportRequest.DefinitionStages.WithExecute withActiveDirectoryLoginAndPassword(String administratorLogin, String administratorPassword);
        }

        /**
         * Sets the authentication type and SQL or Active Directory administrator login and password.
         */
        interface WithAdministratorLoginPassword {
            /**
             * @param administratorLogin the SQL or Active Directory administrator login
             * @param administratorPassword the SQL or Active Directory administrator password
             * @return next definition stage
             */
            SqlDatabaseExportRequest.DefinitionStages.WithExecute withLoginAndPassword(String administratorLogin, String administratorPassword);
        }

        /**
         * The stage of the definition which contains all the minimum required inputs for execution, but also allows
         * for any other optional settings to be specified.
         */
        interface WithExecute extends
            Executable<SqlDatabaseImportExportResponse> {
        }
    }

}
