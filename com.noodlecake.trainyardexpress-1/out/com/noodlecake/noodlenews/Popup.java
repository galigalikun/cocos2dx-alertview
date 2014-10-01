// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) ansi 

package com.noodlecake.noodlenews;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.*;

// Referenced classes of package com.noodlecake.noodlenews:
//            PopupTransaction, PopupTransactionDB, EmailPopup, OpenUrlPopup, 
//            AlertPopup

public class Popup
{

    public Popup()
    {
    }

    public static void addCurrency(int i)
    {
        PopupTransaction popuptransaction = (PopupTransaction)listedTransactions.get(Integer.valueOf(i));
        if(popuptransaction.getParameter3() != null && Integer.parseInt(popuptransaction.getParameter3()) > 0)
            pendingCurrency += Integer.parseInt(popuptransaction.getParameter3());
    }

    public static void appCreate(Context context, Handler handler)
    {
        appContext = context;
        appHandler = handler;
        if(transactionDB != null)
            transactionDB.close();
        transactionDB = new PopupTransactionDB(appContext);
    }

    public static void appPausing()
    {
        isPaused = true;
    }

    public static void appResuming(Bundle bundle)
    {
        isPaused = false;
        if(platformInitialized)
            pollPopupServer();
    }

    public static void checkForPendingNewsId()
    {
        if(newsIdToBeShown != 0)
            showPopupforNewsId(newsIdToBeShown);
        newsIdToBeShown = 0;
    }

    public static void checkImmediatePopup()
    {
        Iterator iterator = getTransactionsNotAcked().iterator();
        PopupTransaction popuptransaction;
        do
        {
            if(!iterator.hasNext())
                return;
            popuptransaction = (PopupTransaction)iterator.next();
        } while(!popuptransaction.isImmediate());
        popItUp(popuptransaction);
    }

    public static void checkParameterizedPopup(int i)
    {
        Iterator iterator = getTransactionsNotAcked().iterator();
        PopupTransaction popuptransaction;
        do
        {
            if(!iterator.hasNext())
                return;
            popuptransaction = (PopupTransaction)iterator.next();
        } while(popuptransaction.isImmediate() || popuptransaction.getParameter1() == null || Integer.parseInt(popuptransaction.getParameter1()) != i);
        popItUp(popuptransaction);
    }

    public static Context getContext()
    {
        return appContext;
    }

    public static Handler getHandler()
    {
        return appHandler;
    }

    public static int getMoreGamesCount()
    {
        return moreGamesCount;
    }

