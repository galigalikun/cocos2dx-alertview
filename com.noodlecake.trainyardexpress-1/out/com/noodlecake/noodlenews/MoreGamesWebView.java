// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) ansi 

package com.noodlecake.noodlenews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.*;
import android.widget.*;
import org.cocos2dx.lib.Cocos2dxActivity;

public class MoreGamesWebView extends Activity
{
    private class MoreGamesWebViewClient extends WebViewClient
    {

        public void onPageFinished(WebView webview, String s)
        {
            progressBar.setVisibility(8);
            super.onPageFinished(webview, s);
        }

        public void onPageStarted(WebView webview, String s, Bitmap bitmap)
        {
            progressBar.setVisibility(0);
            progressBar.bringToFront();
            super.onPageStarted(webview, s, bitmap);
        }

        public void onReceivedError(WebView webview, int i, String s, String s1)
        {
            Toast.makeText(MoreGamesWebView.this, "Other games cannot currently be viewed.  Please try again later.", 4000);
            super.onReceivedError(webview, i, s, s1);
        }

        public boolean shouldOverrideUrlLoading(WebView webview, String s)
        {
            boolean flag = false;
            if(s == null) goto _L2; else goto _L1
_L1:
            if(s.startsWith("market://")) goto _L4; else goto _L3
_L3:
            boolean flag1;
            flag1 = s.startsWith("amzn://");
            flag = false;
            if(!flag1) goto _L2; else goto _L4
_L4:
            webview.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
_L6:
            flag = true;
_L2:
            return flag;
            Exception exception;
            exception;
            if(s.startsWith("market://"))
            {
                webview.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/developer?id=Noodlecake+Studios+Inc")));
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                webview.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.noodlecake.com/games/")));
            }
            catch(Exception exception1)
            {
                return false;
            }
            if(true) goto _L6; else goto _L5
_L5:
        }

        final MoreGamesWebView this$0;

        private MoreGamesWebViewClient()
        {
            this$0 = MoreGamesWebView.this;
            super();
        }

        MoreGamesWebViewClient(MoreGamesWebViewClient moregameswebviewclient)
        {
            this();
        }
    }


    public MoreGamesWebView()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        RelativeLayout relativelayout = new RelativeLayout(this);
        String s = "android";
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null && bundle1.containsKey("com.noodlecake.noodlenews.MoreGamesWebView.key_platform") && bundle1.getInt("com.noodlecake.noodlenews.MoreGamesWebView.key_platform") == 1)
            s = "amazon";
        webView = new WebView(this);
        webView.setWebViewClient(new MoreGamesWebViewClient(null));
        webView.getSettings().setJavaScriptEnabled(true);
        progressBar = new ProgressBar(this, null);
        progressBar.setVisibility(0);
        android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-2, -2);
        layoutparams.addRule(13);
        progressBar.setLayoutParams(layoutparams);
        relativelayout.addView(webView, -1, -1);
        relativelayout.addView(progressBar);
        setContentView(relativelayout);
        webView.loadUrl((new StringBuilder("http://news.noodlecake.net/more_games/?bundle_id=")).append(Cocos2dxActivity.getCocos2dxPackageName()).append("&platform=").append(s).append("&amzn=1").toString());
    }

    public void onDestroy()
    {
        super.onDestroy();
        if(webView != null)
        {
            webView.clearCache(true);
            webView.destroyDrawingCache();
            webView.destroy();
        }
    }

    public static final String MORE_GAMES_URI = "http://news.noodlecake.net/more_games/";
    public static final String PLATFORM_KEY = "com.noodlecake.noodlenews.MoreGamesWebView.key_platform";
    private ProgressBar progressBar;
    private WebView webView;

}
