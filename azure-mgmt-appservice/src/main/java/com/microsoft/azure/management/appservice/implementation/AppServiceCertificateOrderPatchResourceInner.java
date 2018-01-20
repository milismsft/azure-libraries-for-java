/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.appservice.implementation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.azure.management.appservice.CertificateDetails;
import com.microsoft.azure.management.appservice.CertificateOrderStatus;
import com.microsoft.azure.management.appservice.CertificateProductType;
import com.microsoft.azure.management.appservice.ProvisioningState;
import com.microsoft.azure.management.appservice.ProxyOnlyResource;
import com.microsoft.rest.serializer.JsonFlatten;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Map;

/**
 * ARM resource for a certificate order that is purchased through Azure.
 */
@JsonFlatten
public class AppServiceCertificateOrderPatchResourceInner extends ProxyOnlyResource {
    /**
     * State of the Key Vault secret.
     */
    @JsonProperty(value = "properties.certificates")
    private Map<String, AppServiceCertificateInner> certificates;

    /**
     * Certificate distinguished name.
     */
    @JsonProperty(value = "properties.distinguishedName")
    private String distinguishedName;

    /**
     * Domain verification token.
     */
    @JsonProperty(value = "properties.domainVerificationToken", access = JsonProperty.Access.WRITE_ONLY)
    private String domainVerificationToken;

    /**
     * Duration in years (must be between 1 and 3).
     */
    @JsonProperty(value = "properties.validityInYears")
    private Integer validityInYears;

    /**
     * Certificate key size.
     */
    @JsonProperty(value = "properties.keySize")
    private Integer keySize;

    /**
     * Certificate product type. Possible values include:
     * 'StandardDomainValidatedSsl', 'StandardDomainValidatedWildCardSsl'.
     */
    @JsonProperty(value = "properties.productType", required = true)
    private CertificateProductType productType;

    /**
     * &lt;code&gt;true&lt;/code&gt; if the certificate should be automatically
     * renewed when it expires; otherwise, &lt;code&gt;false&lt;/code&gt;.
     */
    @JsonProperty(value = "properties.autoRenew")
    private Boolean autoRenew;

    /**
     * Status of certificate order. Possible values include: 'Succeeded',
     * 'Failed', 'Canceled', 'InProgress', 'Deleting'.
     */
    @JsonProperty(value = "properties.provisioningState", access = JsonProperty.Access.WRITE_ONLY)
    private ProvisioningState provisioningState;

    /**
     * Current order status. Possible values include: 'Pendingissuance',
     * 'Issued', 'Revoked', 'Canceled', 'Denied', 'Pendingrevocation',
     * 'PendingRekey', 'Unused', 'Expired', 'NotSubmitted'.
     */
    @JsonProperty(value = "properties.status", access = JsonProperty.Access.WRITE_ONLY)
    private CertificateOrderStatus status;

    /**
     * Signed certificate.
     */
    @JsonProperty(value = "properties.signedCertificate", access = JsonProperty.Access.WRITE_ONLY)
    private CertificateDetails signedCertificate;

    /**
     * Last CSR that was created for this order.
     */
    @JsonProperty(value = "properties.csr")
    private String csr;

    /**
     * Intermediate certificate.
     */
    @JsonProperty(value = "properties.intermediate", access = JsonProperty.Access.WRITE_ONLY)
    private CertificateDetails intermediate;

    /**
     * Root certificate.
     */
    @JsonProperty(value = "properties.root", access = JsonProperty.Access.WRITE_ONLY)
    private CertificateDetails root;

    /**
     * Current serial number of the certificate.
     */
    @JsonProperty(value = "properties.serialNumber", access = JsonProperty.Access.WRITE_ONLY)
    private String serialNumber;

    /**
     * Certificate last issuance time.
     */
    @JsonProperty(value = "properties.lastCertificateIssuanceTime", access = JsonProperty.Access.WRITE_ONLY)
    private DateTime lastCertificateIssuanceTime;

    /**
     * Certificate expiration time.
     */
    @JsonProperty(value = "properties.expirationTime", access = JsonProperty.Access.WRITE_ONLY)
    private DateTime expirationTime;

    /**
     * &lt;code&gt;true&lt;/code&gt; if private key is external; otherwise,
     * &lt;code&gt;false&lt;/code&gt;.
     */
    @JsonProperty(value = "properties.isPrivateKeyExternal", access = JsonProperty.Access.WRITE_ONLY)
    private Boolean isPrivateKeyExternal;

    /**
     * Reasons why App Service Certificate is not renewable at the current
     * moment.
     */
    @JsonProperty(value = "properties.appServiceCertificateNotRenewableReasons", access = JsonProperty.Access.WRITE_ONLY)
    private List<String> appServiceCertificateNotRenewableReasons;

    /**
     * Time stamp when the certificate would be auto renewed next.
     */
    @JsonProperty(value = "properties.nextAutoRenewalTimeStamp", access = JsonProperty.Access.WRITE_ONLY)
    private DateTime nextAutoRenewalTimeStamp;

    /**
     * Get the certificates value.
     *
     * @return the certificates value
     */
    public Map<String, AppServiceCertificateInner> certificates() {
        return this.certificates;
    }

