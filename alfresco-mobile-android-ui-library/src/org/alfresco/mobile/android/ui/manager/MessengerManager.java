/*******************************************************************************
 * Copyright (C) 2005-2012 Alfresco Software Limited.
 * <p>
 * This file is part of the Alfresco Mobile SDK.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.alfresco.mobile.android.ui.manager;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Manager responsible of all dialog, notification and warning
 *
 * @author jpascal
 */
public final class MessengerManager
{

    private MessengerManager()
    {
    }

    public static void showToast(Context context, String text)
    {
        if (TextUtils.isEmpty(text))
        {
            return;
        }

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public static void showToast(Context context, int text)
    {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public static void showLongToast(Context context, String text)
    {
        if (TextUtils.isEmpty(text))
        {
            return;
        }

        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
