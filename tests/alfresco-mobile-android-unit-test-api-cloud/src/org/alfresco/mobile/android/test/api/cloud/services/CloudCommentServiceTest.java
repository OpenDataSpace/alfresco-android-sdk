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
package org.alfresco.mobile.android.test.api.cloud.services;

import junit.framework.Assert;
import org.alfresco.mobile.android.test.api.services.CommentServiceTest;

/**
 * Test class for Comment Service.
 *
 * @author Jean Marie Pascal
 */
public class CloudCommentServiceTest extends CommentServiceTest
{

    protected void initSession()
    {
        if (alfsession == null)
        {
            alfsession = createCloudSession();
        }
        // Check Services
        Assert.assertNotNull(alfsession.getServiceRegistry());
        commentService = alfsession.getServiceRegistry().getCommentService();
        docfolderservice = alfsession.getServiceRegistry().getDocumentFolderService();
        Assert.assertNotNull(commentService);
    }
}
