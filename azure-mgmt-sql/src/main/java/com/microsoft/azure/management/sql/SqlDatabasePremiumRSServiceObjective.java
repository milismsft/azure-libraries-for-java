/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.apigeneration.Beta;
import com.microsoft.azure.management.apigeneration.Fluent;
import com.microsoft.rest.ExpandableStringEnum;

import java.util.Collection;

/**
 * The name of the configured Service Level Objective of a "PremiumRS" Azure SQL Database.
 */
@Fluent
@Beta(Beta.SinceVersion.V2_0_0)
public class SqlDatabasePremiumRSServiceObjective extends ExpandableStringEnum<ServiceObjectiveName> {
    /** Static value PRS1 for ServiceObjectiveName. */
    public static final ServiceObjectiveName PRS1 = fromString("PRS1");

    /** Static value PRS2 for ServiceObjectiveName. */
    public static final ServiceObjectiveName PRS2 = fromString("PRS2");

    /** Static value PRS4 for ServiceObjectiveName. */
    public static final ServiceObjectiveName PRS4 = fromString("PRS4");

    /** Static value PRS6 for ServiceObjectiveName. */
    public static final ServiceObjectiveName PRS6 = fromString("PRS6");

    /**
     * Creates or finds a ServiceObjectiveName from its string representation.
     * @param name a name to look for
     * @return the corresponding ServiceObjectiveName
     */
    public static ServiceObjectiveName fromString(String name) {
        return fromString(name, ServiceObjectiveName.class);
    }

    /**
     * @return known ServiceObjectiveName values
     */
    public static Collection<ServiceObjectiveName> values() {
        return values(ServiceObjectiveName.class);
    }
}
