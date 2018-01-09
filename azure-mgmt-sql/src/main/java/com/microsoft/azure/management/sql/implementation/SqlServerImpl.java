/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.sql.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.management.resources.fluentcore.arm.models.implementation.GroupableResourceImpl;
import com.microsoft.azure.management.resources.fluentcore.utils.SdkContext;
import com.microsoft.azure.management.sql.FirewallRules;
import com.microsoft.azure.management.sql.SqlFirewallRule;
import com.microsoft.azure.management.sql.SqlFirewallRuleOperations;
import com.microsoft.azure.management.sql.SqlServer;
import rx.Completable;
import rx.Observable;
import rx.functions.Func1;

import java.util.Set;

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

    private boolean allowAzureServicesAccess;
    private SqlFirewallRulesAsExternalChildResourcesImpl sqlFirewallRules;

    protected SqlServerImpl(String name, ServerInner innerObject, SqlServerManager manager) {
        super(name, innerObject, manager);

        allowAzureServicesAccess = true;
        sqlFirewallRules = new SqlFirewallRulesAsExternalChildResourcesImpl(this, "SqlFirewallRule");
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
        if (this.isInCreateMode() && allowAzureServicesAccess) {
            this.withNewFirewallRule("0.0.0.0", "0.0.0.0", "AllowAllWindowsAzureIps");
        }
    }

    @Override
    public Completable afterPostRunAsync(boolean isGroupFaulted) {
        this.sqlFirewallRules.clear();
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
    public SqlFirewallRule addAccessFromAzureServices() {
        SqlFirewallRule firewallRule = this.manager().sqlServers().firewallRules()
                .get(this.resourceGroupName(), this.name(),"AllowAllWindowsAzureIps");
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
            .get(this.resourceGroupName(), this.name(),"AllowAllWindowsAzureIps");
        if (firewallRule != null) {
            this.manager().sqlServers().firewallRules()
                .delete(this.resourceGroupName(), this.name(), "AllowAllWindowsAzureIps");
        }
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

//    @Override
//    public SqlFirewallRule.DefinitionStages.Blank<SqlServer.DefinitionStages.WithCreate> defineFirewallRule(String name) {
//        return this.sqlFirewallRules.defineFirewallRule(name);
//    }

    @Override
    public SqlFirewallRuleImpl defineFirewallRule(String name) {
        return this.sqlFirewallRules.defineFirewallRule(name);
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
            .defineFirewallRule(firewallRuleName)
            .withStartIPAddress(startIPAddress)
            .withEndIPAddress(endIPAddress)
            .parent();
    }

    @Override
    public SqlServerImpl withoutFirewallRule(String firewallRuleName) {
        sqlFirewallRules.withoutWebhook(firewallRuleName);
        return this;
    }
}
