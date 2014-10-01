package org.cocos2dx.lib;

import android.content.DialogInterface;
import android.util.Log;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;

public class Cocos2dxAlertView {
    private static final String TAG = Cocos2dxAlertView.class.getSimpleName();

    public static void show(final String title, final String message, final String negative, final String positive, final String neutral) {
        final Activity a = (Cocos2dxActivity)Cocos2dxActivity.getContext();
        Log.d(TAG, "Show");
        a.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                bundle.putString("message", message);
                bundle.putString("positive", positive);
                bundle.putString("neutral", neutral);
                bundle.putString("negative", negative);
                _show(bundle);
            }
        });
    }
    protected static void _show(final Bundle bundle) {
        final Activity a = (Cocos2dxActivity)Cocos2dxActivity.getContext();
		AlertDialog.Builder dialog = new AlertDialog.Builder(a);

        dialog.setTitle(bundle.getString("title"));
        dialog.setMessage(bundle.getString("message"));

        if(bundle.getString("negative") != null) {
            dialog.setNegativeButton(bundle.getString("negative"),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onNegative();
                        }
                    });
        }
        if(bundle.getString("positive") != null) {
            dialog.setPositiveButton(bundle.getString("positive"),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onPositive();
                        }
                    });
        }
        if(bundle.getString("neutral") != null) {
            dialog.setNeutralButton(bundle.getString("neutral"), 
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onNeutral();
                        }
                    });
        }
        
        dialog.create().show();
    }

    public static native void onPositive();
    public static native void onNegative();
    public static native void onNeutral();

}

