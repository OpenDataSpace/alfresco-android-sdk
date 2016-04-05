/*******************************************************************************
 * Copyright (C) 2005-2012 Alfresco Software Limited.
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
package org.alfresco.mobile.android.api.services;

import org.alfresco.mobile.android.api.model.ListingContext;
import org.alfresco.mobile.android.api.model.Node;
import org.alfresco.mobile.android.api.model.PagingResult;
import org.alfresco.mobile.android.api.model.Tag;

import java.util.List;

/**
 * Tags are keywords or terms assigned to a piece of information including
 * documents, folders... </br> There are various methods and properties relating
 * to the Tagging service, including the ability to:
 * <ul>
 * <li>Add tags</li>
 * <li>Remove tags</li>
 * <li>list (and filter) tags</li>
 * </ul>
 *
 * @author Jean Marie Pascal
 */
public interface TaggingService extends Service
{
    /**
     * @return Returns a list of all tags currently available in the repository.
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : if network or internal problems occur
     *                                                                             during the process.
     */
    List<Tag> getAllTags();

    /**
     * @param listingContext : Listing context that define the behaviour of
     *                       paging results
     *                       {@link org.alfresco.mobile.android.api.model.ListingContext
     *                       ListingContext}
     * @return Returns a paged list of all tags currently available in the
     * repository.
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : if network or internal problems occur
     *                                                                             during the process.
     */
    PagingResult<Tag> getAllTags(ListingContext listingContext);

    /**
     * @param node : tagged node (document or folder)
     * @return Returns a list of all tags stored on the given node.
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : if network or internal problems occur
     *                                                                             during the process.
     */
    List<Tag> getTags(Node node);

    /**
     * @param node           : tagged node (document or folder)
     * @param listingContext : Listing context that define the behaviour of
     *                       paging results
     *                       {@link org.alfresco.mobile.android.api.model.ListingContext
     *                       ListingContext}
     * @return Returns a paged list of all tags stored on the given node.
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : if network or internal problems occur
     *                                                                             during the process.
     */
    PagingResult<Tag> getTags(Node node, ListingContext listingContext);

    /**
     * Adds a list of tags to a node.
     *
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : if network or internal problems occur
     *                                                                             during the process.
     */
    void addTags(Node node, List<String> tags);

}
