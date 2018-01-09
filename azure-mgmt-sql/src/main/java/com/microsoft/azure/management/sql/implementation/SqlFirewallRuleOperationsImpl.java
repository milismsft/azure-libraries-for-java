/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.management.resources.fluentcore.arm.ResourceUtils;
import com.microsoft.azure.management.sql.SqlFirewallRule;
import com.microsoft.azure.management.sql.SqlFirewallRuleOperations;
import com.microsoft.azure.management.sql.SqlServer;
import rx.Completable;
import rx.Observable;
import rx.functions.Func1;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation for SQL Firewall rule operations.
 */
@LangDefinition
public class SqlFirewallRuleOperationsImpl
    implements
        SqlFirewallRuleOperations,
        SqlFirewallRuleOperations.SqlFirewallRuleActionsDefinition {

    private SqlServerManager manager;
    private SqlServer sqlServer;

    public SqlFirewallRuleOperationsImpl(SqlServer parent, SqlServerManager manager) {
        this.sqlServer = parent;
        this.manager = manager;
    }

    public SqlFirewallRuleOperationsImpl(SqlServerManager manager) {
        this.manager = manager;
    }

    @Override
    public SqlFirewallRule get(String resourceGroupName, String sqlServerName, String name) {
        FirewallRuleInner inner = this.manager.inner().firewallRules().get(resourceGroupName, sqlServerName, name);
        return (inner != null) ? new SqlFirewallRuleImpl(resourceGroupName, sqlServerName, inner.name(), inner, manager) : null;
    }

    @Override
    public Observable<SqlFirewallRule> getAsync(final String resourceGroupName, final String sqlServerName, final String name) {
        return this.manager.inner().firewallRules().getAsync(resourceGroupName, sqlServerName, name)
            .map(new Func1<FirewallRuleInner, SqlFirewallRule>() {
                @Override
                public SqlFirewallRule call(FirewallRuleInner inner) {
                    return new SqlFirewallRuleImpl(resourceGroupName, sqlServerName, inner.name(), inner, manager);
                }
            });
    }

    @Override
    public SqlFirewallRule get(String name) {
        return this.get(this.sqlServer.resourceGroupName(), this.sqlServer.name(), name);
    }

    @Override
    public Observable<SqlFirewallRule> getAsync(String name) {
        return this.getAsync(this.sqlServer.resourceGroupName(), this.sqlServer.name(), name);
    }

    @Override
    public SqlFirewallRule getById(String id) {
        return this.get(ResourceUtils.groupFromResourceId(id),
            ResourceUtils.nameFromResourceId(ResourceUtils.parentRelativePathFromResourceId(id)),
            ResourceUtils.nameFromResourceId(id));
    }

    @Override
    public Observable<SqlFirewallRule> getByIdAsync(String id) {
        return this.getAsync(ResourceUtils.groupFromResourceId(id),
            ResourceUtils.nameFromResourceId(ResourceUtils.parentRelativePathFromResourceId(id)),
            ResourceUtils.nameFromResourceId(id));
    }

    @Override
    public void delete(String resourceGroupName, String sqlServerName, String name) {
        this.manager.inner().firewallRules().delete(resourceGroupName, sqlServerName, name);
    }

    @Override
    public Completable deleteAsync(String resourceGroupName, String sqlServerName, String name) {
        return this.manager.inner().firewallRules().deleteAsync(resourceGroupName, sqlServerName, name).toCompletable();
    }

    @Override
    public void deleteById(String id) {
        this.delete(ResourceUtils.groupFromResourceId(id),
            ResourceUtils.nameFromResourceId(ResourceUtils.parentRelativePathFromResourceId(id)),
            ResourceUtils.nameFromResourceId(id));
    }

    @Override
    public Completable deleteByIdAsync(String id) {
        return this.deleteAsync(ResourceUtils.groupFromResourceId(id),
            ResourceUtils.nameFromResourceId(ResourceUtils.parentRelativePathFromResourceId(id)),
            ResourceUtils.nameFromResourceId(id));
    }

    @Override
    public void delete(String name) {
        this.delete(this.sqlServer.resourceGroupName(), this.sqlServer.name(), name);
    }

    @Override
    public Completable deleteAsync(String name) {
        return this.deleteAsync(this.sqlServer.resourceGroupName(), this.sqlServer.name(), name);
    }

    @Override
    public Set<SqlFirewallRule> list(String resourceGroupName, String sqlServerName) {
        Set<SqlFirewallRule> firewallRuleSet = new HashSet<>();
        for (FirewallRuleInner inner : this.manager.inner().firewallRules().listByServer(resourceGroupName, sqlServerName)) {
            firewallRuleSet.add(new SqlFirewallRuleImpl(resourceGroupName, sqlServerName, inner.name(), inner, manager));
        }
        return Collections.unmodifiableSet(firewallRuleSet);
    }

    @Override
    public Observable<SqlFirewallRule> listAsync(final String resourceGroupName, final String sqlServerName) {
        return this.manager.inner().firewallRules().listByServerAsync(resourceGroupName, sqlServerName)
            .flatMap(new Func1<List<FirewallRuleInner>, Observable<FirewallRuleInner>>() {
                @Override
                public Observable<FirewallRuleInner> call(List<FirewallRuleInner> firewallRuleInners) {
                    return Observable.from(firewallRuleInners);
                }})
            .map(new Func1<FirewallRuleInner, SqlFirewallRule>() {
                @Override
                public SqlFirewallRule call(FirewallRuleInner inner) {
                    return new SqlFirewallRuleImpl(resourceGroupName, sqlServerName, inner.name(), inner, manager);
                }
            });
    }

    @Override
    public Set<SqlFirewallRule> list() {
        return this.list(this.sqlServer.resourceGroupName(), this.sqlServer.name());
    }

    @Override
    public Observable<SqlFirewallRule> listAsync() {
        return this.listAsync(this.sqlServer.resourceGroupName(), this.sqlServer.name());
    }

    @Override
    public DefinitionStages.WithSqlServer define(String name) {
        return new SqlFirewallRuleImpl(name, null, new FirewallRuleInner(), this.manager);
    }
}
