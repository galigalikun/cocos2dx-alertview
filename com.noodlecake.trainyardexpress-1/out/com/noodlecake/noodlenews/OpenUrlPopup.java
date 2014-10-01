// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) ansi 

package com.noodlecake.noodlenews;

import android.app.AlertDialog;
import android.content.*;
import android.net.Uri;
import android.os.Handler;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.noodlecake.noodlenews:
//            Popup, PopupTransaction

public class OpenUrlPopup
{

    public OpenUrlPopup()
    {
    }

    public static void openUrl(final PopupTransaction popupObj)
    {
        Handler handler = Popup.getHandler();
        if(handler != null)
            handler.post(new Runnable() {

                public void run()
                {
                    Context context = Popup.getContext();
                    if(context != null)
                        (new android.app.AlertDialog.Builder(context)).setTitle(popupObj.getTitle()).setMessage(popupObj.getDescription()).setPositiveButton(popupObj.getPositiveButtonText(), context. new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int i)
                            {
                                Intent intent = new Intent("android.intent.action.VIEW");
                                intent.setData(Uri.parse(popupObj.getParameter2()));
                                appContext.startActivity(intent);
                                Popup.addCurrency(popupObj.getId());
                                HashMap hashmap = new HashMap();
                                hashmap.put("id", (new StringBuilder()).append(popupObj.getId()).toString());
                                hashmap.put("popup_id", (new StringBuilder()).append(popupObj.getPopupId()).toString());
                                hashmap.put("type", popupObj.getType());
                            }

                            final _cls1 this$1;
                            private final Context val$appContext;
                            private final PopupTransaction val$popupObj;

            
            {
                this$1 = final__pcls1;
                popupObj = popuptransaction;
                appContext = Context.this;
                super();
            }
                        }
).setNegativeButton(popupObj.getNegativeButtonText(), popupObj. new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int i)
                            {
                                HashMap hashmap = new HashMap();
                                hashmap.put("id", (new StringBuilder()).append(popupObj.getId()).toString());
                                hashmap.put("popup_id", (new StringBuilder()).append(popupObj.getPopupId()).toString());
                                hashmap.put("type", popupObj.getType());
                            }

                            final _cls1 this$1;
                            private final PopupTransaction val$popupObj;

            
            {
                this$1 = final__pcls1;
                popupObj = PopupTransaction.this;
                super();
            }
                        }
).create().show();
                }

                private final PopupTransaction val$popupObj;

            
            {
                popupObj = popuptransaction;
                super();
            }
            }
);
    }
}
