// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) ansi 

package com.noodlecake.noodlenews;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import org.cocos2dx.lib.Cocos2dxActivity;

// Referenced classes of package com.noodlecake.noodlenews:
//            MoreGamesWebView

public class NoodleNews
{

    public NoodleNews()
    {
    }

    public static String getAppUrl(int i)
    {
        return getAppUrlForPackage(Cocos2dxActivity.getCocos2dxPackageName(), i);
    }

    public static String getAppUrlForPackage(String s, int i)
    {
        if(i == 0)
            return (new StringBuilder(String.valueOf("http://"))).append("play.google.com/store/apps/details?id=").append(s).toString();
        if(i == 1)
            return (new StringBuilder(String.valueOf("http://"))).append("www.amazon.com/gp/mas/dl/android?p=").append(s).toString();
        else
            return (new StringBuilder(String.valueOf("http://"))).append("www.facebook.com/noodlecakestudios").toString();
    }

    public static String getMarketUrlForPackage(String s, int i)
    {
        if(i == 0)
            return (new StringBuilder(String.valueOf("market://"))).append("details?id=").append(s).toString();
        if(i == 1)
            return (new StringBuilder("http://www.amazon.com/gp/mas/dl/android?p=")).append(s).toString();
        else
            return "http://www.facebook.com/noodlecakestudios";
    }

    public static void gotoReviewScreen(final int platform)
    {
        Handler handler = Cocos2dxActivity.getHandler();
        if(handler != null)
            handler.post(new Runnable() {

                public void run()
                {
                    String s = NoodleNews.getAppUrl(platform);
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(s));
                    NoodleNews._activity.startActivity(intent);
                }

                private final int val$platform;

            
            {
                platform = i;
                super();
            }
            }
);
    }

    public static void init(Activity activity)
    {
        _activity = activity;
    }

    public static void openURL(final String url)
    {
        Log.v("NoodleNews", (new StringBuilder("openURL: ")).append(url).toString());
        Handler handler = Cocos2dxActivity.getHandler();
        if(handler != null)
            handler.post(new Runnable() {

                public void run()
                {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(url));
                    NoodleNews._activity.startActivity(intent);
                }

                private final String val$url;

            
            {
                url = s;
                super();
            }
            }
);
    }

    public static void showMoreGames(final int platform)
    {
        Handler handler = Cocos2dxActivity.getHandler();
        if(handler != null)
            handler.post(new Runnable() {

                public void run()
                {
                    Intent intent = new Intent(NoodleNews._activity, com/noodlecake/noodlenews/MoreGamesWebView);
                    intent.putExtra("com.noodlecake.noodlenews.MoreGamesWebView.key_platform", platform);
                    NoodleNews._activity.startActivity(intent);
                }

                private final int val$platform;

            
            {
                platform = i;
                super();
            }
            }
);
    }

    public static final int DISTRO_AMAZON = 1;
    public static final int DISTRO_GOOGLE_PLAY = 0;
    public static final int DISTRO_UNKNOWN = 2;
    public static final int VERSION_FREE = 40;
    public static final int VERSION_FULL = 30;
    static Activity _activity = null;

}
