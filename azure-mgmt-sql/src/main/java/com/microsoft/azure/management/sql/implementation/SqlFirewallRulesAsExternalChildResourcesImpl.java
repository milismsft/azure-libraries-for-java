/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.management.resources.fluentcore.arm.collection.implementation.ExternalChildResourcesNonCachedImpl;
import com.microsoft.azure.management.sql.SqlFirewallRule;
import com.microsoft.azure.management.sql.SqlServer;

/**
 * Represents a SQL Firewall rules collection associated with an Azure SQL server.
 */
@LangDefinition
public class SqlFirewallRulesAsExternalChildResourcesImpl
    extends ExternalChildResourcesNonCachedImpl<SqlFirewallRuleImpl,
    SqlFirewallRule,
    FirewallRuleInner,
    SqlServerImpl,
    SqlServer> {

    /**
     * Creates a new ExternalNonInlineChildResourcesImpl.
     *
     * @param parent            the parent Azure resource
     * @param childResourceName the child resource name
     */
    protected SqlFirewallRulesAsExternalChildResourcesImpl(SqlServerImpl parent, String childResourceName) {
        super(parent, parent.taskGroup(), childResourceName);
    }

    SqlFirewallRuleImpl defineFirewallRule(String name) {
        return prepareDefine(new SqlFirewallRuleImpl(name, this.parent(), new FirewallRuleInner(), this.parent().manager()));
    }

    SqlFirewallRuleImpl updateWebhook(String name) {
        return prepareUpdate(new SqlFirewallRuleImpl(name, this.parent(), new FirewallRuleInner(), this.parent().manager()));
    }

    void withoutWebhook(String name) {
        prepareRemove(new SqlFirewallRuleImpl(name, this.parent(), new FirewallRuleInner(), this.parent().manager()));
    }
}
