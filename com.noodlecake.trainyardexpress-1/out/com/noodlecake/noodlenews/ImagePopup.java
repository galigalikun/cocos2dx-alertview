// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) ansi 

package com.noodlecake.noodlenews;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.widget.ImageView;

// Referenced classes of package com.noodlecake.noodlenews:
//            Popup, PopupTransaction, WebImage

public class ImagePopup
{

    public ImagePopup()
    {
    }

    public static void popUpImage(final PopupTransaction popupObj)
    {
        Handler handler = Popup.getHandler();
        if(handler != null)
            handler.post(new Runnable() {

                public void run()
                {
                    android.content.Context context = Popup.getContext();
                    if(context != null)
                    {
                        ImageView imageview = new ImageView(context);
                        imageview.setImageBitmap((new WebImage("http://static.noodlecake.net/LRad-hd.jpg")).getBitmap(context));
                        imageview.setMinimumHeight(400);
                        (new android.app.AlertDialog.Builder(context)).setView(imageview).setPositiveButton(popupObj.getPositiveButtonText(), popupObj. new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int i)
                            {
                                Popup.addCurrency(popupObj.getId());
                            }

                            final _cls1 this$1;
                            private final PopupTransaction val$popupObj;

            
            {
                this$1 = final__pcls1;
                popupObj = PopupTransaction.this;
                super();
            }
                        }
).setNegativeButton(popupObj.getNegativeButtonText(), new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int i)
                            {
                            }

                            final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                        }
).create().show();
                    }
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
