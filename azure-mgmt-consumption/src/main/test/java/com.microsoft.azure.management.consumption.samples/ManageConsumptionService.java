/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.consumption.samples;

import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.AzureResponseBuilder;
import com.microsoft.azure.PagedList;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.credentials.AzureCliCredentials;
import com.microsoft.azure.management.consumption.BudgetTimePeriod;
import com.microsoft.azure.management.consumption.CategoryType;
import com.microsoft.azure.management.consumption.TimeGrainType;
import com.microsoft.azure.management.consumption.implementation.BudgetInner;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import com.microsoft.azure.management.resources.fluentcore.utils.ProviderRegistrationInterceptor;
import com.microsoft.azure.management.resources.fluentcore.utils.ResourceManagerThrottlingInterceptor;
import com.microsoft.azure.management.resources.fluentcore.utils.SdkContext;
import com.microsoft.azure.management.consumption.implementation.ConsumptionManager;
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
import java.util.concurrent.TimeUnit;

/**
 * Azure Search sample for managing consumption service.
 *  - Create a Budget resource for the subscription
 *  - List all subscription Consumption Budget resources
 *  - Delete the Search service
 */
public class ManageConsumptionService {
    private static boolean testPassed;
    protected static ResourceManager resourceManager;
    protected static ConsumptionManager consumptionManager;

    @Test
    public void canCRUDConsumption() {
        main(null);
        Assert.assertTrue(testPassed);
    }

    /**
     * Main function which runs the actual sample.
     *
     * @return true if sample runs successfully
     */
    public static boolean runSample() {
        final String budgetName = SdkContext.randomResourceName("budget", 20);
        final String rgName = SdkContext.randomResourceName("rgbudget", 20);
        final Region region = Region.US_EAST;

        try {
            resourceManager.resourceGroups().define(rgName)
                .withRegion(region)
                .create();

            BudgetInner budgetInner = new BudgetInner();
            budgetInner.withCategory(CategoryType.COST);
            budgetInner.withAmount(BigDecimal.valueOf(100));
            budgetInner.withTimeGrain(TimeGrainType.MONTHLY);
            budgetInner.withTimePeriod(new BudgetTimePeriod()
                .withStartDate(DateTime.now().toDateTime(DateTimeZone.UTC).minusDays(5))
                .withEndDate(DateTime.now().toDateTime(DateTimeZone.UTC))
            );

            budgetInner = consumptionManager.inner().budgets().createOrUpdateByResourceGroupName(rgName, budgetName, budgetInner);

            PagedList<BudgetInner> budgetList = consumptionManager.inner().budgets().list();

            for (BudgetInner budget : budgetList) {
                System.out.format("Found Consumption Service Budget resource: %s\n", budget.name());
            }

            return true;
        } catch (Exception f) {
            System.out.println(f.getMessage());
            f.printStackTrace();
            return false;
        } finally {
            System.out.println("Did not create any resource groups in Azure. No clean up is necessary");
        }
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
//            consumptionManager = ConsumptionManager.authenticate(restClient, credentials.defaultSubscriptionId());
//
//            resourceManager = ResourceManager.authenticate(restClient, credentials.defaultSubscriptionId());

//            consumptionManager = ConsumptionManager.authenticate(credentials, credentials.defaultSubscriptionId());
//            resourceManager = ResourceManager.authenticate(credentials).withSubscription(credentials.defaultSubscriptionId());

            consumptionManager = ConsumptionManager.authenticate(AzureCliCredentials.create(), credentials.defaultSubscriptionId());
            resourceManager = ResourceManager.authenticate(AzureCliCredentials.create()).withSubscription(credentials.defaultSubscriptionId());

            testPassed = runSample();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
