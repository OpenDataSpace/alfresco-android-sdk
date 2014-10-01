package org.opendataspace.android.ui.logging;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class OdsLog
{
    private final static Logger lg = Logger.getLogger("org.opendataspace.android.app");
    private static String path;
    private static Thread.UncaughtExceptionHandler handler = null;

    public static void e(String tag, String msg)
    {
        lg.log(Level.SEVERE, "[" + tag + "] " + msg);
    }

    public static void w(String tag, String msg)
    {
        lg.log(Level.WARNING, "[" + tag + "] " + msg);
    }

    public static void i(String tag, String msg)
    {
        lg.log(Level.INFO, "[" + tag + "] " + msg);
    }

    public static void v(String tag, String msg)
    {
        lg.log(Level.FINE, "[" + tag + "] " + msg);
    }

    public static void d(String tag, String msg)
    {
        lg.log(Level.FINER, "[" + tag + "] " + msg);
    }

    public static void ex(String tag, Throwable e)
    {
        lg.log(Level.SEVERE, "[" + tag + "]", e);
    }

    public static void exw(String tag, Throwable e)
    {
        lg.log(Level.WARNING, "[" + tag + "]", e);
    }

    public static void init(String dir, boolean enabled) {
        try {
            FileHandler fh =new FileHandler(dir + "/trace-%u%g.log", 50000, 2, true);
            fh.setLevel(Level.WARNING);
            fh.setFormatter(new SimpleFormatter());
            enable(enabled);
            lg.addHandler(fh);
            path = dir;

            if (handler == null) {
                handler = Thread.getDefaultUncaughtExceptionHandler();

                Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
                {

                    @Override
                    public void uncaughtException(Thread thread, Throwable ex)
                    {
                        OdsLog.ex("UNHANDLED", ex);

                        if (handler != null)
                        {
                            handler.uncaughtException(thread, ex);
                        }
                    }
                });
            }
        } catch (Exception ex) {
            OdsLog.ex("LOGGER", ex);
        }
    }

    public static void enable(boolean val) {
        lg.setLevel(val ? Level.WARNING : Level.OFF);
    }

    public static void send(Context context, String header) {
        final Pattern p = Pattern.compile("trace-.*\\.log");
        final File[] flist = new File(path).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file)
            {
                return p.matcher(file.getName()).matches() && file.exists() && file.length() > 0;
            }
        });

        if (flist.length == 0)
        {
            return;
        }

        final ArrayList<Uri> uris = new ArrayList<Uri>();
        final Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);

        for(File f : flist)
        {
            uris.add(Uri.fromFile(f));
        }

        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { "info@opendataspace.org" });
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Logs");
        emailIntent.putParcelableArrayListExtra(android.content.Intent.EXTRA_STREAM, uris);
        context.startActivity(Intent.createChooser(emailIntent, header));
    }
}
