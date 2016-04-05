/*******************************************************************************
 * Copyright (C) 2005-2013 Alfresco Software Limited.
 * <p/>
 * This file is part of the Alfresco Mobile SDK.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.alfresco.mobile.android.api.model;

import java.io.Serializable;


/**
 * @author Jean Marie Pascal
 * @since 1.3.0
 */
public interface Company extends Serializable
{

    /**
     * Returns the name of the company. Returns null if not available.
     *
     * @since 1.3.0
     */
    String getName();

    /**
     * Returns the first line of the company adress. Returns null if not
     * available.
     *
     * @since 1.3.0
     */
    String getAddress1();

    /**
     * Returns the second line of the company adress. Returns null if not
     * available.
     *
     * @since 1.3.0
     */
    String getAddress2();

    /**
     * Returns the third line of the company adress. Returns null if not
     * available.
     *
     * @since 1.3.0
     */
    String getAddress3();

    /**
     * Returns the adress post code company. Returns null if not available.
     *
     * @since 1.3.0
     */
    String getPostCode();

    /**
     * Returns the telephone number of the company. Returns null if not
     * available.
     *
     * @since 1.3.0
     */
    String getTelephoneNumber();

    /**
     * Returns the fax number of the company. Returns null if not available.
     *
     * @since 1.3.0
     */
    String getFaxNumber();

    /**
     * Returns the email of the company. Returns null if not available.
     *
     * @since 1.3.0
     */
    String getEmail();

    /**
     * Returns the full address of the company. Null if nothing.
     *
     * @since 1.3.0
     */
    String getFullAddress();


}
