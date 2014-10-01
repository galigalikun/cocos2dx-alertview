// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) ansi 

package com.noodlecake.noodlenews;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.noodlecake.noodlenews:
//            Popup, PopupTransaction

public class AlertPopup
{

    public AlertPopup()
    {
    }

    public static void popUpAlert(final PopupTransaction popupObj)
    {
        Handler handler = Popup.getHandler();
        if(handler != null)
            handler.post(new Runnable() {

                public void run()
                {
                    android.content.Context context = Popup.getContext();
                    if(context != null)
                        (new android.app.AlertDialog.Builder(context)).setTitle(popupObj.getTitle()).setMessage(popupObj.getDescription()).setPositiveButton(popupObj.getPositiveButtonText(), popupObj. new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int i)
                            {
                                Popup.addCurrency(popupObj.getId());
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
