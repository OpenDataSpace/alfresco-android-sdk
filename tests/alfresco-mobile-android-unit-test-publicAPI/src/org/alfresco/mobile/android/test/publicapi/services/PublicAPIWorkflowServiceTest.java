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
package org.alfresco.mobile.android.test.publicapi.services;

import junit.framework.Assert;
import org.alfresco.mobile.android.test.api.services.WorkflowServiceTest;


public class PublicAPIWorkflowServiceTest extends WorkflowServiceTest
{
    /** {@inheritDoc} */
    protected void initSession()
    {
        if (alfsession == null)
        {
            alfsession = createRepositorySession();
            alfsession = createSession(WORKFLOW, WORKFLOW_PASSWORD, null);
        }

        // Retrieve Service
        workflowService = alfsession.getServiceRegistry().getWorkflowService();

        // Check Services
        Assert.assertNotNull(alfsession.getServiceRegistry());
        Assert.assertNotNull(workflowService);
    }

    public void testProcessDefinition()
    {
        super.testProcessDefinition();
    }

    public void testAdhocWorkflow()
    {
        super.testAdhocWorkflow();
    }

    @Override
    public void testFilters()
    {
        super.testFilters();
    }

    @Override
    public void testParallelWorkflow()
    {
        super.testParallelWorkflow();
    }

    @Override
    public void testPooledWorkflow()
    {
        super.testPooledWorkflow();
    }

    @Override
    public void testReviewWorkflow()
    {
        super.testReviewWorkflow();
    }

    @Override
    public void testFaillureWorkflowService()
    {
        super.testFaillureWorkflowService();
    }
}
