// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) ansi 

package com.noodlecake.noodlenews;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.noodlecake.ads.InterstitialController;
import com.noodlecake.lib.UIKit;
import gnustep.foundation.Common;
import java.lang.reflect.Method;
import org.cocos2dx.lib.*;

public class NoodlecakeGameActivity extends Cocos2dxActivity
{

    public NoodlecakeGameActivity(boolean flag, boolean flag1, boolean flag2)
    {
        platform = 0;
        version = 30;
        hasPopups = flag;
        hasPurchases = flag1;
        hasNews = flag2;
        hasDepthBuffer = false;
        platform = getPlatform();
        version = getVersion();
        context = this;
    }

    public NoodlecakeGameActivity(boolean flag, boolean flag1, boolean flag2, boolean flag3)
    {
        platform = 0;
        version = 30;
        hasPopups = flag;
        hasPurchases = flag1;
        hasNews = flag2;
        hasDepthBuffer = flag3;
        platform = getPlatform();
        version = getVersion();
        context = this;
    }

    public static Context getContext()
    {
        return context;
    }

    public static Handler getHandler()
    {
        return handler;
    }

    public static native int getPlatform();

    public static native int getVersion();

    protected void onCreate(Bundle bundle)
    {
        Log.e("NoodlecakeGameAtivity", "NoodlecakeGameActivity should be called with overloaded onCreate method");
        if(!$assertionsDisabled)
            throw new AssertionError();
        else
            return;
    }

    protected void onCreate(Bundle bundle, int i, int j, int k)
    {
        super.onCreate(bundle);
        setContentView(i);
        mGLView = (Cocos2dxGLSurfaceView)findViewById(j);
        mGLView.setTextField((EditText)findViewById(k));
        mGLView.setEGLConfigChooser(hasDepthBuffer);
        mGLView.setCocos2dxRenderer(new Cocos2dxRenderer());
        setPackageName(getApplication().getPackageName());
        Common.setup(this);
        UIKit.init(this);
        if(!hasPurchases) goto _L2; else goto _L1
_L1:
        if(platform != 1) goto _L4; else goto _L3
_L3:
        try
        {
            Class.forName("com.noodlecake.iap.AmazonPurchaseWrapper").getMethod("init", new Class[] {
                android/content/Context
            }).invoke(null, new Object[] {
                this
            });
        }
        catch(Exception exception2)
        {
            Log.i("NoodlecakeGameAtivity", (new StringBuilder("Creating Amazon purchase wrapper ")).append(exception2).toString());
            hasPurchases = false;
        }
_L2:
        if(!hasNews)
            break MISSING_BLOCK_LABEL_168;
        Class.forName("com.noodlecake.noodlenews.NoodleNews").getMethod("init", new Class[] {
            android/app/Activity
        }).invoke(null, new Object[] {
            this
        });
        return;
_L4:
        if(platform == 0)
            try
            {
                Class.forName("com.noodlecake.iap.PurchaseWrapper").getMethod("init", new Class[] {
                    android/app/Activity
                }).invoke(null, new Object[] {
                    this
                });
            }
            catch(Exception exception1)
            {
                Log.i("NoodlecakeGameAtivity", (new StringBuilder("Creating purchase wrapper ")).append(exception1).toString());
                hasPurchases = false;
            }
        if(true) goto _L2; else goto _L5
_L5:
        Exception exception;
        exception;
        Log.e("NoodlecakeGameAtivity", (new StringBuilder()).append(exception).toString());
        hasNews = false;
        return;
    }

    protected void onCreate(Bundle bundle, int i, int j, int k, int l)
    {
        onCreate(bundle, i, j, k);
        layout_main = (FrameLayout)game.findViewById(l);
    }

    protected void onPause()
    {
        super.onPause();
        mGLView.onPause();
        if(!hasPopups)
            break MISSING_BLOCK_LABEL_35;
        Class.forName("com.noodlecake.noodlenews.Popup").getMethod("appPausing", null).invoke(null, null);
        return;
        Exception exception;
        exception;
        Log.e("NoodlecakeGameAtivity", (new StringBuilder()).append(exception).toString());
        return;
    }

    protected void onResume()
    {
        super.onResume();
        mGLView.onResume();
        if(hasPopups)
            try
            {
                Method method = Class.forName("com.noodlecake.noodlenews.Popup").getMethod("appResuming", new Class[] {
                    android/os/Bundle
                });
                Object aobj[] = new Object[1];
                aobj[0] = getIntent().getExtras();
                method.invoke(null, aobj);
            }
            catch(Exception exception1)
            {
                Log.e("NoodlecakeGameAtivity", (new StringBuilder()).append(exception1).toString());
            }
        if(hasPurchases && platform == 1)
            try
            {
                Class.forName("com.noodlecake.iap.AmazonPurchaseWrapper").getMethod("onResume", new Class[0]).invoke(null, new Object[0]);
            }
            catch(Exception exception)
            {
                Log.e("NoodlecakeGameAtivity", (new StringBuilder("Resuming aamazon purachase wrapper ")).append(exception).toString());
            }
        InterstitialController.onResume();
    }

    protected void onStart()
    {
        super.onStart();
        if(!hasPopups)
            break MISSING_BLOCK_LABEL_62;
        Method method = Class.forName("com.noodlecake.noodlenews.Popup").getMethod("appCreate", new Class[] {
            android/content/Context, android/os/Handler
        });
        Object aobj[] = new Object[2];
        aobj[0] = this;
        aobj[1] = handler;
        method.invoke(null, aobj);
        return;
        Exception exception;
        exception;
        Log.e("NoodlecakeGameAtivity", (new StringBuilder()).append(exception).toString());
        hasPopups = false;
        return;
    }

    static final boolean $assertionsDisabled = false;
    private static final String TAG = "NoodlecakeGameAtivity";
    protected static String admob_id = null;
    private static Context context = null;
    protected static NoodlecakeGameActivity game = null;
    private static Handler handler = new Handler();
    protected static FrameLayout layout_main = null;
    private boolean hasDepthBuffer;
    private boolean hasNews;
    private boolean hasPopups;
    private boolean hasPurchases;
    protected Cocos2dxGLSurfaceView mGLView;
    protected int platform;
    protected int version;

    static 
    {
        boolean flag;
        if(!com/noodlecake/noodlenews/NoodlecakeGameActivity.desiredAssertionStatus())
            flag = true;
        else
            flag = false;
        $assertionsDisabled = flag;
    }
}