    /**
     * Set the certificates value.
     *
     * @param certificates the certificates value to set
     * @return the AppServiceCertificateOrderPatchResourceInner object itself.
     */
    public AppServiceCertificateOrderPatchResourceInner withCertificates(Map<String, AppServiceCertificateInner> certificates) {
        this.certificates = certificates;
        return this;
    }

    /**
     * Get the distinguishedName value.
     *
     * @return the distinguishedName value
     */
    public String distinguishedName() {
        return this.distinguishedName;
    }

    /**
     * Set the distinguishedName value.
     *
     * @param distinguishedName the distinguishedName value to set
     * @return the AppServiceCertificateOrderPatchResourceInner object itself.
     */
    public AppServiceCertificateOrderPatchResourceInner withDistinguishedName(String distinguishedName) {
        this.distinguishedName = distinguishedName;
        return this;
    }

    /**
     * Get the domainVerificationToken value.
     *
     * @return the domainVerificationToken value
     */
    public String domainVerificationToken() {
        return this.domainVerificationToken;
    }

    /**
     * Get the validityInYears value.
     *
     * @return the validityInYears value
     */
    public Integer validityInYears() {
        return this.validityInYears;
    }

    /**
     * Set the validityInYears value.
     *
     * @param validityInYears the validityInYears value to set
     * @return the AppServiceCertificateOrderPatchResourceInner object itself.
     */
    public AppServiceCertificateOrderPatchResourceInner withValidityInYears(Integer validityInYears) {
        this.validityInYears = validityInYears;
        return this;
    }

    /**
     * Get the keySize value.
     *
     * @return the keySize value
     */
    public Integer keySize() {
        return this.keySize;
    }

    /**
     * Set the keySize value.
     *
     * @param keySize the keySize value to set
     * @return the AppServiceCertificateOrderPatchResourceInner object itself.
     */
    public AppServiceCertificateOrderPatchResourceInner withKeySize(Integer keySize) {
        this.keySize = keySize;
        return this;
    }

    /**
     * Get the productType value.
     *
     * @return the productType value
     */
    public CertificateProductType productType() {
        return this.productType;
    }

    /**
     * Set the productType value.
     *
     * @param productType the productType value to set
     * @return the AppServiceCertificateOrderPatchResourceInner object itself.
     */
    public AppServiceCertificateOrderPatchResourceInner withProductType(CertificateProductType productType) {
        this.productType = productType;
        return this;
    }

    /**
     * Get the autoRenew value.
     *
     * @return the autoRenew value
     */
    public Boolean autoRenew() {
        return this.autoRenew;
    }

    /**
     * Set the autoRenew value.
     *
     * @param autoRenew the autoRenew value to set
     * @return the AppServiceCertificateOrderPatchResourceInner object itself.
     */
    public AppServiceCertificateOrderPatchResourceInner withAutoRenew(Boolean autoRenew) {
        this.autoRenew = autoRenew;
        return this;
    }

    /**
     * Get the provisioningState value.
     *
     * @return the provisioningState value
     */
    public ProvisioningState provisioningState() {
        return this.provisioningState;
    }

    /**
     * Get the status value.
     *
     * @return the status value
     */
    public CertificateOrderStatus status() {
        return this.status;
    }

    /**
     * Get the signedCertificate value.
     *
     * @return the signedCertificate value
     */
    public CertificateDetails signedCertificate() {
        return this.signedCertificate;
    }

    /**
     * Get the csr value.
     *
     * @return the csr value
     */
    public String csr() {
        return this.csr;
    }

    /**
     * Set the csr value.
     *
     * @param csr the csr value to set
     * @return the AppServiceCertificateOrderPatchResourceInner object itself.
     */
    public AppServiceCertificateOrderPatchResourceInner withCsr(String csr) {
        this.csr = csr;
        return this;
    }

    /**
     * Get the intermediate value.
     *
     * @return the intermediate value
     */
    public CertificateDetails intermediate() {
        return this.intermediate;
    }

    /**
     * Get the root value.
     *
     * @return the root value
     */
    public CertificateDetails root() {
        return this.root;
    }

    /**
     * Get the serialNumber value.
     *
     * @return the serialNumber value
     */
    public String serialNumber() {
        return this.serialNumber;
    }

    /**
     * Get the lastCertificateIssuanceTime value.
     *
     * @return the lastCertificateIssuanceTime value
     */
    public DateTime lastCertificateIssuanceTime() {
        return this.lastCertificateIssuanceTime;
    }

    /**
     * Get the expirationTime value.
     *
     * @return the expirationTime value
     */
    public DateTime expirationTime() {
        return this.expirationTime;
    }

    /**
     * Get the isPrivateKeyExternal value.
     *
     * @return the isPrivateKeyExternal value
     */
    public Boolean isPrivateKeyExternal() {
        return this.isPrivateKeyExternal;
    }

    /**
     * Get the appServiceCertificateNotRenewableReasons value.
     *
     * @return the appServiceCertificateNotRenewableReasons value
     */
    public List<String> appServiceCertificateNotRenewableReasons() {
        return this.appServiceCertificateNotRenewableReasons;
    }

    /**
     * Get the nextAutoRenewalTimeStamp value.
     *
     * @return the nextAutoRenewalTimeStamp value
     */
    public DateTime nextAutoRenewalTimeStamp() {
        return this.nextAutoRenewalTimeStamp;
    }

}
