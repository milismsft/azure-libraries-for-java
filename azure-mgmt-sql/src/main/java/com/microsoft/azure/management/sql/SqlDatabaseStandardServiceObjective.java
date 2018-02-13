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
 * The name of the configured Service Level Objective of a "Standard" Azure SQL Database.
 */
@Fluent
@Beta(Beta.SinceVersion.V2_0_0)
public class SqlDatabaseStandardServiceObjective extends ExpandableStringEnum<ServiceObjectiveName> {
    /** Static value S0 for ServiceObjectiveName. */
    public static final ServiceObjectiveName S0 = fromString("S0");

    /** Static value S1 for ServiceObjectiveName. */
    public static final ServiceObjectiveName S1 = fromString("S1");

    /** Static value S2 for ServiceObjectiveName. */
    public static final ServiceObjectiveName S2 = fromString("S2");

    /** Static value S3 for ServiceObjectiveName. */
    public static final ServiceObjectiveName S3 = fromString("S3");

    /** Static value S4 for ServiceObjectiveName. */
    public static final ServiceObjectiveName S4 = fromString("S4");

    /** Static value S6 for ServiceObjectiveName. */
    public static final ServiceObjectiveName S6 = fromString("S6");

    /** Static value S7 for ServiceObjectiveName. */
    public static final ServiceObjectiveName S7 = fromString("S7");

    /** Static value S9 for ServiceObjectiveName. */
    public static final ServiceObjectiveName S9 = fromString("S9");

    /** Static value S12 for ServiceObjectiveName. */
    public static final ServiceObjectiveName S12 = fromString("S12");

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

