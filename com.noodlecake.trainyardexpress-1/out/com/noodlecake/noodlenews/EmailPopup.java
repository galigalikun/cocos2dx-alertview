// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) ansi 

package com.noodlecake.noodlenews;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

// Referenced classes of package com.noodlecake.noodlenews:
//            Popup, PopupTransaction

public class EmailPopup
{

    public EmailPopup()
    {
    }

    public static String getPrePopEmail(String s)
    {
        Account aaccount[];
        int i;
        int j;
        aaccount = AccountManager.get(Popup.getContext()).getAccounts();
        i = aaccount.length;
        j = 0;
_L5:
        if(j < i) goto _L2; else goto _L1
_L1:
        String s1 = "";
_L4:
        return s1;
_L2:
        boolean flag;
        String as[];
        int k;
        int l;
        s1 = aaccount[j].name;
        if(!EMAIL_ADDRESS_PATTERN.matcher(s1).matches())
            break; /* Loop/switch isn't completed */
        flag = true;
        if(s == null || s.equals(""))
            continue; /* Loop/switch isn't completed */
        as = s.split(",");
        k = as.length;
        l = 0;
_L6:
        if(l < k)
            break MISSING_BLOCK_LABEL_99;
        if(flag) goto _L4; else goto _L3
_L3:
        j++;
          goto _L5
        if(s1.indexOf(as[l]) > -1)
            flag = false;
        l++;
          goto _L6
    }

    public static void popUpEmailDialog(final PopupTransaction popupObj)
    {
        Handler handler = Popup.getHandler();
        if(handler != null)
            handler.post(new Runnable() {

                public void run()
                {
                    android.content.Context context = Popup.getContext();
                    final EditText finalEmailField = new EditText(context);
                    if(context != null)
                    {
                        AlertDialog alertdialog = (new android.app.AlertDialog.Builder(context)).setTitle(popupObj.getTitle()).setView(finalEmailField).setMessage(popupObj.getDescription()).setPositiveButton(popupObj.getPositiveButtonText(), popupObj. new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int i)
                            {
                                sendEmail(finalEmailField.getText().toString());
                                Popup.addCurrency(popupObj.getId());
                                HashMap hashmap = new HashMap();
                                hashmap.put("id", (new StringBuilder()).append(popupObj.getId()).toString());
                                hashmap.put("popup_id", (new StringBuilder()).append(popupObj.getPopupId()).toString());
                                hashmap.put("type", popupObj.getType());
                            }

                            final _cls1 this$1;
                            private final EditText val$finalEmailField;
                            private final PopupTransaction val$popupObj;

            
            {
                this$1 = final__pcls1;
                finalEmailField = edittext;
                popupObj = PopupTransaction.this;
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
).create();
                        alertdialog.show();
                        alertdialog.getButton(-1).setEnabled(false);
                        finalEmailField.addTextChangedListener(alertdialog. new TextWatcher() {

                            public void afterTextChanged(Editable editable)
                            {
                            }

                            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
                            {
                            }

                            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
                            {
                                if(EmailPopup.EMAIL_ADDRESS_PATTERN.matcher(finalEmailField.getText().toString()).matches())
                                {
                                    dialog.getButton(-1).setEnabled(true);
                                    return;
                                } else
                                {
                                    dialog.getButton(-1).setEnabled(false);
                                    return;
                                }
                            }

                            final _cls1 this$1;
                            private final AlertDialog val$dialog;
                            private final EditText val$finalEmailField;

            
            {
                this$1 = final__pcls1;
                finalEmailField = edittext;
                dialog = AlertDialog.this;
                super();
            }
                        }
);
                        finalEmailField.setText(EmailPopup.getPrePopEmail(popupObj.getParameter4()));
                    }
                }

                public void sendEmail(final String email)
                {
                    (popupObj. new Thread() {

                        public void run()
                        {
                            String s = (new StringBuilder("?bundle_id=com.noodlecake.ssg&platform=android&email=")).append(email).append("&referral_id=").append(popupObj.getId()).toString();
                            URI uri;
                            HttpGet httpget;
                            DefaultHttpClient defaulthttpclient;
                            try
                            {
                                uri = new URI((new StringBuilder(String.valueOf(popupObj.getParameter2()))).append(s).toString());
                            }
                            catch(URISyntaxException urisyntaxexception)
                            {
                                return;
                            }
                            httpget = new HttpGet(uri);
                            defaulthttpclient = new DefaultHttpClient();
                            defaulthttpclient.execute(httpget).getStatusLine().getStatusCode();
_L2:
                            return;
                            Exception exception;
                            exception;
                            if(true) goto _L2; else goto _L1
_L1:
                        }

                        final _cls1 this$1;
                        private final String val$email;
                        private final PopupTransaction val$popupObj;

            
            {
                this$1 = final__pcls1;
                email = s;
                popupObj = PopupTransaction.this;
                super();
            }
                    }
).start();
                }

                private final PopupTransaction val$popupObj;

            
            {
                popupObj = popuptransaction;
                super();
            }
            }
);
    }

    private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+");


}
