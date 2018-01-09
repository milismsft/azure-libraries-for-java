/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.sql;

import com.microsoft.azure.management.apigeneration.Fluent;
import com.microsoft.azure.management.apigeneration.Method;
import com.microsoft.azure.management.resources.fluentcore.arm.models.HasName;
import com.microsoft.azure.management.resources.fluentcore.model.Indexable;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import rx.Completable;
import rx.Observable;

import java.util.Set;

/**
 * Base class for Azure SQL Server child resource operations.
 */
@Fluent
public interface SqlChildrenOperations<T> {

    /**
     * Gets the information about a child resource from Azure SQL server, identifying it by its name and its resource group.
     *
     * @param resourceGroupName the name of resource group
     * @param sqlServerName the name of SQL server parent resource
     * @param name the name of the child resource
     * @return an immutable representation of the resource
     */
    T get(String resourceGroupName, String sqlServerName, String name);

    /**
     * Asynchronously gets the information about a child resource from Azure SQL server, identifying it by its name and its resource group.
     *
     * @param resourceGroupName the name of resource group
     * @param sqlServerName the name of SQL server parent resource
     * @param name the name of the child resource
     * @return a representation of the deferred computation of this call returning the found resource
     */
    Observable<T> getAsync(String resourceGroupName, String sqlServerName, String name);

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
     * Deletes a child resource from Azure SQL server, identifying it by its name and its resource group.
     *
     * @param resourceGroupName the name of resource group
     * @param sqlServerName the name of SQL server parent resource
     * @param name the name of the child resource
     */
    void delete(String resourceGroupName, String sqlServerName, String name);

    /**
     * Asynchronously delete a child resource from Azure SQL server, identifying it by its name and its resource group.
     *
     * @param resourceGroupName the name of resource group
     * @param sqlServerName the name of SQL server parent resource
     * @param name the name of the child resource
     * @return a representation of the deferred computation of this call
     */
    Completable deleteAsync(String resourceGroupName, String sqlServerName, String name);

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
    Set<T> list(String resourceGroupName, String sqlServerName);

    /**
     * Asynchronously lists Azure SQL child resources of the specified Azure SQL server in the specified resource group.
     *
     * @param resourceGroupName the name of the resource group to list the resources from
     * @param sqlServerName the name of parent Azure SQL server.
     * @return a representation of the deferred computation of this call
     */
    Observable<T> listAsync(String resourceGroupName, String sqlServerName);

    /**
     * Base interface for Azure SQL Server child resource create operations.
     */
    interface SqlChildrenCreatableDefinition<T> {

        /**
         * Execute the create request.
         *
         * @return the create resource
         */
        @Method
        T create();

        /**
         * Puts the request into the queue and allow the HTTP client to execute
         * it when system resources are available.
         *
         * @param callback the callback to handle success and failure
         * @return a handle to cancel the request
         */
        @Method
        ServiceFuture<T> createAsync(final ServiceCallback<T> callback);

        /**
         * Puts the request into the queue and allow the HTTP client to execute
         * it when system resources are available.
         *
         * @return an observable of the request
         */
        @Method
        Observable<T> createAsync();
    }

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
        Set<T> list();

        /**
         * Asynchronously lists Azure SQL child resources.
         *
         * @return a representation of the deferred computation of this call
         */
        Observable<T> listAsync();
    }
}

