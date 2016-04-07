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
package org.alfresco.mobile.android.ui.documentfolder.actions;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;

import org.alfresco.mobile.android.api.asynchronous.LoaderResult;
import org.alfresco.mobile.android.api.asynchronous.NodeUpdateLoader;
import org.alfresco.mobile.android.api.model.Document;
import org.alfresco.mobile.android.api.model.Node;
import org.alfresco.mobile.android.api.model.impl.ContentFileImpl;
import org.alfresco.mobile.android.api.session.AlfrescoSession;
import org.alfresco.mobile.android.ui.documentfolder.listener.OnNodeUpdateListener;
import org.alfresco.mobile.android.ui.fragments.BaseLoaderCallback;

import java.io.File;
import java.io.Serializable;
import java.util.Map;

public class UpdateLoaderCallback extends BaseLoaderCallback implements LoaderCallbacks<LoaderResult<Node>>
{
    private File file;

    private final Node node;

    private Map<String, Serializable> properties;

    private OnNodeUpdateListener mListener;

    public UpdateLoaderCallback(AlfrescoSession session, Activity context, Document content, File file)
    {
        super();
        this.session = session;
        this.context = context;
        this.node = content;
        this.file = file;
    }

    public UpdateLoaderCallback(AlfrescoSession session, Activity context, Node node,
                                Map<String, Serializable> properties)
    {
        super();
        this.session = session;
        this.context = context;
        this.node = node;
        this.properties = properties;
    }

    @Override
    public Loader<LoaderResult<Node>> onCreateLoader(int id, Bundle args)
    {
        if (mListener != null)
        {
            mListener.beforeUpdate(node);
        }
        if (properties != null)
        {
            return new NodeUpdateLoader(context, session, node, properties);
        }
        else
        {
            return new NodeUpdateLoader(context, session, (Document) node, null, new ContentFileImpl(file));
        }
    }

    @Override
    public void onLoadFinished(Loader<LoaderResult<Node>> arg0, LoaderResult<Node> results)
    {
        if (mListener != null)
        {
            if (results.hasException())
            {
                mListener.onExeceptionDuringUpdate(results.getException());
            }
            else
            {
                mListener.afterUpdate(results.getData());
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<LoaderResult<Node>> arg0)
    {
        // Nothing special
    }

    public void setOnUpdateListener(OnNodeUpdateListener mListener)
    {
        this.mListener = mListener;
    }

    public void start()
    {
        if (getLoaderManager().getLoader(NodeUpdateLoader.ID) == null)
        {
            getLoaderManager().initLoader(NodeUpdateLoader.ID, null, this);
        }
        getLoaderManager().restartLoader(NodeUpdateLoader.ID, null, this);
        getLoaderManager().getLoader(NodeUpdateLoader.ID).forceLoad();
    }

}
