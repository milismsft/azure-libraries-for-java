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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Implementation for SQL Firewall Rule operations.
 */
@LangDefinition
public class SqlFirewallRuleOperationsImpl
    implements
        SqlFirewallRuleOperations,
        SqlFirewallRuleOperations.SqlFirewallRuleActionsDefinition {

    private SqlServerManager manager;
    private SqlServer sqlServer;

    public SqlFirewallRuleOperationsImpl(SqlServer parent, SqlServerManager manager) {
        Objects.requireNonNull(manager);
        this.sqlServer = parent;
        this.manager = manager;
    }

    public SqlFirewallRuleOperationsImpl(SqlServerManager manager) {
        Objects.requireNonNull(manager);
        this.manager = manager;
    }

    @Override
    public SqlFirewallRule getBySqlServer(String resourceGroupName, String sqlServerName, String name) {
        FirewallRuleInner inner = this.manager.inner().firewallRules().get(resourceGroupName, sqlServerName, name);
        return (inner != null) ? new SqlFirewallRuleImpl(resourceGroupName, sqlServerName, inner.name(), inner, manager) : null;
    }

    @Override
    public Observable<SqlFirewallRule> getBySqlServerAsync(final String resourceGroupName, final String sqlServerName, final String name) {
        return this.manager.inner().firewallRules().getAsync(resourceGroupName, sqlServerName, name)
            .map(new Func1<FirewallRuleInner, SqlFirewallRule>() {
                @Override
                public SqlFirewallRule call(FirewallRuleInner inner) {
                    return new SqlFirewallRuleImpl(resourceGroupName, sqlServerName, inner.name(), inner, manager);
                }
            });
    }

    @Override
    public SqlFirewallRule getBySqlServer(SqlServer sqlServer, String name) {
        if (sqlServer == null) {
            return null;
        }
        FirewallRuleInner inner = this.manager.inner().firewallRules().get(sqlServer.resourceGroupName(), sqlServer.name(), name);
        return (inner != null) ? new SqlFirewallRuleImpl(inner.name(), (SqlServerImpl) sqlServer, inner, manager) : null;
    }

    @Override
    public SqlFirewallRule get(String name) {
        if (sqlServer == null) {
            return null;
        }
        return this.getBySqlServer(this.sqlServer.resourceGroupName(), this.sqlServer.name(), name);
    }

    @Override
    public Observable<SqlFirewallRule> getAsync(String name) {
        if (sqlServer == null) {
            return null;
        }
        return this.getBySqlServerAsync(this.sqlServer.resourceGroupName(), this.sqlServer.name(), name);
    }

    @Override
    public SqlFirewallRule getById(String id) {
        Objects.requireNonNull(id);
        return this.getBySqlServer(ResourceUtils.groupFromResourceId(id),
            ResourceUtils.nameFromResourceId(ResourceUtils.parentRelativePathFromResourceId(id)),
            ResourceUtils.nameFromResourceId(id));
    }

    @Override
    public Observable<SqlFirewallRule> getByIdAsync(String id) {
        Objects.requireNonNull(id);
        return this.getBySqlServerAsync(ResourceUtils.groupFromResourceId(id),
            ResourceUtils.nameFromResourceId(ResourceUtils.parentRelativePathFromResourceId(id)),
            ResourceUtils.nameFromResourceId(id));
    }

    @Override
    public void deleteBySqlServer(String resourceGroupName, String sqlServerName, String name) {
        this.manager.inner().firewallRules().delete(resourceGroupName, sqlServerName, name);
    }

    @Override
    public Completable deleteBySqlServerAsync(String resourceGroupName, String sqlServerName, String name) {
        return this.manager.inner().firewallRules().deleteAsync(resourceGroupName, sqlServerName, name).toCompletable();
    }

    @Override
    public void deleteById(String id) {
        Objects.requireNonNull(id);
        this.deleteBySqlServer(ResourceUtils.groupFromResourceId(id),
            ResourceUtils.nameFromResourceId(ResourceUtils.parentRelativePathFromResourceId(id)),
            ResourceUtils.nameFromResourceId(id));
    }

    @Override
    public Completable deleteByIdAsync(String id) {
        Objects.requireNonNull(id);
        return this.deleteBySqlServerAsync(ResourceUtils.groupFromResourceId(id),
            ResourceUtils.nameFromResourceId(ResourceUtils.parentRelativePathFromResourceId(id)),
            ResourceUtils.nameFromResourceId(id));
    }

    @Override
    public void delete(String name) {
        if (sqlServer != null) {
            this.deleteBySqlServer(this.sqlServer.resourceGroupName(), this.sqlServer.name(), name);
        }
    }

    @Override
    public Completable deleteAsync(String name) {
        if (sqlServer == null) {
            return null;
        }
        return this.deleteBySqlServerAsync(this.sqlServer.resourceGroupName(), this.sqlServer.name(), name);
    }

    @Override
    public List<SqlFirewallRule> listBySqlServer(String resourceGroupName, String sqlServerName) {
        List<SqlFirewallRule> firewallRuleSet = new ArrayList<>();
        for (FirewallRuleInner inner : this.manager.inner().firewallRules().listByServer(resourceGroupName, sqlServerName)) {
            firewallRuleSet.add(new SqlFirewallRuleImpl(resourceGroupName, sqlServerName, inner.name(), inner, manager));
        }
        return Collections.unmodifiableList(firewallRuleSet);
    }

    @Override
    public Observable<SqlFirewallRule> listBySqlServerAsync(final String resourceGroupName, final String sqlServerName) {
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
    public List<SqlFirewallRule> listBySqlServer(SqlServer sqlServer) {
        List<SqlFirewallRule> firewallRuleSet = new ArrayList<>();
        if (sqlServer != null) {
            for (FirewallRuleInner inner : this.manager.inner().firewallRules().listByServer(sqlServer.resourceGroupName(), sqlServer.name())) {
                firewallRuleSet.add(new SqlFirewallRuleImpl(inner.name(), (SqlServerImpl) sqlServer, inner, manager));
            }
        }
        return Collections.unmodifiableList(firewallRuleSet);
    }

    @Override
    public List<SqlFirewallRule> list() {
        if (sqlServer == null) {
            return null;
        }
        return this.listBySqlServer(this.sqlServer.resourceGroupName(), this.sqlServer.name());
    }

    @Override
    public Observable<SqlFirewallRule> listAsync() {
        if (sqlServer == null) {
            return null;
        }
        return this.listBySqlServerAsync(this.sqlServer.resourceGroupName(), this.sqlServer.name());
    }

    @Override
    public DefinitionStages.WithSqlServer define(String name) {
        return new SqlFirewallRuleImpl(null, null, name, new FirewallRuleInner(), this.manager);
    }
}
