package org.opendataspace.android.ui.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

import android.util.Log;

public class OdsLog
{
    private final static Logger lg = Logger.getLogger("org.opendataspace.android.app");

    public static void e(String tag, String msg)
    {
        lg.log(Level.SEVERE, "[" + tag + "] " + msg);
        Log.e(tag, msg);
    }

    public static void w(String tag, String msg)
    {
        lg.log(Level.WARNING, "[" + tag + "] " + msg);
        Log.w(tag, msg);
    }

    public static void i(String tag, String msg)
    {
        lg.log(Level.INFO, "[" + tag + "] " + msg);
        Log.i(tag, msg);
    }

    public static void v(String tag, String msg)
    {
        lg.log(Level.FINE, "[" + tag + "] " + msg);
        Log.v(tag, msg);
    }

    public static void d(String tag, String msg)
    {
        lg.log(Level.FINER, "[" + tag + "] " + msg);
        Log.d(tag, msg);
    }

    public static void ex(String tag, Throwable e)
    {
        lg.log(Level.SEVERE, "[" + tag + "]", e);
        Log.e(tag, Log.getStackTraceString(e));
    }

    public static void exw(String tag, Throwable e)
    {
        lg.log(Level.WARNING, "[" + tag + "]", e);
        Log.w(tag, Log.getStackTraceString(e));
    }
}
