// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) ansi 

package com.noodlecake.noodlenews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PopupTransactionDB extends SQLiteOpenHelper
{

    public PopupTransactionDB(Context context)
    {
        super(context, "noodlenews", null, 1);
        db = getWritableDatabase();
    }

    public void close()
    {
        this;
        JVM INSTR monitorenter ;
        if(db != null)
            db.close();
        super.close();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void markPopupIdAcked(int i)
    {
        db.beginTransaction();
        ContentValues contentvalues = new ContentValues(2);
        contentvalues.put("popup_id", Integer.valueOf(i));
        contentvalues.put("acked", Integer.valueOf(1));
        db.insert("popups", null, contentvalues);
        db.setTransactionSuccessful();
        db.endTransaction();
        return;
        Exception exception;
        exception;
        db.endTransaction();
        throw exception;
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("CREATE TABLE popups ( popup_id INTEGER PRIMARY KEY, acked INTEGER(1) DEFAULT 0)");
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
    }

    public boolean popupIdNotAcked(int i)
    {
        boolean flag = true;
        Cursor cursor = db.query("popups", null, (new StringBuilder("popup_id =")).append(i).toString(), null, null, null, null);
        if(cursor.moveToFirst() && cursor.getCount() > 0)
            flag = false;
        cursor.close();
        return flag;
    }

    private static final String DATABASE_NAME = "noodlenews";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CREATE = "CREATE TABLE popups ( popup_id INTEGER PRIMARY KEY, acked INTEGER(1) DEFAULT 0)";
    private static final String TABLE_NAME = "popups";
    private SQLiteDatabase db;
}
