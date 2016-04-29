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
package org.alfresco.mobile.android.api.asynchronous;

import android.annotation.TargetApi;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Build;

import org.alfresco.mobile.android.api.session.AlfrescoSession;

/**
 * Abstract Base Loader that request Alfresco Mobile SDK Services.
 *
 * @author Jean Marie Pascal
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class AbstractBaseLoader<T> extends AsyncTaskLoader<T>
{
    private T data;

    protected AlfrescoSession session;

    /**
     * Default constructor.
     */
    public AbstractBaseLoader(Context context)
    {
        super(context);
    }

    @Override
    protected void onStartLoading()
    {
        if (data != null)
        {
            deliverResult(data);
        }

        if (takeContentChanged() || data == null)
        {
            forceLoad();
        }
    }

    @Override
    public void deliverResult(T data)
    {
        if (isReset())
        {
            return;
        }
        this.data = data;
        super.deliverResult(data);
    }

    @Override
    protected void onStopLoading()
    {
        cancelLoad();
    }

    @Override
    protected void onReset()
    {
        super.onReset();

        onStopLoading();

        data = null;
    }
}
