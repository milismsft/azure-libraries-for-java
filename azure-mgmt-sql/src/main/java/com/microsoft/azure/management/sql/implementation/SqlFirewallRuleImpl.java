/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import com.microsoft.azure.management.resources.fluentcore.arm.ResourceUtils;
import com.microsoft.azure.management.resources.fluentcore.arm.models.implementation.ExternalChildResourceImpl;
import com.microsoft.azure.management.sql.SqlFirewallRule;
import com.microsoft.azure.management.sql.SqlFirewallRuleOperations;
import com.microsoft.azure.management.sql.SqlServer;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import rx.Observable;
import rx.functions.Func1;

/**
 * Implementation for SqlFirewallRule.
 */
@LangDefinition
public class SqlFirewallRuleImpl
    extends
        ExternalChildResourceImpl<SqlFirewallRule, FirewallRuleInner, SqlServerImpl, SqlServer>
    implements
        SqlFirewallRule,
        SqlFirewallRule.SqlFirewallRuleDefinition<SqlServer.DefinitionStages.WithCreate>,
        SqlFirewallRule.Update,
        SqlFirewallRuleOperations.SqlFirewallRuleOperationsDefinition {

    private SqlServerManager sqlServerManager;
    private String resourceGroupName;
    private String sqlServerName;

    /**
     * Creates an instance of external child resource in-memory.
     *
     * @param name        the name of this external child resource
     * @param parent      reference to the parent of this external child resource
     * @param innerObject reference to the inner object representing this external child resource
     * @param sqlServerManager reference to the SQL server manager that accesses firewall rule operations
     */
    SqlFirewallRuleImpl(String name, SqlServerImpl parent, FirewallRuleInner innerObject, SqlServerManager sqlServerManager) {
        super(name, parent, innerObject);
        this.sqlServerManager = sqlServerManager;
        if (parent != null) {
            this.resourceGroupName = parent.resourceGroupName();
            this.sqlServerName = parent.name();
        }
    }

    /**
     * Creates an instance of external child resource in-memory.
     *
     * @param resourceGroupName the resource group name
     * @param sqlServerName the parent SQL server name
     * @param name        the name of this external child resource
     * @param innerObject reference to the inner object representing this external child resource
     * @param sqlServerManager reference to the SQL server manager that accesses firewall rule operations
     */
    SqlFirewallRuleImpl(String resourceGroupName, String sqlServerName, String name, FirewallRuleInner innerObject, SqlServerManager sqlServerManager) {
        super(name, null, innerObject);
        this.sqlServerManager = sqlServerManager;
        this.resourceGroupName = resourceGroupName;
        this.sqlServerName = sqlServerName;
    }

    @Override
    public String id() {
        return this.inner().id();
    }

    @Override
    public SqlFirewallRule create() {
        return this.createAsync().toBlocking().last();
    }

    @Override
    public ServiceFuture<SqlFirewallRule> createAsync(ServiceCallback<SqlFirewallRule> callback) {
        return ServiceFuture.fromBody(this.createAsync(), callback);
    }

    @Override
    public Observable<SqlFirewallRule> createAsync() {
        final SqlFirewallRuleImpl self = this;
        return this.sqlServerManager.inner().firewallRules()
            .createOrUpdateAsync(this.resourceGroupName, this.sqlServerName, this.name(),this.inner())
            .map(new Func1<FirewallRuleInner, SqlFirewallRule>() {
                @Override
                public SqlFirewallRule call(FirewallRuleInner inner) {
                    self.setInner(inner);
                    return self;
                }
            });
    }

    @Override
    public Observable<SqlFirewallRule> updateAsync() {
        final SqlFirewallRuleImpl self = this;
        return this.sqlServerManager.inner().firewallRules()
            .createOrUpdateAsync(this.resourceGroupName, this.sqlServerName, this.name(),this.inner())
            .map(new Func1<FirewallRuleInner, SqlFirewallRule>() {
                @Override
                public SqlFirewallRule call(FirewallRuleInner inner) {
                    self.setInner(inner);
                    return self;
                }
            });
    }

    @Override
    public Observable<Void> deleteAsync() {
        return this.sqlServerManager.inner().firewallRules().deleteAsync(this.resourceGroupName, this.sqlServerName, this.name());
    }

    @Override
    protected Observable<FirewallRuleInner> getInnerAsync() {
        return this.sqlServerManager.inner().firewallRules().getAsync(this.resourceGroupName, this.sqlServerName, this.name());
    }

    @Override
    public SqlFirewallRuleImpl update() {
        return this;
    }

    @Override
    public String sqlServerName() {
        return this.sqlServerName;
    }

    @Override
    public String startIPAddress() {
        return this.inner().startIpAddress();
    }

    @Override
    public String endIPAddress() {
        return this.inner().endIpAddress();
    }

    @Override
    public String kind() {
        return this.inner().kind();
    }

    @Override
    public Region region() {
        return Region.fromName(this.inner().location());
    }

    @Override
    public void delete() {
        this.sqlServerManager.inner().firewallRules().delete(this.resourceGroupName, this.sqlServerName, this.name());
    }

    @Override
    public String parentId() {
        return ResourceUtils.parentResourceIdFromResourceId(this.id());
    }

    @Override
    public SqlFirewallRule apply() {
        return this.applyAsync().toBlocking().last();
    }

    @Override
    public Observable<SqlFirewallRule> applyAsync() {
        return this.updateAsync();
    }

    @Override
    public ServiceFuture<SqlFirewallRule> applyAsync(ServiceCallback<SqlFirewallRule> callback) {
        return ServiceFuture.fromBody(this.updateAsync(), callback);
    }

    @Override
    public SqlFirewallRuleImpl withStartIPAddress(String startIPAddress) {
        this.inner().withStartIpAddress(startIPAddress);
        return this;
    }

    @Override
    public SqlFirewallRuleImpl withEndIPAddress(String endIPAddress) {
        this.inner().withEndIpAddress(endIPAddress);
        return this;
    }

    @Override
    public SqlFirewallRuleImpl withExistingSqlServer(String resourceGroupName, String sqlServerName) {
        this.resourceGroupName = resourceGroupName;
        this.sqlServerName = sqlServerName;
        return this;
    }

    @Override
    public SqlFirewallRuleImpl withSqlServerId(String sqlServerId) {
        this.resourceGroupName = ResourceUtils.groupFromResourceId(sqlServerId);
        this.sqlServerName = ResourceUtils.nameFromResourceId(ResourceUtils.parentRelativePathFromResourceId(sqlServerId));
        return this;
    }

    @Override
    public SqlFirewallRuleImpl withIPAddressRange(String startIPAddress, String endIPAddress) {
        this.inner().withStartIpAddress(startIPAddress);
        this.inner().withEndIpAddress(endIPAddress);
        return this;
    }

    @Override
    public SqlFirewallRuleImpl withIPAddress(String ipAddress) {
        this.inner().withStartIpAddress(ipAddress);
        this.inner().withEndIpAddress(ipAddress);
        return this;
    }

    @Override
    public SqlServerImpl attach() {
        return parent();
    }
}
