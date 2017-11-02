/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.batchai;

import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.microsoft.rest.ExpandableStringEnum;

/**
 * Defines values for FileServerType.
 */
public final class FileServerType extends ExpandableStringEnum<FileServerType> {
    /** Static value nfs for FileServerType. */
    public static final FileServerType NFS = fromString("nfs");

    /** Static value glusterfs for FileServerType. */
    public static final FileServerType GLUSTERFS = fromString("glusterfs");

    /**
     * Creates or finds a FileServerType from its string representation.
     * @param name a name to look for
     * @return the corresponding FileServerType
     */
    @JsonCreator
    public static FileServerType fromString(String name) {
        return fromString(name, FileServerType.class);
    }

    /**
     * @return known FileServerType values
     */
    public static Collection<FileServerType> values() {
        return values(FileServerType.class);
    }
}
