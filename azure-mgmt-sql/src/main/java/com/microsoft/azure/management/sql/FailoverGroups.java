/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.apigeneration.Fluent;
import com.microsoft.azure.management.resources.fluentcore.arm.collection.SupportsBatchDeletion;
import com.microsoft.azure.management.resources.fluentcore.arm.collection.SupportsDeletingByResourceGroup;
import com.microsoft.azure.management.resources.fluentcore.arm.collection.SupportsGettingById;
import com.microsoft.azure.management.resources.fluentcore.arm.collection.SupportsGettingByResourceGroup;
import com.microsoft.azure.management.resources.fluentcore.arm.collection.SupportsListingByResourceGroup;
import com.microsoft.azure.management.resources.fluentcore.arm.models.HasManager;
import com.microsoft.azure.management.resources.fluentcore.collection.SupportsDeletingById;
import com.microsoft.azure.management.resources.fluentcore.collection.SupportsListing;
import com.microsoft.azure.management.resources.fluentcore.model.HasInner;
import com.microsoft.azure.management.sql.implementation.FailoverGroupsInner;
import com.microsoft.azure.management.sql.implementation.SqlServerManager;

/**
 *  Entry point to SQL Server Failover Groups management API.
 */
@Fluent
public interface FailoverGroups extends
//    SupportsCreating<FailoverGroup.DefinitionStages.Blank>,
    SupportsListing<FailoverGroup>,
    SupportsListingByResourceGroup<FailoverGroup>,
    SupportsGettingByResourceGroup<FailoverGroup>,
    SupportsGettingById<FailoverGroup>,
    SupportsDeletingById,
    SupportsDeletingByResourceGroup,
    SupportsBatchDeletion,
    HasManager<SqlServerManager>,
    HasInner<FailoverGroupsInner> {
}
