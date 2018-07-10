/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.containerregistry;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The authorization properties for accessing the source code repository.
 */
public class SourceControlAuthInfo {
    /**
     * The type of Auth token. Possible values include: 'PAT', 'OAuth'.
     */
    @JsonProperty(value = "tokenType")
    private TokenType tokenType;

    /**
     * The access token used to access the source control provider.
     */
    @JsonProperty(value = "token", required = true)
    private String token;

    /**
     * The refresh token used to refresh the access token.
     */
    @JsonProperty(value = "refreshToken")
    private String refreshToken;

    /**
     * The scope of the access token.
     */
    @JsonProperty(value = "scope")
    private String scope;

    /**
     * Time in seconds that the token remains valid.
     */
    @JsonProperty(value = "expiresIn")
    private Integer expiresIn;

    /**
     * Get the tokenType value.
     *
     * @return the tokenType value
     */
    public TokenType tokenType() {
        return this.tokenType;
    }

    /**
     * Set the tokenType value.
     *
     * @param tokenType the tokenType value to set
     * @return the SourceControlAuthInfo object itself.
     */
    public SourceControlAuthInfo withTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    /**
     * Get the token value.
     *
     * @return the token value
     */
    public String token() {
        return this.token;
    }

    /**
     * Set the token value.
     *
     * @param token the token value to set
     * @return the SourceControlAuthInfo object itself.
     */
    public SourceControlAuthInfo withToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * Get the refreshToken value.
     *
     * @return the refreshToken value
     */
    public String refreshToken() {
        return this.refreshToken;
    }

    /**
     * Set the refreshToken value.
     *
     * @param refreshToken the refreshToken value to set
     * @return the SourceControlAuthInfo object itself.
     */
    public SourceControlAuthInfo withRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    /**
     * Get the scope value.
     *
     * @return the scope value
     */
    public String scope() {
        return this.scope;
    }

    /**
     * Set the scope value.
     *
     * @param scope the scope value to set
     * @return the SourceControlAuthInfo object itself.
     */
    public SourceControlAuthInfo withScope(String scope) {
        this.scope = scope;
        return this;
    }

    /**
     * Get the expiresIn value.
     *
     * @return the expiresIn value
     */
    public Integer expiresIn() {
        return this.expiresIn;
    }

    /**
     * Set the expiresIn value.
     *
     * @param expiresIn the expiresIn value to set
     * @return the SourceControlAuthInfo object itself.
     */
    public SourceControlAuthInfo withExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

}
