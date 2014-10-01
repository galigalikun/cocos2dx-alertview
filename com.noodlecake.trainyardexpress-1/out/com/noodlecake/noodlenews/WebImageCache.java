// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) ansi 

package com.noodlecake.noodlenews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.*;
import java.lang.ref.SoftReference;
import java.util.concurrent.*;

public class WebImageCache
{

    public WebImageCache(Context context)
    {
        diskCacheEnabled = false;
        memoryCache = new ConcurrentHashMap();
        diskCachePath = (new StringBuilder(String.valueOf(context.getApplicationContext().getCacheDir().getAbsolutePath()))).append("/web_image_cache/").toString();
        File file = new File(diskCachePath);
        file.mkdirs();
        diskCacheEnabled = file.exists();
        writeThread = Executors.newSingleThreadExecutor();
    }

    private void cacheBitmapToDisk(final String url, final Bitmap bitmap)
    {
        writeThread.execute(new Runnable() {

            public void run()
            {
                BufferedOutputStream bufferedoutputstream;
                if(!diskCacheEnabled)
                    break MISSING_BLOCK_LABEL_81;
                bufferedoutputstream = null;
                BufferedOutputStream bufferedoutputstream1 = new BufferedOutputStream(new FileOutputStream(new File(diskCachePath, getCacheKey(url))), 2048);
                bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, bufferedoutputstream1);
                if(bufferedoutputstream1 == null)
                    break MISSING_BLOCK_LABEL_81;
                bufferedoutputstream1.flush();
                bufferedoutputstream1.close();
_L2:
                return;
                FileNotFoundException filenotfoundexception;
                filenotfoundexception;
_L5:
                filenotfoundexception.printStackTrace();
                if(bufferedoutputstream == null) goto _L2; else goto _L1
_L1:
                try
                {
                    bufferedoutputstream.flush();
                    bufferedoutputstream.close();
                    return;
                }
                catch(IOException ioexception1)
                {
                    return;
                }
                Exception exception;
                exception;
_L4:
                IOException ioexception2;
                if(bufferedoutputstream != null)
                    try
                    {
                        bufferedoutputstream.flush();
                        bufferedoutputstream.close();
                    }
                    catch(IOException ioexception) { }
                throw exception;
                ioexception2;
                return;
                exception;
                bufferedoutputstream = bufferedoutputstream1;
                if(true) goto _L4; else goto _L3
_L3:
                filenotfoundexception;
                bufferedoutputstream = bufferedoutputstream1;
                  goto _L5
            }

            final WebImageCache this$0;
            private final Bitmap val$bitmap;
            private final String val$url;

            
            {
                this$0 = WebImageCache.this;
                url = s;
                bitmap = bitmap1;
                super();
            }
        }
);
    }

    private void cacheBitmapToMemory(String s, Bitmap bitmap)
    {
        memoryCache.put(getCacheKey(s), new SoftReference(bitmap));
    }

    private Bitmap getBitmapFromDisk(String s)
    {
        boolean flag = diskCacheEnabled;
        Bitmap bitmap = null;
        if(flag)
        {
            String s1 = getFilePath(s);
            boolean flag1 = (new File(s1)).exists();
            bitmap = null;
            if(flag1)
                bitmap = BitmapFactory.decodeFile(s1);
        }
        return bitmap;
    }

    private Bitmap getBitmapFromMemory(String s)
    {
        SoftReference softreference = (SoftReference)memoryCache.get(getCacheKey(s));
        Bitmap bitmap = null;
        if(softreference != null)
            bitmap = (Bitmap)softreference.get();
        return bitmap;
    }

    private String getCacheKey(String s)
    {
        if(s == null)
            throw new RuntimeException("Null url passed in");
        else
            return s.replaceAll("[.:/,%?&=]", "+").replaceAll("[+]+", "+");
    }

    private String getFilePath(String s)
    {
        return (new StringBuilder(String.valueOf(diskCachePath))).append(getCacheKey(s)).toString();
    }

    public void clear()
    {
        File file;
        memoryCache.clear();
        file = new File(diskCachePath);
        if(!file.exists() || !file.isDirectory()) goto _L2; else goto _L1
_L1:
        File afile[];
        int i;
        int j;
        afile = file.listFiles();
        i = afile.length;
        j = 0;
_L5:
        if(j < i) goto _L3; else goto _L2
_L2:
        return;
_L3:
        File file1 = afile[j];
        if(file1.exists() && file1.isFile())
            file1.delete();
        j++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public Bitmap get(String s)
    {
        Bitmap bitmap = getBitmapFromMemory(s);
        if(bitmap == null)
        {
            bitmap = getBitmapFromDisk(s);
            if(bitmap != null)
                cacheBitmapToMemory(s, bitmap);
        }
        return bitmap;
    }

    public void put(String s, Bitmap bitmap)
    {
        cacheBitmapToMemory(s, bitmap);
        cacheBitmapToDisk(s, bitmap);
    }

    public void remove(String s)
    {
        if(s != null)
        {
            memoryCache.remove(getCacheKey(s));
            File file = new File(diskCachePath, s);
            if(file.exists() && file.isFile())
            {
                file.delete();
                return;
            }
        }
    }

    private static final String DISK_CACHE_PATH = "/web_image_cache/";
    private boolean diskCacheEnabled;
    private String diskCachePath;
    private ConcurrentHashMap memoryCache;
    private ExecutorService writeThread;



}
