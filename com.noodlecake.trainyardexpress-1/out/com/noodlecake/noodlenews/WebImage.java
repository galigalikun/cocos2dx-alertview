// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) ansi 

package com.noodlecake.noodlenews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

// Referenced classes of package com.noodlecake.noodlenews:
//            WebImageCache

public class WebImage
{

    public WebImage(String s)
    {
        url = s;
    }

    private Bitmap getBitmapFromUrl(String s)
    {
        Bitmap bitmap;
        try
        {
            URLConnection urlconnection = (new URL(s)).openConnection();
            urlconnection.setConnectTimeout(5000);
            urlconnection.setReadTimeout(10000);
            bitmap = BitmapFactory.decodeStream((InputStream)urlconnection.getContent());
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        return bitmap;
    }

    public static void removeFromCache(String s)
    {
        if(webImageCache != null)
            webImageCache.remove(s);
    }

    public Bitmap getBitmap(Context context)
    {
        if(webImageCache == null)
            webImageCache = new WebImageCache(context);
        String s = url;
        Bitmap bitmap = null;
        if(s != null)
        {
            bitmap = webImageCache.get(url);
            if(bitmap == null)
            {
                bitmap = getBitmapFromUrl(url);
                if(bitmap != null)
                    webImageCache.put(url, bitmap);
            }
        }
        return bitmap;
    }

    private static final int CONNECT_TIMEOUT = 5000;
    private static final int READ_TIMEOUT = 10000;
    private static WebImageCache webImageCache;
    private String url;
}
