/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.sql.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.management.resources.fluentcore.arm.models.implementation.GroupableResourceImpl;
import com.microsoft.azure.management.resources.fluentcore.dag.FunctionalTaskItem;
import com.microsoft.azure.management.resources.fluentcore.model.Indexable;
import com.microsoft.azure.management.resources.fluentcore.utils.SdkContext;
import com.microsoft.azure.management.sql.ElasticPoolEdition;
import com.microsoft.azure.management.sql.SqlActiveDirectoryAdministrator;
import com.microsoft.azure.management.sql.SqlElasticPoolOperations;
import com.microsoft.azure.management.sql.SqlFirewallRule;
import com.microsoft.azure.management.sql.SqlFirewallRuleOperations;
import com.microsoft.azure.management.sql.SqlServer;
import rx.Completable;
import rx.Observable;
import rx.functions.Func1;

import java.util.UUID;

/**
 * Implementation for SqlServer and its parent interfaces.
 */
@LangDefinition
public class SqlServerImpl
        extends
            GroupableResourceImpl<
                    SqlServer,
                    ServerInner,
                    SqlServerImpl,
                    SqlServerManager>
        implements
            SqlServer,
            SqlServer.Definition,
            SqlServer.Update {

    private FunctionalTaskItem sqlADAdminCreator;
    private boolean allowAzureServicesAccess;
    private SqlFirewallRulesAsExternalChildResourcesImpl sqlFirewallRules;
    private SqlElasticPoolsAsExternalChildResourcesImpl sqlElasticPools;
    private SqlDatabasesAsExternalChildResourcesImpl sqlDatabases;

    protected SqlServerImpl(String name, ServerInner innerObject, SqlServerManager manager) {
        super(name, innerObject, manager);

        this.sqlADAdminCreator = null;
        this.allowAzureServicesAccess = true;
        this.sqlFirewallRules = new SqlFirewallRulesAsExternalChildResourcesImpl(this, "SqlFirewallRule");
        this.sqlElasticPools = new SqlElasticPoolsAsExternalChildResourcesImpl(this, "SqlElasticPool");
        this.sqlDatabases = new SqlDatabasesAsExternalChildResourcesImpl(this, "SqlDatabase");
    }

    @Override
    protected Observable<ServerInner> getInnerAsync() {
        return this.manager().inner().servers().getByResourceGroupAsync(
                this.resourceGroupName(), this.name());
    }

    @Override
    public Observable<SqlServer> createResourceAsync() {
        final SqlServer self = this;
        return this.manager().inner().servers().createOrUpdateAsync(this.resourceGroupName(), this.name(), this.inner())
            .map(new Func1<ServerInner, SqlServer>() {
                @Override
                public SqlServer call(ServerInner serverInner) {
                    setInner(serverInner);
                    return self;
                }
            });
    }

    @Override
    public void beforeGroupCreateOrUpdate() {
        if (this.isInCreateMode()) {
            if (allowAzureServicesAccess) {
                this.sqlFirewallRules
                    .defineInlineFirewallRule("AllowAllWindowsAzureIps")
                    .withStartIPAddress("0.0.0.0")
                    .withEndIPAddress("0.0.0.0");
            }
            if (sqlADAdminCreator != null) {
                this.addPostRunDependent(sqlADAdminCreator);
            }
        }
    }

    @Override
    public Completable afterPostRunAsync(boolean isGroupFaulted) {
        this.sqlADAdminCreator = null;
        this.sqlFirewallRules.clear();
        this.sqlElasticPools.clear();
        this.sqlDatabases.clear();
        return Completable.complete();
    }

    @Override
    public String fullyQualifiedDomainName() {
        return this.inner().fullyQualifiedDomainName();
    }

    @Override
    public String administratorLogin() {
        return this.inner().administratorLogin();
    }

    @Override
    public String kind() {
        return this.inner().kind();
    }

    @Override
    public String version() {
        return this.inner().version();
    }

    @Override
    public SqlFirewallRule setAccessFromAzureServices() {
        SqlFirewallRule firewallRule = this.manager().sqlServers().firewallRules()
                .getBySqlServer(this.resourceGroupName(), this.name(),"AllowAllWindowsAzureIps");
        if (firewallRule == null) {
            firewallRule = this.manager().sqlServers().firewallRules()
                .define("AllowAllWindowsAzureIps")
                .withExistingSqlServer(this.resourceGroupName(), this.name())
                .withIPAddress("0.0.0.0")
                .create();
        }

        return firewallRule;
    }

    @Override
    public void removeAccessFromAzureServices() {
        SqlFirewallRule firewallRule = this.manager().sqlServers().firewallRules()
            .getBySqlServer(this.resourceGroupName(), this.name(),"AllowAllWindowsAzureIps");
        if (firewallRule != null) {
            this.manager().sqlServers().firewallRules()
                .deleteBySqlServer(this.resourceGroupName(), this.name(), "AllowAllWindowsAzureIps");
        }
    }

    @Override
    public SqlActiveDirectoryAdministratorImpl setActiveDirectoryAdministrator(String userLogin, String objectId) {
        ServerAzureADAdministratorInner serverAzureADAdministratorInner = new ServerAzureADAdministratorInner()
            .withLogin(userLogin)
            .withSid(UUID.fromString(objectId))
            .withTenantId(UUID.fromString(this.manager().tenantId()));

        return new SqlActiveDirectoryAdministratorImpl(this.manager().inner().serverAzureADAdministrators().createOrUpdate(this.resourceGroupName(), this.name(), serverAzureADAdministratorInner));
    }

    @Override
    public SqlActiveDirectoryAdministratorImpl getActiveDirectoryAdministrator() {
        ServerAzureADAdministratorInner serverAzureADAdministratorInner = this.manager().inner().serverAzureADAdministrators().get(this.resourceGroupName(), this.name());

        return serverAzureADAdministratorInner != null ? new SqlActiveDirectoryAdministratorImpl(serverAzureADAdministratorInner) : null;
    }

    @Override
    public void removeActiveDirectoryAdministrator() {
        this.manager().inner().serverAzureADAdministrators().delete(this.resourceGroupName(), this.name());
    }

    @Override
    public SqlFirewallRuleOperations.SqlFirewallRuleActionsDefinition firewallRules() {
        return new SqlFirewallRuleOperationsImpl(this, this.manager());
    }

    @Override
    public SqlServerImpl withAdministratorLogin(String administratorLogin) {
        this.inner().withAdministratorLogin(administratorLogin);
        return this;
    }

    @Override
    public SqlServerImpl withAdministratorPassword(String administratorLoginPassword) {
        this.inner().withAdministratorLoginPassword(administratorLoginPassword);
        return this;
    }

    @Override
    public SqlServerImpl withoutAccessFromAzureServices() {
        allowAzureServicesAccess = false;
        return this;
    }

    @Override
    public SqlServer.DefinitionStages.WithCreate withActiveDirectoryAdministrator(final String userLogin, final String objectId) {
        final SqlServerImpl self = this;
        sqlADAdminCreator = new FunctionalTaskItem() {
            @Override
            public Observable<Indexable> call(final Context context) {
                ServerAzureADAdministratorInner serverAzureADAdministratorInner = new ServerAzureADAdministratorInner()
                    .withLogin(userLogin)
                    .withSid(UUID.fromString(objectId))
                    .withTenantId(UUID.fromString(self.manager().tenantId()));

                return self.manager().inner().serverAzureADAdministrators()
                    .createOrUpdateAsync(self.resourceGroupName(), self.name(), serverAzureADAdministratorInner)
                    .flatMap(new Func1<ServerAzureADAdministratorInner, Observable<Indexable>>() {
                        @Override
                        public Observable<Indexable> call(ServerAzureADAdministratorInner serverAzureADAdministratorInner) {
                            return context.voidObservable();
                        }
                    });
            }
        };
        return this;
    }

    @Override
    public SqlFirewallRuleImpl defineFirewallRule(String name) {
        return this.sqlFirewallRules.defineInlineFirewallRule(name);
    }

    @Override
    public SqlServerImpl withNewFirewallRule(String ipAddress) {
        return this.withNewFirewallRule(ipAddress, ipAddress);
    }

    @Override
    public SqlServerImpl withNewFirewallRule(String startIPAddress, String endIPAddress) {
        return this.withNewFirewallRule(startIPAddress, endIPAddress, SdkContext.randomResourceName("firewall_", 15));
    }

    @Override
    public SqlServerImpl withNewFirewallRule(String startIPAddress, String endIPAddress, String firewallRuleName) {
        return sqlFirewallRules
            .defineInlineFirewallRule(firewallRuleName)
            .withStartIPAddress(startIPAddress)
            .withEndIPAddress(endIPAddress)
            .parent();
    }

    @Override
    public SqlServerImpl withoutFirewallRule(String firewallRuleName) {
        sqlFirewallRules.removeInlineFirewallRule(firewallRuleName);
        return this;
    }

    @Override
    public SqlElasticPoolOperations.SqlElasticPoolActionsDefinition elasticPools() {
        return new SqlElasticPoolOperationsImpl(this, this.manager());
    }

    @Override
    public SqlElasticPoolImpl defineElasticPool(String name) {
        return this.sqlElasticPools.defineInlineElasticPool(name);
    }

    @Override
    public SqlServerImpl withNewElasticPool(String elasticPoolName, ElasticPoolEdition elasticPoolEdition) {
        return sqlElasticPools
            .defineInlineElasticPool(elasticPoolName)
            .withEdition(elasticPoolEdition)
            .parent();
    }

    @Override
    public SqlServerImpl withoutElasticPool(String elasticPoolName) {
        sqlElasticPools.removeInlineElasticPool(elasticPoolName);
        return this;
    }

    @Override
    public SqlServerImpl withNewElasticPool(String elasticPoolName, ElasticPoolEdition elasticPoolEdition, String... databaseNames) {
        // call addCreatableDependency for the Elastic Pool external child non-cached item through parent sql server in database
        // beforeGroupInvoke()
        return null;
    }

    @Override
    public SqlDatabaseImpl defineDatabase(String name) {
//    public SqlDatabase.DefinitionStages.Blank<SqlServer.DefinitionStages.WithCreate> defineDatabase(String name) {
        return null;
    }

    @Override
    public SqlServerImpl withNewDatabase(String databaseName) {
        return null;
    }

    @Override
    public SqlServerImpl withoutDatabase(String databaseName) {
        return null;
    }

}
