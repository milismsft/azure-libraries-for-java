/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.resources.core.TestUtilities;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import com.microsoft.azure.management.resources.fluentcore.model.Creatable;
import com.microsoft.azure.management.resources.fluentcore.model.Indexable;
import com.microsoft.azure.management.resources.fluentcore.utils.SdkContext;
import com.microsoft.azure.management.resources.fluentcore.utils.Utils;
import com.microsoft.azure.management.resources.implementation.ResourceGroupInner;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlServerOperationsTests extends SqlServerTest {
    private static final String SQL_DATABASE_NAME = "myTestDatabase2";
    private static final String COLLATION = "SQL_Latin1_General_CP1_CI_AS";
    private static final String SQL_ELASTIC_POOL_NAME = "testElasticPool";
    private static final String SQL_FIREWALLRULE_NAME = "firewallrule1";
    private static final String START_IPADDRESS = "10.102.1.10";
    private static final String END_IPADDRESS = "10.102.1.12";

    @Test
    public void canCRUDSqlServer() throws Exception {
        // Create

        String rgName = RG_NAME;
        String sqlServerName = SQL_SERVER_NAME;
        String sqlServerAdminName = "sqladmin";
//        sqlServerManager.resourceManager().inner().resourceGroups().createOrUpdate(rgName, new ResourceGroupInner().withName(rgName).withLocation(Region.US_CENTRAL.name()));

        SqlServer sqlServer = sqlServerManager
            .sqlServers()
                .define(SQL_SERVER_NAME)
                    .withRegion(Region.US_CENTRAL)
                    .withNewResourceGroup(RG_NAME)
                    .withAdministratorLogin(sqlServerAdminName)
                    .withAdministratorPassword("N0t@P@ssw0rd!")
                    .withAccessFromAzureServices()
                    .create();
        Assert.assertEquals(sqlServerAdminName, sqlServer.administratorLogin());
        Assert.assertEquals("v12.0", sqlServer.kind());
        Assert.assertEquals("12.0", sqlServer.version());

        sqlServer = sqlServerManager.sqlServers().getByResourceGroup(RG_NAME, SQL_SERVER_NAME);
        Assert.assertEquals(sqlServerAdminName, sqlServer.administratorLogin());
        Assert.assertEquals("v12.0", sqlServer.kind());
        Assert.assertEquals("12.0", sqlServer.version());



//        sqlServerManager.inner().servers().createOrUpdate()

        

    }
}
