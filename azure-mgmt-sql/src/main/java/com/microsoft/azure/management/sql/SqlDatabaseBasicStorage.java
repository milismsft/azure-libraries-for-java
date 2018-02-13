/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.apigeneration.Beta;
import com.microsoft.azure.management.apigeneration.Fluent;

/**
 * The maximum allowed storage capacity for a "Basic" edition of an Azure SQL Elastic Pool.
 */
@Fluent
@Beta(Beta.SinceVersion.V2_0_0)
public enum SqlDatabaseBasicStorage {
    /** 100 MB storage capacity available for the database. */
    MAX_100_MB(100),

    /** 100 MB storage capacity available for the database. */
    MAX_500_MB(500),

    /** 1 GB storage capacity available for the database. */
    MAX_1_GB(1024),

    /** 2 GB storage capacity available for the database. */
    MAX_2_GB(2 * 1024);

    /** The maximum allowed storage capacity in MB for the SQL Database. */
    private int capacityInMB;

    SqlDatabaseBasicStorage(int capacityInMB) {
        this.capacityInMB = capacityInMB;
    }

    /**
     * @return the maximum allowed storage capacity in MB for the SQL Elastic Pool
     */
    public int capacityInMB() {
        return this.capacityInMB;
    }
}
