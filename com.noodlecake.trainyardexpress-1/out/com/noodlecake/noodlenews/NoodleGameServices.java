// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) ansi 

package com.noodlecake.noodlenews;

import android.app.Activity;
import android.util.Log;
import com.amazon.ags.api.*;
import com.amazon.ags.api.achievements.AchievementsClient;
import com.amazon.ags.api.achievements.UpdateProgressResponse;
import com.amazon.ags.api.overlay.PopUpLocation;
import java.util.EnumSet;

// Referenced classes of package com.noodlecake.noodlenews:
//            NoodlecakeGameActivity

public class NoodleGameServices
{

    public NoodleGameServices(Activity activity)
    {
        if(NoodlecakeGameActivity.getPlatform() == 1)
        {
            EnumSet enumset = EnumSet.of(AmazonGamesFeature.Achievements);
            agsClient = AmazonGamesClient.initialize(activity.getApplication(), new AmazonGamesCallback() {

                static int[] $SWITCH_TABLE$com$amazon$ags$api$AmazonGamesStatus()
                {
                    int ai[] = $SWITCH_TABLE$com$amazon$ags$api$AmazonGamesStatus;
                    if(ai != null)
                        return ai;
                    int ai1[] = new int[AmazonGamesStatus.values().length];
                    try
                    {
                        ai1[AmazonGamesStatus.CANNOT_AUTHORIZE.ordinal()] = 6;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        ai1[AmazonGamesStatus.CANNOT_BIND.ordinal()] = 4;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        ai1[AmazonGamesStatus.INITIALIZING.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror2) { }
                    try
                    {
                        ai1[AmazonGamesStatus.INVALID_SESSION.ordinal()] = 5;
                    }
                    catch(NoSuchFieldError nosuchfielderror3) { }
                    try
                    {
                        ai1[AmazonGamesStatus.NOT_AUTHENTICATED.ordinal()] = 9;
                    }
                    catch(NoSuchFieldError nosuchfielderror4) { }
                    try
                    {
                        ai1[AmazonGamesStatus.NOT_AUTHORIZED.ordinal()] = 7;
                    }
                    catch(NoSuchFieldError nosuchfielderror5) { }
                    try
                    {
                        ai1[AmazonGamesStatus.SERVICE_CONNECTED.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror6) { }
                    try
                    {
                        ai1[AmazonGamesStatus.SERVICE_DISCONNECTED.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError nosuchfielderror7) { }
                    try
                    {
                        ai1[AmazonGamesStatus.SERVICE_NOT_OPTED_IN.ordinal()] = 8;
                    }
                    catch(NoSuchFieldError nosuchfielderror8) { }
                    $SWITCH_TABLE$com$amazon$ags$api$AmazonGamesStatus = ai1;
                    return ai1;
                }

                public void onServiceNotReady(AmazonGamesStatus amazongamesstatus)
                {
                    NoodleGameServices.authenticated = false;
                    Log.v("Amazon Game Circle", "--- something");
                    switch($SWITCH_TABLE$com$amazon$ags$api$AmazonGamesStatus()[amazongamesstatus.ordinal()])
                    {
                    case 5: // '\005'
                    case 8: // '\b'
                    default:
                        return;

                    case 4: // '\004'
                        Log.v("Amazon Game Circle", "--- cannot bind");
                        // fall through

                    case 6: // '\006'
                        Log.v("Amazon Game Circle", "--- cannot authorize");
                        // fall through

                    case 7: // '\007'
                        Log.v("Amazon Game Circle", "--- not authorized");
                        // fall through

                    case 9: // '\t'
                        Log.v("Amazon Game Circle", "--- not authenticated");
                        break;
                    }
                }

                public void onServiceReady()
                {
                    Log.v("Amazon Game Circle", "--- service ready");
                    NoodleGameServices.authenticated = true;
                }

                private static int $SWITCH_TABLE$com$amazon$ags$api$AmazonGamesStatus[];
                final NoodleGameServices this$0;

            
            {
                this$0 = NoodleGameServices.this;
                super();
            }
            }
, enumset);
        }
    }

    public static boolean isAuthenticated()
    {
        if(NoodlecakeGameActivity.getPlatform() == 1)
            return authenticated;
        else
            return false;
    }

    public static boolean isSupported()
    {
        return isAuthenticated();
    }

    public static void setPopUpLocation()
    {
        if(NoodlecakeGameActivity.getPlatform() == 1)
            agsClient.setPopUpLocation(PopUpLocation.BOTTOM_RIGHT);
    }

    public static void showAchievements()
    {
        if(NoodlecakeGameActivity.getPlatform() == 1 && isSupported())
            agsClient.getAchievementsClient().showAchievementsOverlay(new Object[0]).setCallback(new AGResponseCallback() {

                public void onComplete(RequestResponse requestresponse)
                {
                    if(requestresponse.isError())
                    {
                        Log.v("Amazon Game Circle", "RequestResponse --- error");
                        NoodleGameServices.authenticated = false;
                        return;
                    } else
                    {
                        Log.v("Amazon Game Circle", "RequestResponse --- successful!");
                        return;
                    }
                }

            }
);
    }

    public static void updateProgress(String s, float f)
    {
        if(NoodlecakeGameActivity.getPlatform() == 1 && isSupported())
        {
            String s1 = s.replace('.', '_');
            agsClient.getAchievementsClient().updateProgress(s1, f, new Object[0]).setCallback(new AGResponseCallback() {

                public volatile void onComplete(RequestResponse requestresponse)
                {
                    onComplete((UpdateProgressResponse)requestresponse);
                }

                public void onComplete(UpdateProgressResponse updateprogressresponse)
                {
                    if(updateprogressresponse.isError())
                    {
                        Log.v("Amazon Game Circle", "Update Progress --- error");
                        return;
                    } else
                    {
                        Log.v("Amazon Game Circle", "UpdateProgress --- successful!");
                        return;
                    }
                }

            }
);
        }
    }

    private static AmazonGames agsClient;
    private static boolean authenticated = false;


}
