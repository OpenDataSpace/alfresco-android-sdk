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
package org.alfresco.mobile.android.ui.manager;

import android.content.Context;
import android.os.Environment;

import org.alfresco.mobile.android.api.exceptions.AlfrescoServiceException;
import org.alfresco.mobile.android.api.exceptions.ErrorCodeRegistry;
import org.opendataspace.android.ui.logging.OdsLog;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StorageManager
{

    private static final String TAG = "StorageManager";

    /**
     * Return the cache dir and check if exists
     *
     * @throws AlfrescoServiceException
     */
    public static File getCacheDir(Context context, String extendedPath)
    {
        File folder;
        try
        {
            folder = createFolder(context.getCacheDir(), extendedPath);
        }
        catch (Exception e)
        {
            throw new AlfrescoServiceException(ErrorCodeRegistry.GENERAL_IO, e);
        }

        return folder;
    }

    protected static File createFolder(File f, String extendedPath)
    {
        File tmpFolder;
        tmpFolder = new File(f, extendedPath);
        if (!tmpFolder.exists())
        {
            //noinspection ResultOfMethodCallIgnored
            tmpFolder.mkdirs();
            try
            {
                //noinspection ResultOfMethodCallIgnored
                new File(tmpFolder, ".nomedia").createNewFile();
            }
            catch (IOException e)
            {
                throw new AlfrescoServiceException(ErrorCodeRegistry.GENERAL_IO, e);
            }
        }

        return tmpFolder;
    }

    /**
     * Create the MD5 representation of a string
     */
    public static String md5(String s)
    {
        try
        {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest)
            {
                hexString.append(Integer.toHexString(0xFF & aMessageDigest));
            }
            return hexString.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            OdsLog.ex(TAG, e);
        }
        return "";
    }

    /**
     * Get specific access to DownloadFolder
     *
     * @throws AlfrescoServiceException
     */
    public static File getDownloadFolder(Context context, String urlValue, String username)
    {
        File folder = null;
        try
        {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
            {
                folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            }
            else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState()))
            {
                folder = Environment.getDownloadCacheDirectory();
            }

            folder = createFolder(folder, context.getResources().getText(
                    context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes)
                    .toString());
            folder = createFolder(folder, getDownloadAccountFolder(urlValue, username));
        }
        catch (Exception e)
        {
            throw new AlfrescoServiceException(ErrorCodeRegistry.GENERAL_IO, e);
        }

        return folder;
    }

    /**
     * Return null if already exists
     *
     * @throws AlfrescoServiceException
     */
    public static File getDownloadFile(Context context, String urlValue, String username, String fileName)
    {
        File f = new File(getDownloadFolder(context, urlValue, username), fileName);
        if (!f.exists())
        {
            return f;
        }
        else
        {
            return null;
        }// trhow excception
    }

    private static String getDownloadAccountFolder(String urlValue, String username)
    {
        String name = null;
        try
        {
            URL url = new URL(urlValue);
            name = url.getHost();
        }
        catch (MalformedURLException e)
        {
            OdsLog.ex(TAG, e);
        }
        return name + "-" + username;
    }

}
