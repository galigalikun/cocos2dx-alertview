// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) ansi 

package com.noodlecake.noodlenews;


public class PopupTransaction
{

    public PopupTransaction(int i)
    {
        id = i;
    }

    public String getDescription()
    {
        return description;
    }

    public int getId()
    {
        return id;
    }

    public String getNegativeButtonText()
    {
        return negative_button_text;
    }

    public String getParameter1()
    {
        return parameter_1;
    }

    public String getParameter2()
    {
        return parameter_2;
    }

    public String getParameter3()
    {
        return parameter_3;
    }

    public String getParameter4()
    {
        return parameter_4;
    }

    public int getPopupId()
    {
        return popup_id;
    }

    public String getPositiveButtonText()
    {
        return positive_button_text;
    }

    public String getTitle()
    {
        return title;
    }

    public String getType()
    {
        return type;
    }

    public boolean isAcked()
    {
        return acked;
    }

    public boolean isImmediate()
    {
        return immediate;
    }

    public void setAcked(boolean flag)
    {
        acked = flag;
    }

    public void setDescription(String s)
    {
        description = s;
    }

    public void setId(int i)
    {
        id = i;
    }

    public void setImmediate(boolean flag)
    {
        immediate = flag;
    }

    public void setNegativeButtonText(String s)
    {
        negative_button_text = s;
    }

    public void setParameter1(String s)
    {
        parameter_1 = s;
    }

    public void setParameter2(String s)
    {
        parameter_2 = s;
    }

    public void setParameter3(String s)
    {
        parameter_3 = s;
    }

    public void setParameter4(String s)
    {
        parameter_4 = s;
    }

    public void setPopupId(int i)
    {
        popup_id = i;
    }

    public void setPositiveButtonText(String s)
    {
        positive_button_text = s;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void setType(String s)
    {
        type = s.toUpperCase();
    }

    private boolean acked;
    private String description;
    private int id;
    private boolean immediate;
    private String negative_button_text;
    private String parameter_1;
    private String parameter_2;
    private String parameter_3;
    private String parameter_4;
    private int popup_id;
    private String positive_button_text;
    private String title;
    private String type;
}
