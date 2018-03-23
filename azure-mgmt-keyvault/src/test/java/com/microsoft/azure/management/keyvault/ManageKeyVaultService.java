/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.keyvault;

import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.AzureResponseBuilder;
import com.microsoft.azure.PagedList;
import com.microsoft.azure.Resource;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.credentials.AzureCliCredentials;
import com.microsoft.azure.management.keyvault.implementation.KeyVaultManager;
import com.microsoft.azure.management.keyvault.implementation.VaultCreateOrUpdateParametersInner;
import com.microsoft.azure.management.keyvault.implementation.VaultInner;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import com.microsoft.azure.management.resources.fluentcore.utils.ProviderRegistrationInterceptor;
import com.microsoft.azure.management.resources.fluentcore.utils.ResourceManagerThrottlingInterceptor;
import com.microsoft.azure.management.resources.fluentcore.utils.SdkContext;
import com.microsoft.azure.management.resources.implementation.ResourceManager;
import com.microsoft.azure.serializer.AzureJacksonAdapter;
import com.microsoft.rest.LogLevel;
import com.microsoft.rest.RestClient;
import com.microsoft.rest.interceptors.LoggingInterceptor;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Azure Search sample for managing KeyVault service.
 *  - Create a Vault resource
 *  - List all Vault resources
 *  - Delete the Vault resource
 */
public class ManageKeyVaultService {
    private static boolean testPassed;
    protected static ResourceManager resourceManager;
    protected static KeyVaultManager keyVaultManager;
    protected static String tenantId;
    protected static String clientId;

    @Test
    public void canCRUDKeyVault() {
        main(null);
        Assert.assertTrue(testPassed);
    }

    /**
     * Main function which runs the actual sample.
     *
     * @return true if sample runs successfully
     */
    public static boolean runSample() {
        final String kvName = SdkContext.randomResourceName("kv", 20);
        final String rgName = SdkContext.randomResourceName("rgkv", 20);
        final Region region = Region.US_EAST;

        try {
            resourceManager.resourceGroups().define(rgName)
                .withRegion(region)
                .create();

            VaultCreateOrUpdateParametersInner vaultInnerParams = new VaultCreateOrUpdateParametersInner();
            vaultInnerParams.withLocation(region.name());
            vaultInnerParams.withProperties(new VaultProperties());
            vaultInnerParams.properties().withAccessPolicies(new ArrayList<AccessPolicyEntry>());
            vaultInnerParams.properties().accessPolicies().add(new AccessPolicyEntry()
                .withTenantId(UUID.fromString(tenantId))
                .withObjectId(clientId)
                .withPermissions(new Permissions().withSecrets(new ArrayList<SecretPermissions>(SecretPermissions.values()))));
            vaultInnerParams.properties().withTenantId(UUID.fromString(tenantId));
            vaultInnerParams.properties().withSku(new Sku().withName(SkuName.STANDARD));

            VaultInner vaultInnerResult = keyVaultManager.inner().vaults()
                .createOrUpdate(rgName, kvName, vaultInnerParams);

            PagedList<Resource> vaultList = keyVaultManager.inner().vaults()
                .list();


            for (Resource vaultItem : vaultList) {
                System.out.format("Found KeyVault resource: %s\n", vaultItem.name());
            }

            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                System.out.println("Deleting resource group: " + rgName);
                resourceManager.resourceGroups().deleteByName(rgName);
                System.out.println("Deleted resource group: " + rgName);
            } catch (NullPointerException npe) {
                System.out.println("Did not create any resources in Azure. No clean up is necessary");
            } catch (Exception g) {
                g.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Main entry point.
     *
     * @param args the parameters
     */
    public static void main(String[] args) {
        try {
            //=============================================================
            // Authenticate

            final File credFile = new File(System.getenv("AZURE_AUTH_LOCATION"));
            ApplicationTokenCredentials credentials = ApplicationTokenCredentials.fromFile(credFile);
            clientId = credentials.clientId();
            tenantId = credentials.domain();

            //============================================================
            // Authenticate using default RestClient

            keyVaultManager = KeyVaultManager.authenticate(credentials, credentials.defaultSubscriptionId());
            resourceManager = ResourceManager.authenticate(credentials).withSubscription(credentials.defaultSubscriptionId());

//            //============================================================
//            // Authenticate using custom RestClient
//
//            RestClient.Builder builder = new RestClient.Builder()
//                .withBaseUrl(AzureEnvironment.AZURE.url(AzureEnvironment.Endpoint.RESOURCE_MANAGER))
//                .withSerializerAdapter(new AzureJacksonAdapter())
//                .withResponseBuilderFactory(new AzureResponseBuilder.Factory())
//                .withInterceptor(new ProviderRegistrationInterceptor(credentials))
//                .withCredentials(credentials)
//                .withLogLevel(LogLevel.NONE)
//                .withReadTimeout(3, TimeUnit.MINUTES)
//                .withNetworkInterceptor(new LoggingInterceptor(LogLevel.BODY_AND_HEADERS))
//                .withInterceptor(new ResourceManagerThrottlingInterceptor());
//            RestClient restClient = builder.build();
//            keyVaultManager = KeyVaultManager.authenticate(restClient, credentials.clientId(), credentials.defaultSubscriptionId());
//            resourceManager = ResourceManager.authenticate(restClient).withSubscription(credentials.defaultSubscriptionId());
//
//            //============================================================
//            // Authenticate using default RestClient and Azure CLI credentials
//
//            keyVaultManager = KeyVaultManager.authenticate(AzureCliCredentials.create(), credentials.defaultSubscriptionId());
//            resourceManager = ResourceManager.authenticate(AzureCliCredentials.create()).withSubscription(credentials.defaultSubscriptionId());

            testPassed = runSample();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
