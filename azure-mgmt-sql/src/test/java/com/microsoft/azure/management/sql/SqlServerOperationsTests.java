/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

public class SqlServerOperationsTests extends SqlServerTest {
    private static final String SQL_DATABASE_NAME = "myTestDatabase2";
    private static final String COLLATION = "SQL_Latin1_General_CP1_CI_AS";
    private static final String SQL_ELASTIC_POOL_NAME = "testElasticPool";
    private static final String SQL_FIREWALLRULE_NAME = "firewallrule1";
    private static final String START_IPADDRESS = "10.102.1.10";
    private static final String END_IPADDRESS = "10.102.1.12";

    @Test
    public void canCRUDSqlElasticPool() throws Exception {
        String id = UUID.randomUUID().toString();
        SqlServer sqlServer = sqlServerManager.sqlServers().getByResourceGroup("a1-test-sql", "mysql112233");
        if (sqlServer == null) {
            sqlServer = sqlServerManager
                .sqlServers()
                .define("mysql112233")
                    .withRegion(Region.US_WEST)
                    .withNewResourceGroup("a1-test-sql")
                    .withAdministratorLogin("adminsql")
                    .withAdministratorPassword("passSQL1")
                    .withActiveDirectoryAdministrator("DSEng", id)
//                    .withoutAccessFromAzureServices()
                    .defineFirewallRule("somefirewallrule1")
                        .withIPAddressRange("0.0.0.1", "255.255.255.255")
                        .attach()
                .create();
        }

        sqlServerManager.resourceManager().resourceGroups().define(RG_NAME).withRegion("westus").create();

//        SqlElasticPool elasticPool = sqlServerManager.sqlServers().elasticPools()
//            .define("ep1")
//            .withSqlServer(sqlServer)
//            .withBasicPool()
//            .withReservedDtu(SqlElasticPoolBasicEDTUs.eDTU_200)
//            .withDatabaseDtuMax(SqlElasticPoolBasicMaxEDTUs.eDTU_5)
//            .withDatabaseDtuMin(SqlElasticPoolBasicMinEDTUs.eDTU_5)
//            .create();
//
//        Assert.assertNotNull(elasticPool);
//        Assert.assertEquals(ElasticPoolEdition.BASIC, elasticPool.edition());
//        Assert.assertEquals(200, elasticPool.dtu());
//        Assert.assertEquals(5, elasticPool.databaseDtuMax());
//        Assert.assertEquals(5, elasticPool.databaseDtuMin());
//
//        elasticPool.delete();

        List<SqlRestorableDroppedDatabase> restorableDroppedDatabases = sqlServer.listRestorableDroppedDatabases();
        SqlRestorableDroppedDatabase restorableDroppedDatabase = restorableDroppedDatabases.get(0);

        SqlDatabase db = sqlServerManager.sqlServers().databases().define("test-restore")
            .withSqlServer(sqlServer)
            .withSourceDatabase(restorableDroppedDatabase.id())
            .withMode(CreateMode.RESTORE)
            .create();

        db.delete();
//        SqlDatabase db5 = sqlServerManager.sqlServers().databases()
//            .define("db5")
//            .withSqlServer(sqlServer)
//            .defineElasticPool("ep3")
//                .withBasicPool()
//                .attach()
//            .create();

//        SqlElasticPool elasticPool2 = sqlServerManager.sqlServers().elasticPools()
//            .define("ep2")
//            .withSqlServer(sqlServer)
//            .withBasicPool()
//            .withNewDatabase("db2")
//            .defineDatabase("db3")
//                .attach()
//            .withExistingDatabase("db4")
//            .create();
//
//        elasticPool2.delete();


//                canCRUDSqlServer();
    }

//    @Test
    public void canCRUDSqlServer() throws Exception {
        // Create

        String rgName = RG_NAME;
        String sqlServerName = SQL_SERVER_NAME;
        String sqlServerAdminName = "sqladmin";
        String id = UUID.randomUUID().toString();

//        sqlServerManager.resourceManager().inner().resourceGroups().createOrUpdate(rgName, new ResourceGroupInner().withName(rgName).withLocation(Region.US_CENTRAL.name()));

        SqlServer sqlServer = sqlServerManager
            .sqlServers()
                .define(SQL_SERVER_NAME)
                    .withRegion(Region.US_CENTRAL)
                    .withNewResourceGroup(RG_NAME)
                    .withAdministratorLogin(sqlServerAdminName)
                    .withAdministratorPassword("N0t@P@ssw0rd!")
                    .withActiveDirectoryAdministrator("DSEng", id)
                    .withoutAccessFromAzureServices()
                    .defineFirewallRule("somefirewallrule1")
                        .withIPAddress("0.0.0.1")
                        .attach()
                    .create();
        Assert.assertEquals(sqlServerAdminName, sqlServer.administratorLogin());
        Assert.assertEquals("v12.0", sqlServer.kind());
        Assert.assertEquals("12.0", sqlServer.version());

        sqlServer = sqlServerManager.sqlServers().getByResourceGroup(RG_NAME, SQL_SERVER_NAME);
        Assert.assertEquals(sqlServerAdminName, sqlServer.administratorLogin());
        Assert.assertEquals("v12.0", sqlServer.kind());
        Assert.assertEquals("12.0", sqlServer.version());

        SqlActiveDirectoryAdministrator sqlADAdmin = sqlServer.getActiveDirectoryAdministrator();
        Assert.assertNotNull(sqlADAdmin);
        Assert.assertEquals("DSEng", sqlADAdmin.signInName());
        Assert.assertEquals(id, sqlADAdmin.id());
        Assert.assertEquals("ActiveDirectory", sqlADAdmin.administratorType());

        sqlADAdmin = sqlServer.setActiveDirectoryAdministrator("DSEngAll", id);
        Assert.assertNotNull(sqlADAdmin);
        Assert.assertEquals("DSEngAll", sqlADAdmin.signInName());
        Assert.assertEquals(id, sqlADAdmin.id());
        Assert.assertEquals("ActiveDirectory", sqlADAdmin.administratorType());
        sqlServer.removeActiveDirectoryAdministrator();
        sqlADAdmin = sqlServer.getActiveDirectoryAdministrator();
        Assert.assertNull(sqlADAdmin);

        SqlFirewallRule firewallRule = sqlServerManager.sqlServers().firewallRules().getBySqlServer(RG_NAME, SQL_SERVER_NAME, "somefirewallrule1");
        Assert.assertEquals("0.0.0.1", firewallRule.startIPAddress());
        Assert.assertEquals("0.0.0.1", firewallRule.endIPAddress());

        firewallRule = sqlServerManager.sqlServers().firewallRules().getBySqlServer(RG_NAME, SQL_SERVER_NAME, "AllowAllWindowsAzureIps");
        Assert.assertNull(firewallRule);

        sqlServer.enableAccessFromAzureServices();
        firewallRule = sqlServerManager.sqlServers().firewallRules().getBySqlServer(RG_NAME, SQL_SERVER_NAME, "AllowAllWindowsAzureIps");
        Assert.assertEquals("0.0.0.0", firewallRule.startIPAddress());
        Assert.assertEquals("0.0.0.0", firewallRule.endIPAddress());

        sqlServer.update()
            .withNewFirewallRule("0.0.0.2")
            .apply();

        firewallRule = sqlServerManager.sqlServers().firewallRules()
            .define("newFirewallRule")
            .withExistingSqlServer(RG_NAME, SQL_SERVER_NAME)
            .withIPAddress("0.0.0.3")
            .create();

        Assert.assertEquals("0.0.0.3", firewallRule.startIPAddress());
        Assert.assertEquals("0.0.0.3", firewallRule.endIPAddress());

        firewallRule = firewallRule.update().withStartIPAddress("0.0.0.1").apply();

        Assert.assertEquals("0.0.0.1", firewallRule.startIPAddress());
        Assert.assertEquals("0.0.0.3", firewallRule.endIPAddress());

        sqlServer.firewallRules().delete("somefirewallrule1");
        firewallRule = sqlServerManager.sqlServers().firewallRules().getBySqlServer(RG_NAME, SQL_SERVER_NAME, "somefirewallrule1");
        Assert.assertNull(firewallRule);

        firewallRule = sqlServer.firewallRules().define("somefirewallrule2")
            .withIPAddress("0.0.0.4")
            .create();

        Assert.assertEquals("0.0.0.4", firewallRule.startIPAddress());
        Assert.assertEquals("0.0.0.4", firewallRule.endIPAddress());

        firewallRule.delete();
    }
}