    public static ArrayList getTransactionsNotAcked()
    {
        ArrayList arraylist = new ArrayList();
        Map map = listedTransactions;
        map;
        JVM INSTR monitorenter ;
        Iterator iterator = listedTransactions.values().iterator();
_L1:
        if(iterator.hasNext())
            break MISSING_BLOCK_LABEL_41;
        return arraylist;
        PopupTransaction popuptransaction = (PopupTransaction)iterator.next();
        if(transactionDB.popupIdNotAcked(popuptransaction.getPopupId()))
            arraylist.add(popuptransaction);
          goto _L1
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static boolean hasImmediatePopup()
    {
        Iterator iterator = getTransactionsNotAcked().iterator();
        do
            if(!iterator.hasNext())
                return false;
        while(!((PopupTransaction)iterator.next()).isImmediate());
        return true;
    }

    public static boolean hasParameterizedPopup(int i)
    {
        Iterator iterator = getTransactionsNotAcked().iterator();
        PopupTransaction popuptransaction;
        do
        {
            if(!iterator.hasNext())
                return false;
            popuptransaction = (PopupTransaction)iterator.next();
        } while(popuptransaction.isImmediate() || popuptransaction.getParameter1() == null || Integer.parseInt(popuptransaction.getParameter1()) != i);
        return true;
    }

    public static void incomingPushBundle(Bundle bundle)
    {
        int j = (new JSONObject(bundle.getString("com.parse.Data"))).getInt("news_id");
        int i = j;
_L2:
        if(!showPopupforNewsId(i) && i != 0)
        {
            newsIdToBeShown = i;
            pollPopupServer();
        }
        return;
        Exception exception;
        exception;
        i = 0;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static HashMap parsePollingResponse(JSONObject jsonobject)
        throws JSONException
    {
        HashMap hashmap = new HashMap();
        JSONArray jsonarray;
        int i;
        int j;
        try
        {
            moreGamesCount = jsonobject.getInt("game_count");
        }
        catch(Exception exception)
        {
            moreGamesCount = 0;
        }
        jsonarray = jsonobject.getJSONArray("results");
        i = jsonarray.length();
        j = 0;
        do
        {
            if(j >= i)
                return hashmap;
            JSONObject jsonobject1 = jsonarray.getJSONObject(j);
            PopupTransaction popuptransaction = new PopupTransaction(jsonobject1.getInt("id"));
            popuptransaction.setPopupId(jsonobject1.getInt("popup_id"));
            if(!jsonobject1.isNull("title"))
                popuptransaction.setTitle(jsonobject1.getString("title"));
            if(!jsonobject1.isNull("description"))
                popuptransaction.setDescription(jsonobject1.getString("description"));
            if(!jsonobject1.isNull("type"))
                popuptransaction.setType(jsonobject1.getString("type"));
            boolean flag;
            if(jsonobject1.getInt("immediate") == 1)
                flag = true;
            else
                flag = false;
            popuptransaction.setImmediate(flag);
            if(!jsonobject1.isNull("positive_button_text"))
                popuptransaction.setPositiveButtonText(jsonobject1.getString("positive_button_text"));
            if(!jsonobject1.isNull("negative_button_text"))
                popuptransaction.setNegativeButtonText(jsonobject1.getString("negative_button_text"));
            if(!jsonobject1.isNull("parameter_1"))
                popuptransaction.setParameter1(jsonobject1.getString("parameter_1"));
            if(!jsonobject1.isNull("parameter_2"))
                popuptransaction.setParameter2(jsonobject1.getString("parameter_2"));
            if(!jsonobject1.isNull("parameter_3"))
                popuptransaction.setParameter3(jsonobject1.getString("parameter_3"));
            if(!jsonobject1.isNull("parameter_4"))
                popuptransaction.setParameter4(jsonobject1.getString("parameter_4"));
            hashmap.put(Integer.valueOf(popuptransaction.getId()), popuptransaction);
            j++;
        } while(true);
    }

    public static void pollPopupServer()
    {
        if(appHandler == null)
        {
            return;
        } else
        {
            final Thread pollThread = new Thread() {

                public void checkPopups(URI uri)
                    throws IOException, ClientProtocolException, JSONException
                {
                    HttpGet httpget = new HttpGet(uri);
                    HashMap hashmap = Popup.parsePollingResponse(new JSONObject((String)(new DefaultHttpClient()).execute(httpget, new BasicResponseHandler())));
                    Popup.listedTransactions.putAll(hashmap);
                }

                public void run()
                {
                    String s = Popup.appContext.getPackageName();
                    String s1 = "1.0";
                    String s2;
                    URI uri;
                    Exception exception;
                    JSONException jsonexception;
                    NetworkInfo networkinfo;
                    try
                    {
                        s1 = Popup.appContext.getPackageManager().getPackageInfo(Popup.appContext.getPackageName(), 0).versionName;
                    }
                    catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception) { }
                    s2 = (new StringBuilder("?bundle_id=")).append(s).append("&platform=").append(Popup.platform).append("&version=").append(s1).toString();
                    Log.i("Popup", (new StringBuilder("http://news.noodlecake.net/popups/")).append(s2).toString());
                    try
                    {
                        uri = new URI((new StringBuilder("http://news.noodlecake.net/popups/")).append(s2).toString());
                    }
                    catch(URISyntaxException urisyntaxexception)
                    {
                        return;
                    }
                    if(Popup.isPaused)
                        break MISSING_BLOCK_LABEL_161;
                    networkinfo = ((ConnectivityManager)Popup.appContext.getSystemService("connectivity")).getActiveNetworkInfo();
                    if(networkinfo == null)
                        break MISSING_BLOCK_LABEL_161;
                    if(networkinfo.isConnected())
                    {
                        checkPopups(uri);
                        Popup.checkForPendingNewsId();
                    }
_L1:
                    return;
                    jsonexception;
                    Log.i("Popup", (new StringBuilder("json exception ")).append(jsonexception.getMessage()).toString());
                      goto _L1
                    exception;
                    Log.i("Popup", (new StringBuilder("regular exception ")).append(exception.getMessage()).toString());
                      goto _L1
                }

            }
;
            appHandler.post(new Runnable() {

                public void run()
                {
                    pollThread.start();
                }

                private final Thread val$pollThread;

            
            {
                pollThread = thread;
                super();
            }
            }
);
            return;
        }
    }

    public static void popItUp(PopupTransaction popuptransaction)
    {
        if(popuptransaction.getType().equals("MAILING_LIST"))
        {
            EmailPopup.popUpEmailDialog(popuptransaction);
        } else
        {
            if(!popuptransaction.getType().equals("URL_OPEN"))
                continue;
            OpenUrlPopup.openUrl(popuptransaction);
        }
        do
        {
            transactionDB.markPopupIdAcked(popuptransaction.getPopupId());
            do
                return;
            while(!popuptransaction.getType().equals("ALERT"));
            AlertPopup.popUpAlert(popuptransaction);
        } while(true);
    }

    public static void setCurrencyName(String s)
    {
        currencyName = s;
    }

    public static void setPlatform(int i)
    {
        platformInitialized = true;
        if(i != 1) goto _L2; else goto _L1
_L1:
        Log.i("Popup", "Huzzah...");
        platform = "amazon";
_L4:
        pollPopupServer();
        return;
_L2:
        if(i == 0)
            platform = "android";
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static int shouldCredit()
    {
        if(pendingCurrency > 0)
        {
            int i = pendingCurrency;
            pendingCurrency -= i;
            return i;
        } else
        {
            return 0;
        }
    }

    public static boolean showPopupforNewsId(int i)
    {
        Iterator iterator = getTransactionsNotAcked().iterator();
        PopupTransaction popuptransaction;
        do
        {
            if(!iterator.hasNext())
                return false;
            popuptransaction = (PopupTransaction)iterator.next();
        } while(popuptransaction.getPopupId() != i);
        popItUp(popuptransaction);
        return true;
    }

    public static void showSuccess(final int value)
    {
        if(appHandler != null)
            appHandler.post(new Runnable() {

                public void run()
                {
                    if(Popup.appContext != null)
                    {
                        Toast toast = Toast.makeText(Popup.appContext, (new StringBuilder("Thanks!  You've received ")).append(value).append(" ").append(Popup.currencyName).append("!").toString(), 1);
                        toast.setGravity(80, 0, 0);
                        toast.show();
                    }
                }

                private final int val$value;

            
            {
                value = i;
                super();
            }
            }
);
    }

    private static final String POPUP_URI = "http://news.noodlecake.net/popups/";
    private static final String TAG = "Popup";
    private static Context appContext;
    private static Handler appHandler;
    private static String currencyName = "Coins";
    private static boolean isPaused = true;
    private static Map listedTransactions = Collections.synchronizedMap(new HashMap());
    private static int moreGamesCount = 0;
    private static int newsIdToBeShown = 0;
    private static int pendingCurrency = 0;
    public static String platform = "android";
    public static boolean platformInitialized = false;
    private static PopupTransactionDB transactionDB = null;






}
