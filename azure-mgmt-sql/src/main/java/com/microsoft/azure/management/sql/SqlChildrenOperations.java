/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.apigeneration.Beta;
import com.microsoft.azure.management.apigeneration.Fluent;
import com.microsoft.azure.management.apigeneration.Method;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import rx.Completable;
import rx.Observable;

import java.util.List;
import java.util.Set;

/**
 * Base class for Azure SQL Server child resource operations.
 */
@Fluent
@Beta(Beta.SinceVersion.V2_0_0)
public interface SqlChildrenOperations<T> {

    /**
     * Gets the information about a child resource from Azure SQL server, identifying it by its name and its resource group.
     *
     * @param resourceGroupName the name of resource group
     * @param sqlServerName the name of SQL server parent resource
     * @param name the name of the child resource
     * @return an immutable representation of the resource
     */
    T getBySqlServer(String resourceGroupName, String sqlServerName, String name);

    /**
     * Asynchronously gets the information about a child resource from Azure SQL server, identifying it by its name and its resource group.
     *
     * @param resourceGroupName the name of resource group
     * @param sqlServerName the name of SQL server parent resource
     * @param name the name of the child resource
     * @return a representation of the deferred computation of this call returning the found resource
     */
    Observable<T> getBySqlServerAsync(String resourceGroupName, String sqlServerName, String name);

    /**
     * Gets the information about a child resource from Azure SQL server, identifying it by its name and its resource group.
     *
     * @param sqlServer the SQL server parent resource
     * @param name the name of the child resource
     * @return an immutable representation of the resource
     */
    @Deprecated
    T getBySqlServer(SqlServer sqlServer, String name);

    /**
     * Gets the information about a child resource from Azure SQL server using the resource ID.
     *
     * @param id the ID of the resource.
     * @return an immutable representation of the resource
     */
    T getById(String id);

    /**
     * Asynchronously gets the information about a child resource from Azure SQL server using the resource ID.
     *
     * @param id the ID of the resource.
     * @return a representation of the deferred computation of this call
     */
    Observable<T> getByIdAsync(String id);

    /**
     * Deletes a child resource from Azure SQL server, identifying it by its name and its resource group.
     *
     * @param resourceGroupName the name of resource group
     * @param sqlServerName the name of SQL server parent resource
     * @param name the name of the child resource
     */
    void deleteBySqlServer(String resourceGroupName, String sqlServerName, String name);

    /**
     * Asynchronously delete a child resource from Azure SQL server, identifying it by its name and its resource group.
     *
     * @param resourceGroupName the name of resource group
     * @param sqlServerName the name of SQL server parent resource
     * @param name the name of the child resource
     * @return a representation of the deferred computation of this call
     */
    Completable deleteBySqlServerAsync(String resourceGroupName, String sqlServerName, String name);

    /**
     * Deletes a child resource from Azure SQL server, identifying it by its resource ID.
     *
     * @param id the resource ID of the resource to delete
     */
    void deleteById(String id);

    /**
     * Asynchronously delete a child resource from Azure SQL server, identifying it by its resource ID.
     *
     * @param id the resource ID of the resource to delete
     * @return a representation of the deferred computation of this call
     */
    Completable deleteByIdAsync(String id);

    /**
     * Lists Azure SQL child resources of the specified Azure SQL server in the specified resource group.
     *
     * @param resourceGroupName the name of the resource group to list the resources from
     * @param sqlServerName the name of parent Azure SQL server.
     * @return the list of resources
     */
    List<T> listBySqlServer(String resourceGroupName, String sqlServerName);

    /**
     * Asynchronously lists Azure SQL child resources of the specified Azure SQL server in the specified resource group.
     *
     * @param resourceGroupName the name of the resource group to list the resources from
     * @param sqlServerName the name of parent Azure SQL server.
     * @return a representation of the deferred computation of this call
     */
    Observable<T> listBySqlServerAsync(String resourceGroupName, String sqlServerName);

    /**
     * Lists Azure SQL child resources of the specified Azure SQL server in the specified resource group.
     *
     * @param sqlServer the parent Azure SQL server.
     * @return the list of resources
     */
    @Deprecated
    List<T> listBySqlServer(SqlServer sqlServer);

//    /**
//     * Base interface for Azure SQL Server child resource create operations.
//     */
//    interface SqlChildrenCreatableDefinition<T> {
//
//        /**
//         * Execute the create request.
//         *
//         * @return the create resource
//         */
//        @Method
//        T create();
//
//        /**
//         * Puts the request into the queue and allow the HTTP client to execute
//         * it when system resources are available.
//         *
//         * @param callback the callback to handle success and failure
//         * @return a handle to cancel the request
//         */
//        @Method
//        ServiceFuture<T> createAsync(final ServiceCallback<T> callback);
//
//        /**
//         * Puts the request into the queue and allow the HTTP client to execute
//         * it when system resources are available.
//         *
//         * @return an observable of the request
//         */
//        @Method
//        Observable<T> createAsync();
//    }
//
    /**
     * Base interface for Azure SQL Server child resource actions.
     */
    interface SqlChildrenActionsDefinition<T> {
        /**
         * Gets the information about a child resource from Azure SQL server.
         *
         * @param name the name of the child resource
         * @return an immutable representation of the resource
         */
        T get(String name);

        /**
         * Asynchronously gets the information about a child resource from Azure SQL server.
         *
         * @param name the name of the child resource
         * @return a representation of the deferred computation of this call returning the found resource
         */
        Observable<T> getAsync(String name);

        /**
         * Gets the information about a child resource from Azure SQL server using the resource ID.
         *
         * @param id the ID of the resource.
         * @return an immutable representation of the resource
         */
        T getById(String id);

        /**
         * Asynchronously gets the information about a child resource from Azure SQL server using the resource ID.
         *
         * @param id the ID of the resource.
         * @return an immutable representation of the resource
         */
        Observable<T> getByIdAsync(String id);

        /**
         * Deletes a child resource from Azure SQL server.
         *
         * @param name the name of the child resource
         */
        void delete(String name);

        /**
         * Asynchronously delete a child resource from Azure SQL server.
         *
         * @param name the name of the child resource
         * @return a representation of the deferred computation of this call
         */
        Completable deleteAsync(String name);

        /**
         * Deletes a child resource from Azure SQL server, identifying it by its resource ID.
         *
         * @param id the resource ID of the resource to delete
         */
        void deleteById(String id);

        /**
         * Asynchronously delete a child resource from Azure SQL server, identifying it by its resource ID.
         *
         * @param id the resource ID of the resource to delete
         * @return a representation of the deferred computation of this call
         */
        Completable deleteByIdAsync(String id);

        /**
         * Lists Azure SQL child resources.
         *
         * @return the list of resources
         */
        List<T> list();

        /**
         * Asynchronously lists Azure SQL child resources.
         *
         * @return a representation of the deferred computation of this call
         */
        Observable<T> listAsync();
    }
}

