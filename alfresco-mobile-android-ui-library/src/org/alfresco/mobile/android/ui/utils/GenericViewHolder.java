/*******************************************************************************
 * Copyright (C) 2005-2012 Alfresco Software Limited.
 *
 * This file is part of the Alfresco Mobile SDK.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/
package org.alfresco.mobile.android.ui.utils;

import org.alfresco.mobile.android.ui.R;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Default class for listing item viewholder.
 *
 * @author Jean Marie Pascal
 */
public class GenericViewHolder extends ViewHolder
{

    public final TextView topText;

    public final TextView bottomText;

    public final ImageView icon;

    public final ImageView choose;

    public GenericViewHolder(View v)
    {
        super(v);
        icon = (ImageView) v.findViewById(R.id.icon);
        topText = (TextView) v.findViewById(R.id.toptext);
        bottomText = (TextView) v.findViewById(R.id.bottomtext);
        choose = (ImageView) v.findViewById(R.id.choose);
    }
}
