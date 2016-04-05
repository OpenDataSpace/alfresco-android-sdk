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
package org.alfresco.mobile.android.api.services;

import org.alfresco.mobile.android.api.constants.ContentModel;
import org.alfresco.mobile.android.api.constants.OnPremiseConstant;
import org.alfresco.mobile.android.api.model.Folder;
import org.alfresco.mobile.android.api.model.ListingContext;
import org.alfresco.mobile.android.api.model.PagingResult;
import org.alfresco.mobile.android.api.model.Person;
import org.alfresco.mobile.android.api.model.Site;

import java.util.List;

/**
 * Sites are a key concept within Alfresco Share for managing documents, wiki
 * pages, blog posts, discussions, and other collaborative content relating to
 * teams, projects, communities of interest, and other types of collaborative
 * sites. </br> There are various methods relating to the Sites service,
 * including the ability to:
 * <ul>
 * <li>List Sites (Favorites, all sites, user are member of)</li>
 * </ul>
 *
 * @author Jean Marie Pascal
 */
public interface SiteService extends Service
{

    /**
     * Allowable sorting property : Name of the document or folder.
     */
    String SORT_PROPERTY_SHORTNAME = OnPremiseConstant.SHORTNAME_VALUE;

    /**
     * Allowable sorting property : Title of the document or folder.
     */
    String SORT_PROPERTY_TITLE = ContentModel.PROP_TITLE;

    /**
     * @param siteShortName : Unique identifier name of the site.
     * @return Returns a site with the given short name, if the site doesn’t
     * exist null is returned.
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : if network or internal problems occur
     *                                                                             during the process.
     */
    Site getSite(String siteShortName);

    /**
     * @return Return a list of all the sites in the repository the current user
     * has visibility of.
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : if network or internal problems occur
     *                                                                             during the process.
     */
    List<Site> getAllSites();

    /**
     * @param listingContext : Listing context that define the behavior of
     *                       paging results
     *                       {@link org.alfresco.mobile.android.api.model.ListingContext
     *                       ListingContext}
     * @return Return a paged list of all the sites in the repository the
     * current user has visibility of.
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : if network or internal problems occur
     *                                                                             during the process.
     */
    PagingResult<Site> getAllSites(ListingContext listingContext);

    /**
     * @return Returns a list of sites the current user has a explicit
     * membership to.
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : if network or internal problems occur
     *                                                                             during the process.
     */
    List<Site> getSites();

    /**
     * @param listingContext : Listing context that define the behaviour of
     *                       paging results
     *                       {@link org.alfresco.mobile.android.api.model.ListingContext
     *                       ListingContext}
     * @return Returns a paged list of sites the current user has a explicit
     * membership to.
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : if network or internal problems occur
     *                                                                             during the process.
     */
    PagingResult<Site> getSites(ListingContext listingContext);

    /**
     * @return Returns a list of sites the current user has a explicit
     * membership to and has marked as a favourite.
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : if network or internal problems occur
     *                                                                             during the process.
     */
    List<Site> getFavoriteSites();

    /**
     * @param listingContext : Listing context that define the behaviour of
     *                       paging results
     *                       {@link org.alfresco.mobile.android.api.model.ListingContext
     *                       ListingContext}
     * @return Returns a paged list of sites the current user has a explicit
     * membership to and has marked as a favourite.
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : if network or internal problems occur
     *                                                                             during the process.
     */
    PagingResult<Site> getFavoriteSites(ListingContext listingContext);

    /**
     * Get the documents container folder for the given site.
     *
     * @param site : Unique identifier name of the site.
     * @return Returns the root folder container to share document library.
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : if network or internal problems occur
     *                                                                             during the process.
     */
    Folder getDocumentLibrary(Site site);

    /**
     * Adds the given site to the current users list of favorite sites. <br/>
     * It's possible to favorite a site independently of its visibility.
     *
     * @param site : site object
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : if the request can not be completed
     *                                                                             successfully an exception is thrown with error code
     * @since 1.1.0
     */
    Site addFavoriteSite(Site site);

    /**
     * Removes the given site from the current users list of favorite sites. <br/>
     * It's possible to favorite a site independently of its visibility.
     *
     * @param site : site object
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : if the request can not be completed
     *                                                                             successfully an exception is thrown with error code
     *                                                                             .
     * @since 1.1.0
     */
    Site removeFavoriteSite(Site site);

    /**
     * Adds the current user as a member of the given site with an optional
     * message explaining why they wish to join the site.
     *
     * @param site : site object
     * @return If the site is moderated, a JoinSiteRequest object is returned. <br/>
     * If the site is public null is returned.
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : <br/>
     *                                                                             If the current user is already a member of the site or there
     *                                                                             is a pending join request for the user an exception is thrown
     *                                                                             with error code
     *                                                                             {@link org.alfresco.mobile.android.api.exceptions.ErrorCodeRegistry#SITE_ALREADY_MEMBER
     *                                                                             SITE_ALREADY_MEMBER}. <br/>
     *                                                                             If the request fails for any other reason an exception is
     *                                                                             thrown with error code
     *                                                                             .
     * @since 1.1.0
     */
    Site joinSite(Site site);

    /**
     * Returns a list of join site requests from the current user that have yet
     * to be actioned. An empty list is returned if there are no outstanding
     * requests.
     *
     * @return : List of site the user wants to join. Empty list if there's no
     * request.
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException
     * @since 1.1.0
     */
    List<Site> getPendingSites();

    /**
     * Returns a list of join site requests from the current user that have yet
     * to be actioned. An empty list is returned if there are no outstanding
     * requests.
     *
     * @return Returns a paged list of sites the current user has requested to
     * be a member.
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException
     * @since 1.2.0
     */
    PagingResult<Site> getPendingSites(ListingContext listingContext);

    /**
     * Cancels a previous request to join a site made by the current user.
     *
     * @param site : site object
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : If the request can not be completed
     *                                                                             successfully an exception is thrown with error code
     *                                                                             .
     * @since 1.1.0
     */
    Site cancelRequestToJoinSite(Site site);

    /**
     * Removes the current user from the given site.
     *
     * @param site : site object
     * @throws org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException : If the request can not be completed
     *                                                                             successfully an exception is thrown with error code
     *                                                                             or
     *                                                                             {@link org.alfresco.mobile.android.api.exceptions.ErrorCodeRegistry#SITE_LAST_MANAGER
     *                                                                             SITE_LAST_MANAGER} if the user is the last manager of the
     *                                                                             site.
     * @since 1.1.0
     */
    Site leaveSite(Site site);

    /**
     * Returns a list of site members.
     *
     * @since 1.3.0
     */
    List<Person> getAllMembers(Site site);

    /**
     * Returns a paged list of site members.
     *
     * @since 1.3.0
     */
    PagingResult<Person> getAllMembers(Site site, ListingContext listingContext);

    /**
     * Returns true if the person is a member of the specified site.
     *
     * @since 1.3.0
     */
    boolean isMember(Site site, Person person);

    /**
     * @since 1.3.0
     */
    List<Person> searchMembers(Site site, String keywords);

    /**
     * @since 1.3.0
     */
    PagingResult<Person> searchMembers(Site site, String keywords, ListingContext listingContext);
}
